package com.example.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activity.databinding.MainActivityBinding
import com.example.activity.ui.theme.AppTheme


class MainActivity : AppCompatActivity() {
    lateinit var binding: MainActivityBinding
    private val adapter = Adapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        loadCards()
    }


    private fun init() =  with(binding)
    {
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = adapter
    }

    private fun loadCards() {
        val cards = listOf(
            Card(R.drawable.ic_android, "Android Developer", "Крутой"),
            Card(R.drawable.ic_code, "Программист", "Мега Крутой"),
            Card(R.drawable.ic_design, "Дизайнер", "Супер мега крутой"),
            Card(R.drawable.ic_analytics, "Аналитик", "Супер мега крутой"),
            Card(R.drawable.ic_manager, "Менеджер", "Супер мега крутой"),
            Card(R.drawable.ic_testing, "Тестировщик", "Супер мега крутой"),
            Card(R.drawable.ic_security, "Безопасность", "Супер мега крутой"),
            Card(R.drawable.ic_cloud, "Cloud инженер", "Супер мега крутой"),
            Card(R.drawable.ic_mobile, "Мобильная разработка", "Супер мега крутой"),
            Card(R.drawable.ic_web, "Веб разработка", "Супер мега крутой"),
            Card(R.drawable.ic_android, "Android Developer", "Супер мега крутой"),
            Card(R.drawable.ic_code, "Программист", "Супер мега крутой"),
            Card(R.drawable.ic_design, "Дизайнер", "Супер мега крутой"),
            Card(R.drawable.ic_analytics, "Аналитик", "Супер мега крутой"),
            Card(R.drawable.ic_manager, "Менеджер", "Супер мега крутой"),
            Card(R.drawable.ic_testing, "Тестировщик", "Супер мега крутой"),
            Card(R.drawable.ic_security, "Безопасность", "Супер мега крутой"),
            Card(R.drawable.ic_cloud, "Cloud инженер", "Супер мега крутой"),
            Card(R.drawable.ic_mobile, "Мобильная разработка", "Супер мега крутой"),
            Card(R.drawable.ic_web, "Веб разработка", "Супер мега крутой")
        )

        adapter.addAllCards(cards)
    }

}
