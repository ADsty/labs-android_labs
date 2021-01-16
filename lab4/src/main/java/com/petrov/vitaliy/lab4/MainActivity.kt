package com.petrov.vitaliy.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GetData.setResources(resources.openRawResource(R.raw.references))

        nav_view_main.setupWithNavController(findNavController(R.id.nav_host_fragment))
    }
}