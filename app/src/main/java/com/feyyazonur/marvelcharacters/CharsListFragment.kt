package com.feyyazonur.marvelcharacters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.feyyazonur.marvelcharacters.databinding.CharsListFragmentBinding
import com.feyyazonur.marvelcharacters.network.RetrofitService

class CharsListFragment : Fragment() {

    private var _binding: CharsListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CharsListViewModel

    private val retrofitService = RetrofitService.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CharsListFragmentBinding.inflate(inflater, container, false)

        val adapter = CharsListAdapter()
        binding.charsListRecyclerview.adapter = adapter
        binding.charsListRecyclerview.setHasFixedSize(true)
        binding.charsListRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(
            this,
            CharsListViewModelFactory(CharsRepository(retrofitService))
        ).get(CharsListViewModel::class.java)


        viewModel.charsList.observe(this, Observer {
            Log.d("CharsListFragment", "viewModel.charsList observe it: : :$it")
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