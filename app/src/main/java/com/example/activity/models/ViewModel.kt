package com.example.activity.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.activity.data.CharacterRepository
import com.example.activity.Requester
import com.example.activity.data.CharacterEntity
import com.example.activity.database.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.sql.DriverManager.println
import java.util.Collections.emptyList

class NetViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application)
    private val characterDao = database.characterDao()
    private val requester = Requester()
    private val repository = CharacterRepository(characterDao, requester)
    private val _characters = MutableStateFlow<List<CharacterEntity>>(emptyList())
    val characters: StateFlow<List<CharacterEntity>> = _characters.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    init {
        loadData()
        viewModelScope.launch {
            repository.getAllCharacters().collect { charactersList ->
                _characters.value = charactersList
            }
        }
    }

    fun loadData() {
        viewModelScope.launch {
            _loading.value = true
            try {
                repository.refreshData()
            } catch (e: Exception) {
                println("Ошибка загрузки: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }
}