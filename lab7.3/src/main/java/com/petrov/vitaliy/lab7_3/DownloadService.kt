package com.petrov.vitaliy.lab7_3

import android.annotation.SuppressLint
import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.FileOutputStream
import java.net.URL

class DownloadService : IntentService("downloadService") {

    private var url: String? = null

    private fun sendBroadcast(msg: String) {
        sendBroadcast(Intent("getPath").putExtra("path", msg))
    }

    override fun onHandleIntent(intent: Intent?) {
        url = intent?.getStringExtra("url")
        val bitmap: Bitmap?
        bitmap = try {
            URL(url).openStream().use {
                return@use BitmapFactory.decodeStream(it)
            }
        }catch (e: Exception){
            null
        }
        val path = saveImage(bitmap)
        sendBroadcast(path)
    }

    private fun saveImage(bitmap: Bitmap?): String {
        val nameOfImage = "myImage"
        val stream: FileOutputStream
        if(bitmap == null) return "null"
        try {
            stream = applicationContext.openFileOutput(nameOfImage, Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return applicationContext.getFileStreamPath(nameOfImage).absolutePath
    }

    private lateinit var fromClientMessenger: Messenger

    private val messageFromClient = 1
    private val toClient = 2

    override fun onBind(intent: Intent): IBinder? {
        fromClientMessenger = Messenger(
        @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(message: Message) {
                when (message.what) {

                    messageFromClient -> {
                        sendMessageToClient(message)
                    }
                }
            }
        })
        return fromClientMessenger.binder
    }

    private fun sendMessageToClient(messageFromClient: Message): Bitmap? {
        val url = messageFromClient.data.getString("url", url)
        var bitmap: Bitmap? = null
        val toClientService = messageFromClient.replyTo

        CoroutineScope(Dispatchers.Main).launch {
            bitmap = async(Dispatchers.IO) {
                try {
                    URL(url).openStream().use {
                        return@async BitmapFactory.decodeStream(it)
                    }
                }catch (e: Exception){
                    return@async null
                }
            }.await()

            val path = saveImage(bitmap)

            val message = Message.obtain(null, toClient, 0, 0).apply {
                data = Bundle().apply {
                    putString("answer", path)
                }
            }
            toClientService.send(message)
        }

        return bitmap
    }
}