package com.example.fitvibe.main.presentation.trainer.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.fitvibe.R
import com.example.fitvibe.databinding.FragmentMainFitnessTrainerBinding
import com.example.fitvibe.main.presentation.trainer.data.dayList
import com.example.fitvibe.main.presentation.trainer.data.timeList
import com.example.fitvibe.main.presentation.trainer.presentation.adapter.FitnessTrainerDayListener
import com.example.fitvibe.main.presentation.trainer.presentation.adapter.FitnessTrainerTimeListener
import com.example.fitvibe.main.presentation.trainer.presentation.adapter.MainFitnessTrainerDayAdapter
import com.example.fitvibe.main.presentation.trainer.presentation.adapter.MainFitnessTrainerTimeAdapter

class MainFitnessTrainerFragment : Fragment(), FitnessTrainerDayListener,
    FitnessTrainerTimeListener {

    private var _binding: FragmentMainFitnessTrainerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainFitnessTrainerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViews()
        initAdapters()
        initListeners()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDayClick(value: String) {
        TODO("Not yet implemented")
    }

    override fun onTimeClick(value: String) {
        TODO("Not yet implemented")
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun initViews() {

    }

    private fun initAdapters() {
        val dayAdapter = MainFitnessTrainerDayAdapter(this)
        dayAdapter.setList(dayList)
        binding.dayRecyclerView.adapter = dayAdapter

        val timeAdapter = MainFitnessTrainerTimeAdapter(this)
        timeAdapter.setList(timeList)
        binding.timeRecyclerView.adapter = timeAdapter
    }


    private fun initListeners() {
        binding.imageContainerConstraint.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<MainFitnessTrainerDescriptionFragment>(
                    R.id.fragment_main_container,
                    MainFitnessTrainerDescriptionFragment.TAG
                )
                addToBackStack(MainFitnessTrainerDescriptionFragment.TAG)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        const val TAG = "main_fitness_trainer_fragment"
    }
}