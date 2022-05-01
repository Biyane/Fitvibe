package com.example.fitvibe.profile.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.fitvibe.R
import com.example.fitvibe.databinding.ProfileContainerFragmentBinding

class ProfileContainerFragment : Fragment() {

    private var _binding: ProfileContainerFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileContainerFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ProfileFragment>(R.id.container)
            addToBackStack(null)
        }
    }

}