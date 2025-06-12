package com.eulergomees.mybooks.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eulergomees.mybooks.R
import com.eulergomees.mybooks.databinding.FragmentDetailsBinding
import com.eulergomees.mybooks.helper.BookConstant
import com.eulergomees.mybooks.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()

    private var bookId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        setListeners()
        setObservers()

        bookId = arguments?.getInt(BookConstant.KEY.BOOK_ID) ?: 0
        viewModel.getBookById(bookId)

        return binding.root
    }

    private fun setListeners() {
        binding.imageviewBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setObservers() {
        viewModel.book.observe(viewLifecycleOwner) {
            binding.textviewTitle.text = it.title
            binding.textviewAuthorValue.text = it.author
            binding.textviewGenreValue.text = it.genre
            binding.checkboxFavorite.isChecked = it.favorite

            setGenreBackgroud(it.genre)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setGenreBackgroud(genre: String) {
        when (genre) {
            "Terror" -> {
                binding.textviewGenreValue.setBackgroundResource(R.drawable.rouded_label_red)
            }

            "Fantasia" -> {
                binding.textviewGenreValue.setBackgroundResource(R.drawable.rouded_label_fantasy)
            }

            "Ficção" -> {
                binding.textviewGenreValue.setBackgroundResource(R.drawable.rouded_label_scifi)
            }

            "Cyberpunk" -> {
                binding.textviewGenreValue.setBackgroundResource(R.drawable.rouded_label_scifi)
            }

            else -> {
                binding.textviewGenreValue.setBackgroundResource(R.drawable.rouded_label_teal)
            }
        }
    }
}