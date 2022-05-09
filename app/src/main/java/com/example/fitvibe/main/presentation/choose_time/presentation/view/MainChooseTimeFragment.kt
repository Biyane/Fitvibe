package com.example.fitvibe.main.presentation.choose_time.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.fitvibe.R
import com.example.fitvibe.databinding.FragmentMainFitnessTrainerBinding
import com.example.fitvibe.main.presentation.choose_time.data.dayList
import com.example.fitvibe.main.presentation.choose_time.data.timeList
import com.example.fitvibe.main.presentation.choose_time.presentation.adapter.FitnessTrainerDayListener
import com.example.fitvibe.main.presentation.choose_time.presentation.adapter.FitnessTrainerTimeListener
import com.example.fitvibe.main.presentation.choose_time.presentation.adapter.MainFitnessTrainerDayAdapter
import com.example.fitvibe.main.presentation.choose_time.presentation.adapter.MainFitnessTrainerTimeAdapter
import com.example.fitvibe.main.presentation.trainers_list.view.MainTrainersListFragment
import com.example.fitvibe.utils.Trainer
import com.example.fitvibe.utils.trainersList

class MainChooseTimeFragment : Fragment(), FitnessTrainerDayListener,
    FitnessTrainerTimeListener, EnrollButtonListener {

    private var _binding: FragmentMainFitnessTrainerBinding? = null
    private val binding get() = _binding!!

    private var trainer: Trainer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trainer = arguments?.getParcelable(MainTrainersListFragment.TRAINER)
    }

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

    override fun onEnrollClick() {

    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun initViews() {
        if (trainer == null) return
        with(binding) {
            fitnessNameTextView.text = trainer?.profession
            trainerNameTextView.text = trainer?.name
            trainerStatusTextView.text = if (trainer?.status == true) "Онлайн" else "Оффлайн"
            trainDurationTextView.text = trainer?.duration
            fitnessDescription.text = trainer?.description
            favouritesImageView.setImageResource(if (trainer?.isFavourite == true) R.drawable.ic_favourites_selected else R.drawable.ic_favourite_unselected)
            setProfile()
            setMainImage()
        }
    }

    private fun setProfile() {
        Glide.with(this)
            .load(trainer?.image)
            .into(binding.profileImageView)
    }

    private fun setMainImage() {
        Glide.with(this)
            .load(trainer?.fitnessPicture)
            .into(binding.fitnessImageView)
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

        binding.favouritesImageView.setOnClickListener {
            trainersList[trainersList.indexOf(trainer)].isFavourite =
                !trainersList[trainersList.indexOf(trainer)].isFavourite

            binding.favouritesImageView.setImageResource(if (trainersList[trainersList.indexOf(trainer)].isFavourite) R.drawable.ic_favourites_selected else R.drawable.ic_favourite_unselected)
        }
    }

    private fun showAlertDialog() {
        val dialogFragment = SuccessEnrollFragment()
        dialogFragment.show(childFragmentManager, SuccessEnrollFragment.TAG)
    }

    companion object {
        const val TAG = "main_fitness_trainer_fragment"
    }
}