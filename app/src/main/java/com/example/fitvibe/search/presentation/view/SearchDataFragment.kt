package com.example.fitvibe.search.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.fitvibe.R
import com.example.fitvibe.databinding.FragmentSearchDataBinding
import com.example.fitvibe.main.presentation.choose_time.presentation.view.MainChooseTimeFragment
import com.example.fitvibe.main.presentation.trainers_list.adapter.MainTrainersAdapter
import com.example.fitvibe.main.presentation.trainers_list.adapter.MainTrainersListener
import com.example.fitvibe.main.presentation.trainers_list.view.MainTrainersListFragment
import com.example.fitvibe.utils.Trainer

class SearchDataFragment : Fragment(), MainTrainersListener {

    private var _binding: FragmentSearchDataBinding? = null
    private val binding get() = _binding!!

    private var dataList: ArrayList<Trainer>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataList = arguments?.getParcelableArrayList(SearchFragment.KEY)
    }

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
        val bundle = Bundle().apply {
            putParcelable(MainTrainersListFragment.TRAINER, trainer)
        }
        parentFragmentManager.commit {
            replace<MainChooseTimeFragment>(R.id.search_container, MainChooseTimeFragment.TAG, bundle)
            addToBackStack(MainChooseTimeFragment.TAG)
        }
    }

    private fun initAdapter() {
        val adapter = MainTrainersAdapter(this)
        dataList?.let { adapter.setList(it) }
        binding.searchDataRecyclerView.adapter = adapter
    }

    private fun initListener() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
        binding.myToolbar.setNavigationOnClickListener {
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