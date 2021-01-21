package com.petrov.vitaliy.lab7_1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start_service.*
import java.io.File

class StartService: AppCompatActivity() {

    private val url =
        "https://1.bp.blogspot.com/-PmdvONVt7gY/UqRUZDsaWtI/AAAAAAAAEfs/UwZ0i5Anb90/s1600/e33.jpeg"

    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_service)

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

        start_service_btn.setOnClickListener {
            startService(
                Intent(this, DownloadService::class.java).putExtra("url", url)
            )
            load_bar.visibility = View.VISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(broadcastReceiver, IntentFilter("getPath"))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }
}