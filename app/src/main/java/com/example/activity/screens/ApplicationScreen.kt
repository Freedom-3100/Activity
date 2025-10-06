package com.example.activity.screens

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.activity.R
import com.example.activity.ui.theme.AppTheme


class ApplicationScreen: Fragment(R.layout.fragment_application_screen) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val navController = findNavController()
               AppScreen(navController)
            }
        }
    }
}

@Composable
fun AppScreen(navController : NavController)
{
    Column(modifier = Modifier.fillMaxSize())
    {
        Box (modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colorScheme.primary))
        Column( modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {

            Image( imageVector = Icons.Filled.ThumbUp,
                contentDescription = "Sucsess login",
                modifier = Modifier.size(50.dp, 50.dp))
            Spacer(modifier = Modifier.padding(10.dp))
            Text("Вы вошли в приложение", fontSize = 24.sp)

            Button(modifier = Modifier.padding(top = 10.dp),onClick = {navController.navigate(R.id.action_applicationScreen_to_registrationScreen)})
            {
                Text(text = "Go to Registration Screen", fontSize = 24.sp)
            }
        }
    }

}




//@Preview(showBackground = true)
//@Composable
//fun ShowAppScreen()
//{
//    AppScreen()
//}
