package com.example.activity

sealed class Card {
    data class Content(
        val id: Int,
        val imageUrl: String, // Теперь URL вместо resource ID
        val title: String,
        val description: String,
        val status: String = ""
    ) : Card()

    data class Title(
        val text: String,
        val isMainTitle: Boolean = false
    ) : Card()
}