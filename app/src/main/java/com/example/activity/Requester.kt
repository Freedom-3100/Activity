package com.example.activity

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String
)

@Serializable
data class ResultsResponse<T>(
    val results: List<T>
)


class Requester {
    private val client = HttpClient(OkHttp.create()) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getData(): ResultsResponse<Character> {
        println("Делаем запрос к API...")
        return client.get("https://rickandmortyapi.com/api/character").body()
    }


}

class NetViewModel : ViewModel() {
    private val requester = Requester()
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters.asStateFlow()
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()
    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = requester.getData()
                _characters.value = response.results
                println("Данные загружены: ${response.results.size} персонажей")
            } catch (e: Exception) {
                println("Ошибка загрузки: ${e.message}")
                _characters.value = emptyList()
            } finally {
                _loading.value = false
            }
        }
    }
}