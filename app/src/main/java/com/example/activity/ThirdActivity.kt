package com.example.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.activity.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)
        findViewById<Button>(R.id.send_to_act).setOnClickListener {
            val context: Context = this@ThirdActivity
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            finish()
        }
    }
}

//создал новую ветку Фрагмент