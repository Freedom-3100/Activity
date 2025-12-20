package com.example.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.activity.data.CharacterEntity
import com.example.activity.databinding.CardItemBinding
import com.example.activity.databinding.CardItemTitleBinding

class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_ITEM = 0
        private const val TYPE_TITLE = 1
    }

    // Изменяем тип списка на Character вместо Card
    private val characterList = ArrayList<CharacterEntity>()

    override fun getItemViewType(position: Int): Int {
        // Для первой позиции - заголовок, для остальных - контент
        return if (position == 0) TYPE_TITLE else TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ITEM -> {
                val binding = CardItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ContentViewHolder(binding)
            }
            TYPE_TITLE -> {
                val binding = CardItemTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TitleViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ContentViewHolder -> {
                // Для контентных элементов используем position - 1 (т.к. первая позиция - заголовок)
                val character = characterList[position - 1]
                holder.bind(character)
            }
            is TitleViewHolder -> {
                holder.bind("Персонажи Rick and Morty")
            }
        }
    }

    override fun getItemCount(): Int = characterList.size + 1 // +1 для заголовка

    fun submitCharacters(characters: List<CharacterEntity>) {
        characterList.clear()
        characterList.addAll(characters)
        notifyDataSetChanged()
    }

    fun clear() {
        characterList.clear()
        notifyDataSetChanged()
    }

    // ViewHolder для контентных карточек (теперь принимает Character)
    class ContentViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: CharacterEntity) = with(binding) {
            // Загружаем изображение с помощью Coil
            imageView.load(character.image) {
                crossfade(true)
                placeholder(android.R.drawable.ic_menu_gallery)
                error(android.R.drawable.ic_menu_report_image)
            }

            textView.text = character.name
            textViewDescription.text = "${character.species} • ${character.status}"

            // Цвет статуса
            when (character.status.lowercase()) {
                "alive" -> textViewDescription.setTextColor(android.graphics.Color.GREEN)
                "dead" -> textViewDescription.setTextColor(android.graphics.Color.RED)
                else -> textViewDescription.setTextColor(android.graphics.Color.GRAY)
            }
        }
    }

    // ViewHolder для заголовков
    class TitleViewHolder(private val binding: CardItemTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(titleText: String) = with(binding) {
            titleTextView.text = titleText
            titleTextView.textSize = 20f
            titleTextView.setTextColor(android.graphics.Color.BLACK)
        }
    }
}