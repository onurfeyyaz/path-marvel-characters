package com.feyyazonur.marvelcharacters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feyyazonur.marvelcharacters.adapter.CharsListAdapter
import com.feyyazonur.marvelcharacters.databinding.CharsListFragmentBinding
import com.feyyazonur.marvelcharacters.network.RetrofitService
import com.feyyazonur.marvelcharacters.repository.CRepository
import com.feyyazonur.marvelcharacters.viewmodel.CharsListViewModel
import com.feyyazonur.marvelcharacters.viewmodel.CharsListViewModelFactory

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

        viewModel = ViewModelProvider(
            this,
            CharsListViewModelFactory(CRepository(retrofitService))
        ).get(CharsListViewModel::class.java)

        val adapter = CharsListAdapter()
        binding.charsListRecyclerview.adapter = adapter
        binding.charsListRecyclerview.setHasFixedSize(true)
        binding.charsListRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        binding.charsListRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.charsListRecyclerview.canScrollVertically(1)) {
                    viewModel.limitUp()
                }
            }
        })

        viewModel.charsList.observe(this, Observer {
            // Log.d("CharsListFragment", "viewModel.charsList observe it: : :$it")
            adapter.setCharsList(it)
        })

        viewModel.getCharacters(viewModel.limit, viewModel.charOffSet)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}