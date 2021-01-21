package com.petrov.vitaliy.lab7_2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_get_path.*

class GetPathFromOtherApp : AppCompatActivity() {
    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_path)

        broadcastReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context?, intent: Intent?) {
                val path = intent?.getStringExtra("path")
                message_text.text = path
            }
        }
        registerReceiver(broadcastReceiver, IntentFilter("getPath"))
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putCharSequence("text", message_text.text)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        message_text.text = savedInstanceState.getCharSequence("text")
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }
}