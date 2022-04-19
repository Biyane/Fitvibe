package com.example.fitvibe.registration.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fitvibe.R
import com.example.fitvibe.databinding.ActivityRegistrationBaseBinding

class RegistrationBaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.registration_activity_fragment_container,
                RegistrationSmsFragment.newInstance(),
                RegistrationSmsFragment.TAG
            ).commit()
    }
}