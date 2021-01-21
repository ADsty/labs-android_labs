package com.petrov.vitaliy.lab7_1

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.FileOutputStream
import java.net.URL

class DownloadService : IntentService("downloadService") {

    override fun onHandleIntent(intent: Intent?) {
        val url = intent?.getStringExtra("url")
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

    private fun sendBroadcast(msg: String) {
        sendBroadcast(Intent("getPath").putExtra("path", msg))
    }
}