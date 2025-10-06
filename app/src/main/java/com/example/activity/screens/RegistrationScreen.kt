package com.example.activity.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.activity.R
import com.example.activity.model.Model

class RegistrationScreen : Fragment(R.layout.fragment_registaration_screen) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val navController = findNavController()
        return ComposeView(requireContext()).apply {
            setContent {
                Screen(navController)
            }
        }
    }
}

@Composable
fun Screen(navController: NavController) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") } // состояние ошибки

    Column(modifier = Modifier.fillMaxSize())
    {
        Box (modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(MaterialTheme.colorScheme.primary))

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Вход в приложение", fontSize = 24.sp)

            TextMessage(
                nameOfField = "username",
                value = login,
                onValueChange = { login = it }
            )

            TextMessage(
                nameOfField = "password",
                value = password,
                onValueChange = { password = it }
            )

            if (errorMessage.isNotEmpty()) { // показываем сообщение ошибки
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Button(onClick = {
                val isValid = check(login, password)
                if (isValid) {
                    navController.navigate(R.id.action_registrationScreen_to_applicationScreen)
                    errorMessage = "" // сбрасываем ошибку
                } else {
                    errorMessage = "Неверный логин или пароль" // показываем на экране
                }
            }) {
                Text("Go to next screen", fontSize = 24.sp)
            }
        }
    }


}

@Composable
fun TextMessage(
    nameOfField: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = value,
            onValueChange = { newText ->
                onValueChange(newText)
            },
            label = { Text("Enter $nameOfField") }
        )
    }
}


fun check(login: String, password : String): Boolean
{
    return  Model().checkPassword(password) && Model().checkLogin(login)
}


//@Preview(showBackground = true)
//@Composable
//fun ShowScreen()
//{
//    Screen()
//}