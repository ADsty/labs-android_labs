package com.petrov.vitaliy.lab6.watch

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import com.petrov.vitaliy.lab6.R
import kotlinx.android.synthetic.main.fragment_watch.*
import kotlinx.coroutines.*

class CoroutineFragment : Fragment() {

    private lateinit var job: Job

    private lateinit var preferences: SharedPreferences

    private var lastNumber: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        preferences = activity!!.getSharedPreferences("myPref", Context.MODE_PRIVATE)
        return inflater.inflate(R.layout.fragment_watch, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        lastNumber = preferences.getInt("lastNumber", 0)
        job = lifecycle.coroutineScope.launchWhenResumed {
            while (isActive) {
                withContext(Dispatchers.Default){
                    val time = System.nanoTime()
                    while(System.nanoTime() - time < 1000000000);
                    lastNumber++
                    println(lastNumber)
                }
                text_seconds_elapsed.text = "Seconds elapsed: $lastNumber"
            }
        }.also {
            it.invokeOnCompletion { cause->
                if(cause is CancellationException)
                    println("I'm done")
            }
        }
        super.onResume()
    }

    override fun onPause() {
        preferences.edit().putInt("lastNumber", lastNumber).apply()
        job.cancel()
        super.onPause()
    }
}
