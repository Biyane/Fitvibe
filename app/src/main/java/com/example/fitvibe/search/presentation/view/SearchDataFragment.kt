package com.example.fitvibe.search.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.example.fitvibe.databinding.FragmentSearchDataBinding
import com.example.fitvibe.main.presentation.trainers_list.adapter.MainTrainersAdapter
import com.example.fitvibe.main.presentation.trainers_list.adapter.MainTrainersListener
import com.example.fitvibe.utils.Trainer

class SearchDataFragment : Fragment(), MainTrainersListener {

    private var _binding: FragmentSearchDataBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchDataBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initListener()
    }

    override fun onClick(trainer: Trainer) {

    }

    private fun initAdapter() {
        val adapter = MainTrainersAdapter(this)

    }

    private fun initListener() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "search_data_fragment"
    }
}