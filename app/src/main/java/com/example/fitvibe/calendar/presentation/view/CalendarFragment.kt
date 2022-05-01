package com.example.fitvibe.calendar.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitvibe.R
import com.example.fitvibe.calendar.presentation.viewmodel.CalendarViewModel

class CalendarFragment : Fragment() {

    private lateinit var viewModel: CalendarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.calendar_fragment, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("hello", "CalendarFragmentDestroyed")
    }

    companion object {
        fun newInstance() = CalendarFragment()
    }

}