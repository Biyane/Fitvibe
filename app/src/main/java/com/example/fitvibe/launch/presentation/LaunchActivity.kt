package com.example.fitvibe.launch.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fitvibe.databinding.ActivityLaunchBinding
import com.example.fitvibe.registration.presentation.RegistrationBaseActivity

class LaunchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaunchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initListener()
    }

    private fun initListener() {
        binding.registrationButton.setOnClickListener {
            val intent = Intent(this, RegistrationBaseActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}