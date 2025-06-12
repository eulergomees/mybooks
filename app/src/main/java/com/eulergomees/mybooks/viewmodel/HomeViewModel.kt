package com.eulergomees.mybooks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eulergomees.mybooks.entity.BookEntity
import com.eulergomees.mybooks.repository.BookRepository

class HomeViewModel : ViewModel() {

    private val _books = MutableLiveData<List<BookEntity>>()
    val books: LiveData<List<BookEntity>> = _books

    private val repository = BookRepository()

    fun getAllBooks() {
        _books.value = repository.getAllBooks()
    }
}