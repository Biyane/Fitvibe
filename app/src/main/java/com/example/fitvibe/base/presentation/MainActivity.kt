package com.example.fitvibe.base.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.example.fitvibe.R
import com.example.fitvibe.base.presentation.types.BottomNavigationTab
import com.example.fitvibe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        handleNavigation()
        binding.bottomNav.selectedItemId = R.id.profile
    }

    private fun handleNavigation() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.main -> {
                    onTabSelected(BottomNavigationTab.Main)
                }
                R.id.search -> {
                    onTabSelected(BottomNavigationTab.Search)
                }
                R.id.calendar -> {
                    onTabSelected(BottomNavigationTab.Calendar)
                }
                R.id.profile -> {
                    onTabSelected(BottomNavigationTab.Profile)
                }
            }
            true
        }
    }

    private fun onTabSelected(tab: BottomNavigationTab) {
        val currentFragment: Fragment? = getCurrentFragment()
        val newFragment: Fragment? = supportFragmentManager.findFragmentByTag(tab.getTag())
        if (currentFragment == null) {
            handleFirstEntry(tab)
            return
        }

        supportFragmentManager.commitNow {
            hide(currentFragment)
            if (newFragment == null) {
                add(R.id.fragment_container, tab.getFragment(), tab.getTag())
            } else {
                if (isSelectedTabSame(currentFragment, newFragment)) return
                show(newFragment)
            }
            setReorderingAllowed(true)
        }
    }

    private fun getCurrentFragment(): Fragment? =
        supportFragmentManager.fragments.find { fragment -> fragment.isVisible }

    private fun handleFirstEntry(tab: BottomNavigationTab) {
        supportFragmentManager.commitNow {
            add(R.id.fragment_container,tab.getFragment(), tab.getTag())
            setReorderingAllowed(true)
        }
    }

    private fun isSelectedTabSame(currentFragment: Fragment, newFragment: Fragment): Boolean {
        return newFragment == currentFragment
    }

}