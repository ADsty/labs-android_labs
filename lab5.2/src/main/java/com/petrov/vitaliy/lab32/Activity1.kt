package com.petrov.vitaliy.lab32

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView


class Activity1 : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item ->
        if(item.itemId == R.id.about) {
            val intent = Intent(this, ActivityAbout::class.java)
            startActivity(intent)
        }
        else if (item.itemId == R.id.clear){
            val intent = Intent(this, ActivityClear::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
        return@OnNavigationItemSelectedListener  true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1)

        val button1: Button = findViewById(R.id.from_1_to_2)
        button1.setOnClickListener{
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }
        val nav : NavigationView = findViewById(R.id.nav_view)
        nav.setNavigationItemSelectedListener (mOnNavigationItemSelectedListener)
    }

}