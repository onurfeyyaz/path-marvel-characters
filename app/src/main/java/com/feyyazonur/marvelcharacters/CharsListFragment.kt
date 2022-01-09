package com.feyyazonur.marvelcharacters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.feyyazonur.marvelcharacters.databinding.CharsListFragmentBinding
import com.feyyazonur.marvelcharacters.network.RetrofitService

class CharsListFragment : Fragment() {

    private var _binding: CharsListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CharsListViewModel

    private val retrofitService = RetrofitService.getInstance()
    private val adapter = CharsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CharsListFragmentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            this,
            CharsListViewModelFactory(CharsRepository(retrofitService))
        )[CharsListViewModel::class.java]

        binding.charsListRecyclerview.adapter = adapter

        viewModel.charsList.observe(this, Observer {
            Log.d("CharsListFragment", it.toString())
            adapter.setCharsList(it)
        })

        viewModel.getCharacters()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}