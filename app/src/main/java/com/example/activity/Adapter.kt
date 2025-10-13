package com.example.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Card
import androidx.recyclerview.widget.RecyclerView
import com.example.activity.databinding.CardItemBinding

class Adapter: RecyclerView.Adapter<Adapter.CardHolder>() {

    val cardList = ArrayList<Card>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return CardHolder(view)
    }

    override fun onBindViewHolder(
        holder: CardHolder,
        position: Int
    ) {
        holder.bind(cardList[position])
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    // Метод для добавления данных
    fun addCard(card: Card) {
        cardList.add(card)
        notifyItemInserted(cardList.size - 1)
    }

    // Метод для добавления списка карточек
    fun addAllCards(cards: List<Card>) {
        cardList.addAll(cards)
        notifyDataSetChanged()
    }

    class CardHolder(item : View): RecyclerView.ViewHolder(item) {
        val binding = CardItemBinding.bind(item)
        fun bind(card: Card) = with(binding)
        {
            imageView.setImageResource(card.image)
            textView.text = card.title
            textViewDescription.text = card.description
        }

    }

}