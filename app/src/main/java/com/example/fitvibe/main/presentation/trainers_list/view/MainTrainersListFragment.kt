package com.example.fitvibe.main.presentation.trainers_list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.fitvibe.R
import com.example.fitvibe.databinding.FragmentMainTrainersBinding
import com.example.fitvibe.main.presentation.choose_time.presentation.view.MainChooseTimeFragment
import com.example.fitvibe.main.presentation.trainers_list.adapter.MainTrainersAdapter
import com.example.fitvibe.main.presentation.trainers_list.adapter.MainTrainersListener


class MainTrainersListFragment : Fragment(), MainTrainersListener {

    private var _binding: FragmentMainTrainersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainTrainersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initToolbar()
        initListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(name: String) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<MainChooseTimeFragment>(R.id.fragment_main_container, MainChooseTimeFragment.TAG)
            addToBackStack(MainChooseTimeFragment.TAG)
        }
    }

    private fun initAdapter() {
        binding.trainersListRecyclerView.adapter = MainTrainersAdapter(this)
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun initListener() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        const val TAG = "main_trainers_fragment"
    }
}