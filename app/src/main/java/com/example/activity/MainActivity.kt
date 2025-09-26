package com.example.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//Hello its me
//Second task
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationApp()
        }
    }
}

@Composable
fun NavigationApp() {
    val context = LocalContext.current

    MainScreen(
        onNavigateToThird = {
            context.startActivity(Intent(context, ThirdActivity::class.java))
            (context as? ComponentActivity)?.finish()
        }
    )
}

@Composable
fun MainScreen(onNavigateToThird: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "You in First activity", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(24.dp))

        TextMessange(
            nameOfField = "username",
            value = username,
            onValueChange = { newValue -> username = newValue }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextMessange(
            nameOfField = "password",
            value = email,
            onValueChange = { newValue -> email = newValue }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Username: $username")
        Text("Password: $email")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                println("User: $username, Email: $email")
                onNavigateToThird()
            },
            enabled = username.isNotEmpty() && email.isNotEmpty()
        ) {
            Text(text = "Continue", fontSize = 20.sp)
        }
    }
}

@Composable
fun TextMessange(
    nameOfField: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        TextField(
            value = value,
            onValueChange = { newText ->
                onValueChange(newText)
            },
            label = { Text("Enter $nameOfField") }
        )
    }
}