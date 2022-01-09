package com.feyyazonur.marvelcharacters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.feyyazonur.marvelcharacters.databinding.FragmentCharDetailBinding

class CharDetailFragment : Fragment() {

    private var _binding: FragmentCharDetailBinding? = null
    private val binding get() = _binding!!

    private val charArgsFromListAdapter by navArgs<CharDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharDetailBinding.inflate(inflater, container, false)

        binding.toolbarDetail.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbarDetail.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        val imagePath =
            "${charArgsFromListAdapter.resultsArgument.thumbnail!!.path}/portrait_small.${charArgsFromListAdapter.resultsArgument.thumbnail!!.extension}"
        Log.d("CharsList", "char detail fragment image path: : : $imagePath")

        Glide.with(requireContext())
            .load("http://x.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73/portrait_small.jpg") //"http://x.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73/portrait_small.jpg")
            .into(binding.heroImageDetail)

        binding.heroNameDetail.text = charArgsFromListAdapter.resultsArgument.name
        binding.heroDescriptionDetail.text =
            if (charArgsFromListAdapter.resultsArgument.description == "") "no description" else charArgsFromListAdapter.resultsArgument.description

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}