package com.petrov.vitaliy.lab32

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView

class Activity2: AppCompatActivity() {

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
        setContentView(R.layout.activity2)
        val nav : NavigationView = findViewById(R.id.nav_view)
        nav.setNavigationItemSelectedListener (mOnNavigationItemSelectedListener)
        val button1: Button = findViewById(R.id.from_2_to_1)
        button1.setOnClickListener { finish() }
        val button2: Button = findViewById(R.id.from_2_to_3)
        button2.setOnClickListener{
            val intent = Intent(this, Activity3::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 1) {
            finish()
        }
    }

}