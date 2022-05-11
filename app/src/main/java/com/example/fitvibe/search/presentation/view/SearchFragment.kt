package com.example.fitvibe.search.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.fitvibe.R
import com.example.fitvibe.databinding.SearchFragmentBinding
import com.example.fitvibe.search.data.searchList
import com.example.fitvibe.search.presentation.adapter.OnSearchListener
import com.example.fitvibe.search.presentation.adapter.SearchAdapter
import com.example.fitvibe.utils.trainersList

class SearchFragment : Fragment(), OnSearchListener {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapter: SearchAdapter? = null
    private var filteredDataList = listOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initTextWatcher()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSearchClick(value: String) {
        val dataList = ArrayList(trainersList.filter { it.name == value || it.profession == value })
        val bundle = Bundle().apply {
            putParcelableArrayList(KEY, dataList)
        }
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SearchDataFragment>(R.id.search_container, SearchDataFragment.TAG, bundle)
            addToBackStack(SearchDataFragment.TAG)
        }
    }

    private fun initAdapter() {
        adapter = SearchAdapter(this)
        binding.requestRecyclerView.adapter = adapter
        adapter?.setList(searchList)
    }

    private fun initTextWatcher() {
        binding.editText.doAfterTextChanged { editable ->
            if (editable.isNullOrBlank()) {
                adapter?.setList(searchList)
            } else {
                adapter?.setList(
                    filteredDataList.filter {
                        it == editable.toString()
                    }
                )
            }
        }
    }

    companion object {
        const val TAG = "search_fragment"
        const val KEY = "search_fragment_key"
    }
}