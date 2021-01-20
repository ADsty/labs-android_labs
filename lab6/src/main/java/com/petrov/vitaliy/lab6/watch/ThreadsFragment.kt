package com.petrov.vitaliy.lab6.watch

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.petrov.vitaliy.lab6.R
import kotlinx.android.synthetic.main.fragment_watch.*

class ThreadsFragment : Fragment() {
    private lateinit var preferences: SharedPreferences

    var stopped: Boolean = true
    private var lastNumber: Int = 0

    @SuppressLint("SetTextI18n")
    private var timer = Thread{
        while (!stopped) {
            val time = System.nanoTime()
            while(System.nanoTime() - time < 1000000000);
            lastNumber++
            if(!stopped)
                text_seconds_elapsed.post {
                    text_seconds_elapsed.text = "Seconds elapsed: $lastNumber"
                }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        preferences = activity!!.getSharedPreferences("myPref", MODE_PRIVATE)
        return inflater.inflate(R.layout.fragment_watch, container, false)
    }

    override fun onResume() {
        super.onResume()
        if (preferences.contains("lastNumber"))
            lastNumber = preferences.getInt("lastNumber", 0)
        stopped = false
        timer.start()
    }

    override fun onPause() {
        super.onPause()
        preferences.edit().putInt("lastNumber", lastNumber).apply()
        println("Threads count is " + Thread.activeCount())
        stopped = true
        timer.join()
        if(!timer.isAlive){
            println("Timer was killed")
            println("Threads count is " + Thread.activeCount())
        }
    }


}
