package com.example.fitvibe.main.presentation.choose_time.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fitvibe.databinding.FragmentMainFitnessTrainerBinding
import com.example.fitvibe.main.presentation.choose_time.data.dayList
import com.example.fitvibe.main.presentation.choose_time.data.timeList
import com.example.fitvibe.main.presentation.choose_time.presentation.adapter.FitnessTrainerDayListener
import com.example.fitvibe.main.presentation.choose_time.presentation.adapter.FitnessTrainerTimeListener
import com.example.fitvibe.main.presentation.choose_time.presentation.adapter.MainFitnessTrainerDayAdapter
import com.example.fitvibe.main.presentation.choose_time.presentation.adapter.MainFitnessTrainerTimeAdapter

class MainChooseTimeFragment : Fragment(), FitnessTrainerDayListener,
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

    }

    override fun onTimeClick(value: String) {

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
        binding.dayRecyclerView.adapter = dayAdapter
        dayAdapter.setList(dayList)

        val timeAdapter = MainFitnessTrainerTimeAdapter(this)
        binding.timeRecyclerView.adapter = timeAdapter
        binding.timeRecyclerView.layoutManager = GridLayoutManager(context, 5)
        timeAdapter.setList(timeList)
    }


    private fun initListeners() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
        binding.enrollButton.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        SuccessEnrollFragment().show(childFragmentManager, SuccessEnrollFragment.TAG)
    }

    companion object {
        const val TAG = "main_fitness_trainer_fragment"
    }
}