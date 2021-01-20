package com.petrov.vitaliy.lab6.watch


import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.petrov.vitaliy.lab6.R
import kotlinx.android.synthetic.main.fragment_watch.*


class AsyncTaskFragment : Fragment() {

    private lateinit var timerAsyncTask : TimerAsyncTask

    private lateinit var preferences: SharedPreferences

    private var lastNumber: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        preferences = activity!!.getSharedPreferences("myPref", Context.MODE_PRIVATE)
        return inflater.inflate(R.layout.fragment_watch, container, false)
    }

    override fun onResume() {
        lastNumber = preferences.getInt("lastNumber", 0)
        timerAsyncTask = TimerAsyncTask()
        timerAsyncTask.execute()
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        timerAsyncTask.cancel(true)
        preferences.edit().putInt("lastNumber", lastNumber).apply()
    }

    @SuppressLint("StaticFieldLeak")
    private inner class TimerAsyncTask internal constructor() : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg params: Void?): Void? {
            while (!isCancelled) {
                val time = System.currentTimeMillis()
                while(System.currentTimeMillis() - time < 1000);
                lastNumber++
                publishProgress()
            }
            println("i finished")
            return null
        }

        @SuppressLint("SetTextI18n")
        override fun onProgressUpdate(vararg values: Void?) {
            if(!isCancelled) {
                super.onProgressUpdate(*values)
                text_seconds_elapsed.text = "Seconds elapsed: $lastNumber"
            }
        }
    }
}
