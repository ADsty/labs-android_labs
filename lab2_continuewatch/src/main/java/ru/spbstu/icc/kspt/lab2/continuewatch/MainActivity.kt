package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences : SharedPreferences
    var secondsElapsed: Int = 0
    var stopped: Boolean = false
    var backgroundThread = Thread {
        while (!stopped) {
            Thread.sleep(1000)
            if(!stopped) {
                textSecondsElapsed.post {
                    textSecondsElapsed.text = "Seconds elapsed: " + secondsElapsed++
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("seconds", Context.MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        stopped = false
        if (sharedPreferences.contains("savedSeconds"))
            secondsElapsed = sharedPreferences.getInt("savedSeconds", 0)
        backgroundThread.start()
    }

    override fun onPause() {
        super.onPause()
        stopped = true
        backgroundThread.join()
        if(!backgroundThread.isAlive){
            println("Counter stopped")
        }
        sharedPreferences.edit().putInt("savedSeconds", secondsElapsed).apply()
    }
}
