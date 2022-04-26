package com.example.fitvibe.profile.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import com.example.fitvibe.R
import com.example.fitvibe.databinding.ProfileFragmentBinding
import com.example.fitvibe.profile.presentation.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        setFragmentResultListener("request") { requestKey: String, bundle: Bundle ->  

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListener() {
        binding.myDataTextView.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, ProfileEditFragment.newInstance(), ProfileEditFragment.TAG)
                setReorderingAllowed(true)
                addToBackStack(ProfileEditFragment.TAG)
            }
        }
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }

}