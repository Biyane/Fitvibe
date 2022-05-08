package com.example.fitvibe.search.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.fitvibe.R
import com.example.fitvibe.databinding.FragmentSearchContainerBinding

class SearchContainerFragment : Fragment() {

    private var _binding: FragmentSearchContainerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchContainerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.search_container, SearchFragment(), SearchFragment.TAG)
            addToBackStack(SearchFragment.TAG)
        }
    }
}