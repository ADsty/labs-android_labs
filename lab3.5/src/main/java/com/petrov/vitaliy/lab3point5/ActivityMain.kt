package com.petrov.vitaliy.lab3point5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView

class ActivityMain: AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item ->
        val o = supportFragmentManager.fragments[0]
        val i = NavHostFragment.findNavController(o).currentDestination?.id
        if(item.itemId == R.id.about) {
            when (i) {
                R.id.ActivityFragment1 -> NavHostFragment.findNavController(o)
                    .navigate(R.id.from_first_to_about)
                R.id.ActivityFragment2 -> NavHostFragment.findNavController(o)
                    .navigate(R.id.from_second_to_about)
                R.id.ActivityFragment3 -> NavHostFragment.findNavController(o)
                    .navigate(R.id.from_third_to_about)
            }
        }
        else if (item.itemId == R.id.clear){
            when (i) {
                R.id.ActivityFragment1 -> NavHostFragment.findNavController(o)
                    .navigate(R.id.from_first_to_clear)
                R.id.ActivityFragment2 -> NavHostFragment.findNavController(o)
                    .navigate(R.id.from_second_to_clear)
                R.id.ActivityFragment3 -> NavHostFragment.findNavController(o)
                    .navigate(R.id.from_third_to_clear)
            }
        }
        return@OnNavigationItemSelectedListener  true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nav : NavigationView = findViewById(R.id.nav_view)
        nav.setNavigationItemSelectedListener (mOnNavigationItemSelectedListener)
    }
}