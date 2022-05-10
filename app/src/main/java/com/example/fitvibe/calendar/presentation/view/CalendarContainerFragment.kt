package com.example.fitvibe.calendar.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.fitvibe.R

class CalendarContainerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.commit {
            replace(R.id.calendar_container, CalendarFragment(), CalendarFragment.TAG)
            addToBackStack(CalendarFragment.TAG)
        }
    }

    companion object {
        const val TAG = "calendar_container_fragment"
    }
}