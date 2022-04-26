package com.example.fitvibe.profile.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.fitvibe.databinding.ProfileEditFragmentBinding

class ProfileEditFragment : Fragment() {

    private var _binding: ProfileEditFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileEditFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListener() {
        binding.saveDataButton.setOnClickListener {
            setFragmentResult("request", bundleOf("" to 1))
        }
    }

    companion object {
        const val TAG = "profile_edit_fragment"

        fun newInstance(): ProfileEditFragment = ProfileEditFragment()
    }
}