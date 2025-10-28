package com.example.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.activity.databinding.CardItemBinding
import com.example.activity.databinding.CardItemTitleBinding

class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_ITEM = 0
        private const val TYPE_TITLE = 1
    }

    private val cardList = ArrayList<Card>()

    override fun getItemViewType(position: Int): Int {
        return when (cardList[position]) {
            is Card.Content -> TYPE_ITEM
            is Card.Title -> TYPE_TITLE
        }
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
        when (val card = cardList[position]) {
            is Card.Content -> (holder as ContentViewHolder).bind(card)
            is Card.Title -> (holder as TitleViewHolder).bind(card)
        }
    }

    override fun getItemCount(): Int = cardList.size

    fun submitCharacters(characters: List<Character>) {
        cardList.clear()

        // Добавляем заголовок
        cardList.add(Card.Title("Персонажи Rick and Morty", true))

        // Преобразуем Character в Card.Content
        characters.forEach { character ->
            cardList.add(
                Card.Content(
                    id = character.id,
                    imageUrl = character.image,
                    title = character.name,
                    description = "${character.species} • ${character.status}",
                    status = character.status
                )
            )
        }

        notifyDataSetChanged()
    }

    fun clear() {
        cardList.clear()
        notifyDataSetChanged()
    }

    // ViewHolder для контентных карточек
    class ContentViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card.Content) = with(binding) {
            // Загружаем изображение с помощью Coil
            imageView.load(card.imageUrl) {
                crossfade(true)
                placeholder(android.R.drawable.ic_menu_gallery)
                error(android.R.drawable.ic_menu_report_image)
            }

            textView.text = card.title
            textViewDescription.text = card.description

            // Цвет статуса
            when (card.status.lowercase()) {
                "alive" -> textViewDescription.setTextColor(android.graphics.Color.GREEN)
                "dead" -> textViewDescription.setTextColor(android.graphics.Color.RED)
                else -> textViewDescription.setTextColor(android.graphics.Color.GRAY)
            }
        }
    }

    // ViewHolder для заголовков
    class TitleViewHolder(private val binding: CardItemTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card.Title) = with(binding) {
            titleTextView.text = card.text

            if (card.isMainTitle) {
                titleTextView.textSize = 20f
                titleTextView.setTextColor(android.graphics.Color.BLACK)
            } else {
                titleTextView.textSize = 16f
                titleTextView.setTextColor(android.graphics.Color.GRAY)
            }
        }
    }
}