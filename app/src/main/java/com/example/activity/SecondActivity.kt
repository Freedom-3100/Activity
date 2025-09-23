package com.example.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_another)
        findViewById<Button>(R.id.send_to_act).setOnClickListener {
            val context: Context = this@SecondActivity
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
        }
    }
