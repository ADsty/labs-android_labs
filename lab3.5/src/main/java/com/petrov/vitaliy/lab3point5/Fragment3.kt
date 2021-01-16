package com.petrov.vitaliy.lab3point5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

class Fragment3: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val button1: Button = view.findViewById(R.id.from_3_to_2)
        button1.setOnClickListener{
            NavHostFragment.findNavController(this).navigate(R.id.from_third_to_second)
        }
        val button2: Button = view.findViewById(R.id.from_3_to_1)
        button2.setOnClickListener{
            NavHostFragment.findNavController(this).navigate(R.id.from_third_to_first)
        }
    }

}