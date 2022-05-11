package com.example.fitvibe.launch.presentation

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitvibe.base.presentation.MainActivity
import com.example.fitvibe.databinding.ActivityLaunchBinding
import com.example.fitvibe.registration.presentation.RegistrationBaseActivity
import com.example.fitvibe.registration.presentation.SmsCodeFragment
import org.koin.android.ext.android.inject

class LaunchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLaunchBinding

    private val sharedPref by inject<SharedPreferences>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initListener()
        initRegistration()
    }

    private fun initListener() {
        binding.registrationButton.setOnClickListener {
            val intent = Intent(this, RegistrationBaseActivity::class.java)
            startActivity(intent)
            finish()
        }
//        binding.skipButton.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
    }

    private fun initRegistration() {
        if (!sharedPref.getString(SmsCodeFragment.AUTH_KEY, null).isNullOrBlank()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}