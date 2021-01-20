package com.petrov.vitaliy.lab6.watch


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.petrov.vitaliy.lab6.R
import kotlinx.android.synthetic.main.fragment_menu_watch.*

class MenuWatchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_watch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        use_threads.setOnClickListener {
            this.findNavController().navigate(R.id.action_from_menu_watch_use_threads)
        }

        use_async_task.setOnClickListener {
            this.findNavController().navigate(R.id.action_from_menu_watch_use_async_task)
        }

        use_coroutines.setOnClickListener {
            this.findNavController().navigate(R.id.action_from_menu_watch_use_coroutines)
        }
    }
}
