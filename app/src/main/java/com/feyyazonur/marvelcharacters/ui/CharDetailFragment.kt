package com.feyyazonur.marvelcharacters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.feyyazonur.marvelcharacters.R
import com.feyyazonur.marvelcharacters.adapter.ComicsListAdapter
import com.feyyazonur.marvelcharacters.databinding.FragmentCharDetailBinding
import com.feyyazonur.marvelcharacters.network.RetrofitService
import com.feyyazonur.marvelcharacters.repository.CRepository
import com.feyyazonur.marvelcharacters.viewmodel.CharDetailViewModel
import com.feyyazonur.marvelcharacters.viewmodel.CharDetailViewModelFactory

class CharDetailFragment : Fragment() {

    private var _binding: FragmentCharDetailBinding? = null
    private val binding get() = _binding!!
    private val charArgsFromListAdapter by navArgs<CharDetailFragmentArgs>()
    private lateinit var viewModel: CharDetailViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = ComicsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharDetailBinding.inflate(inflater, container, false)

        toolbarConf(binding)
        adapterConf(binding)

        viewModel = ViewModelProvider(
            this,
            CharDetailViewModelFactory(CRepository(retrofitService))
        ).get(CharDetailViewModel::class.java)

        viewModel.comicsList.observe(this, Observer {
            //Log.d("CharsListDetail", "viewModel.charsList observe it: : :$it")
            adapter.setComicsList(it)
        })

        viewModel.getComics(charArgsFromListAdapter.resultsArgument.id!!) // charArgsFromListAdapter.resultsArgument.id!!

        val imagePath =
            "${charArgsFromListAdapter.resultsArgument.thumbnail!!.path}/portrait_medium.${charArgsFromListAdapter.resultsArgument.thumbnail!!.extension}"
        // Log.d("CharsList", "char detail fragment image path: : : $imagePath")

        Glide.with(requireContext())
            .load(imagePath) //"http://x.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73/portrait_small.jpg")
            .into(binding.heroImageDetail)

        binding.heroNameDetail.text = charArgsFromListAdapter.resultsArgument.name
        binding.heroDescriptionDetail.text =
            if (charArgsFromListAdapter.resultsArgument.description == "") "no description" else charArgsFromListAdapter.resultsArgument.description

        return binding.root
    }

    private fun toolbarConf(binding: FragmentCharDetailBinding) {
        binding.toolbarDetail.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbarDetail.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun adapterConf(binding: FragmentCharDetailBinding) {
        binding.comicsListRecyclerViewDetail.adapter = adapter
        binding.comicsListRecyclerViewDetail.setHasFixedSize(true)
        binding.comicsListRecyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}