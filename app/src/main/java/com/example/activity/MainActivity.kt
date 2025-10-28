package com.example.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activity.databinding.MainActivityBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private val adapter = Adapter()
    private val netViewModel: NetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setupObservers()
    }

    private fun init() = with(binding) {
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        // Наблюдаем за данными персонажей
        lifecycleScope.launch {
            netViewModel.characters.collectLatest { characters ->
                adapter.submitCharacters(characters)
                binding.titleTextView.text = "Персонажи Rick and Morty (${characters.size})"
            }
        }

        // Наблюдаем за состоянием загрузки
        lifecycleScope.launch {
            netViewModel.loading.collectLatest { isLoading ->
                if (isLoading) {
                    binding.titleTextView.text = "Загрузка персонажей..."
                }
            }
        }
    }
}