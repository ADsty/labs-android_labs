package ru.spbstu.icc.kspt.lab2.continuewatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class AnotherActivity : AppCompatActivity() {
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
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        secondsElapsed = savedInstanceState.getInt("count")
    }

    override fun onResume() {
        super.onResume()
        stopped = false
        backgroundThread.start()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", secondsElapsed)
    }

    override fun onPause() {
        super.onPause()
        stopped = true
        backgroundThread.join()
        if(!backgroundThread.isAlive){
            println("Counter stopped")
        }
    }
}