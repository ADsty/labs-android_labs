package com.petrov.vitaliy.lab32

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

class Fragment2: Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val button1: Button = view.findViewById(R.id.from_2_to_3)
        button1.setOnClickListener{
            NavHostFragment.findNavController(this).navigate(R.id.from_second_to_third)
        }
        val button2: Button = view.findViewById(R.id.from_2_to_1)
        button2.setOnClickListener{
            NavHostFragment.findNavController(this).navigate(R.id.from_second_to_first)
        }
    }

}