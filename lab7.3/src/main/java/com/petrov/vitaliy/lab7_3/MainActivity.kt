package com.petrov.vitaliy.lab7_3

import android.annotation.SuppressLint
import android.content.*
import android.graphics.BitmapFactory
import android.os.*
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var broadcastReceiver: BroadcastReceiver

    private val url1 =
        "https://1.bp.blogspot.com/-PmdvONVt7gY/UqRUZDsaWtI/AAAAAAAAEfs/UwZ0i5Anb90/s1600/e33.jpeg"

    private val url2 = "https://memepedia.ru/wp-content/uploads/2019/12/kot-gruzitsja-9.jpg"

    private val messageFromClient = 1
    private val messageFromService = 2

    private var toServiceMessenger: Messenger? = null

    private val connectionOfService = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName) {
            toServiceMessenger = null
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            toServiceMessenger = Messenger(service)
        }
    }

    private val fromServiceMessenger = Messenger(CaughtMessages())

    @SuppressLint("HandlerLeak")
    inner class CaughtMessages : Handler() {

        override fun handleMessage(message: Message) {
            when (message.what) {
                messageFromService -> {
                    val path = message.data.getString("answer")!!
                    val file = File(path)
                    if(file.exists()) {
                        image.setImageBitmap(BitmapFactory.decodeFile(path))
                    }
                    else{
                        Toast.makeText(this@MainActivity, "Can't Download", Toast.LENGTH_SHORT).show()
                    }
                    load_bar.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        registerReceiver(broadcastReceiver, IntentFilter("getPath"))

        val intent = Intent(this, DownloadService::class.java)
        this.bindService(intent, connectionOfService, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()

        unregisterReceiver(broadcastReceiver)

        this.unbindService(connectionOfService)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val path = intent?.getStringExtra("path")!!
                val file = File(path)
                if(file.exists()) {
                    image.setImageBitmap(BitmapFactory.decodeFile(path))
                }
                else{
                    Toast.makeText(context, "Can't Download", Toast.LENGTH_SHORT).show()
                }
                load_bar.visibility = View.INVISIBLE
            }
        }

        lab7_1_button.setOnClickListener {
            startService(
                Intent(this, DownloadService::class.java).putExtra("url", url1)
            )
            load_bar.visibility = View.VISIBLE
        }

        lab7_3_button.setOnClickListener {
            val message = Message.obtain(null, messageFromClient, 0, 0)

            message.data = Bundle().apply {
                putString("url", url2)
            }

            message.replyTo = fromServiceMessenger
            toServiceMessenger?.send(message)
            load_bar.visibility = View.VISIBLE
        }
    }
}