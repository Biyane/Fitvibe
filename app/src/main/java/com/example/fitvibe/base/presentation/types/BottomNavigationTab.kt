package com.example.fitvibe.base.presentation.types

import androidx.fragment.app.Fragment
import com.example.fitvibe.calendar.presentation.view.CalendarFragment
import com.example.fitvibe.main.presentation.main.view.MainContainerFragment
import com.example.fitvibe.profile.presentation.view.ProfileContainerFragment
import com.example.fitvibe.search.presentation.view.SearchFragment

sealed class BottomNavigationTab {

    object Main : BottomNavigationTab() {

        private const val MAIN_TAG = "main_tag"

        override fun getTag(): String = MAIN_TAG

        override fun getFragment(): Fragment = MainContainerFragment()

    }

    object Search : BottomNavigationTab() {

        private const val SEARCH_TAG = "search_tag"

        override fun getTag(): String  = SEARCH_TAG

        override fun getFragment(): Fragment = SearchFragment()


    }

    object Calendar : BottomNavigationTab() {

        private const val CALENDAR_TAG = "calendar_tag"

        override fun getTag(): String  = CALENDAR_TAG

        override fun getFragment(): Fragment = CalendarFragment()

    }

    object Profile : BottomNavigationTab() {

        private const val PROFILE_TAG = "profile_tag"

        override fun getTag(): String = PROFILE_TAG

        override fun getFragment(): Fragment = ProfileContainerFragment()

    }

    abstract fun getTag(): String
    abstract fun getFragment(): Fragment
}