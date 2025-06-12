package com.eulergomees.mybooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eulergomees.mybooks.R
import com.eulergomees.mybooks.databinding.FragmentHomeBinding
import com.eulergomees.mybooks.helper.BookConstant
import com.eulergomees.mybooks.ui.adapter.BookAdapter
import com.eulergomees.mybooks.ui.listener.BookListener
import com.eulergomees.mybooks.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val adapter: BookAdapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recyclerviewBooks.layoutManager = LinearLayoutManager(context)
        //Adapter
        binding.recyclerviewBooks.adapter = adapter

        attachListener()

        viewModel.getAllBooks()
        setObserves()

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObserves() {
        viewModel.books.observe(viewLifecycleOwner) {
            adapter.updateBooks(it)
        }
    }

    private fun attachListener() {
        adapter.attachListener(object : BookListener{
            override fun onClick(id: Int) {
                val bundle = Bundle()
                bundle.putInt(BookConstant.KEY.BOOK_ID, id)
               findNavController().navigate(R.id.navigation_details, bundle)
            }
        })
    }
}