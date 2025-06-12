package com.eulergomees.mybooks.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.eulergomees.mybooks.R
import com.eulergomees.mybooks.databinding.ItemBookBinding
import com.eulergomees.mybooks.entity.BookEntity
import com.eulergomees.mybooks.ui.listener.BookListener

class BookViewHolder(private val item: ItemBookBinding, private val listener: BookListener) :
    RecyclerView.ViewHolder(item.root) {

    fun bind(book: BookEntity) {
        item.textviewTitle.text = book.title
        item.textviewAuthor.text = book.author
        item.textviewGenre.text = book.genre
        item.textviewTitle.setOnClickListener { listener.onClick(book.id) }

        setGenreBackgroud(book.genre)
        updateFavoriteIcon(book.favorite)

    }

    private fun updateFavoriteIcon(favorite: kotlin.Boolean) {
        if (favorite) {
            item.imageviewFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            item.imageviewFavorite.setImageResource(R.drawable.ic_favorite_empty)
        }
    }

    private fun setGenreBackgroud(genre: String) {
        when (genre) {
            "Terror" -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rouded_label_red)
            }

            "Fantasia" -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rouded_label_fantasy)
            }

            "Ficção" -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rouded_label_scifi)
            }

            "Cyberpunk" -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rouded_label_scifi)
            }

            else -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rouded_label_teal)
            }
        }
    }
}