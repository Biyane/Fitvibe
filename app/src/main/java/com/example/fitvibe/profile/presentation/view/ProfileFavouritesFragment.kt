package com.example.fitvibe.profile.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.fitvibe.R
import com.example.fitvibe.databinding.FragmentProfileFavouritesBinding
import com.example.fitvibe.main.presentation.choose_time.presentation.view.MainChooseTimeFragment
import com.example.fitvibe.main.presentation.trainers_list.adapter.MainTrainersAdapter
import com.example.fitvibe.main.presentation.trainers_list.adapter.MainTrainersListener
import com.example.fitvibe.main.presentation.trainers_list.view.MainTrainersListFragment
import com.example.fitvibe.utils.Trainer
import com.example.fitvibe.utils.trainersList

class ProfileFavouritesFragment : Fragment(), MainTrainersListener {

    private var _binding: FragmentProfileFavouritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileFavouritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(trainer: Trainer) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            val bundle = Bundle().apply { putParcelable(MainTrainersListFragment.TRAINER, trainer) }
            replace<MainChooseTimeFragment>(R.id.container, MainChooseTimeFragment.TAG, bundle)
            addToBackStack(MainChooseTimeFragment.TAG)
        }
    }

    private fun initAdapter() {
        val adapter = MainTrainersAdapter(this)
        binding.favouritesRecyclerView.adapter = adapter
        adapter.setList(trainersList.filter { it.isFavourite })
    }


    private fun initListeners() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        const val KEY = "profile_favourites_fragment_key"
        const val TAG = "profile_favourites_fragment"
    }
}