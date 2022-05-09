package com.example.fitvibe.registration.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fitvibe.R
import com.example.fitvibe.base.presentation.MainActivity
import com.example.fitvibe.databinding.SmsCodeFragmentBinding
import com.example.fitvibe.registration.presentation.viewmodel.SmsCodeViewModel

class SmsCodeFragment : Fragment() {

    private val viewModel: SmsCodeViewModel by viewModels()
    private lateinit var phoneString: String

    private var _binding: SmsCodeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        phoneString = arguments?.getString(PHONE) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SmsCodeFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        setFakeAccessVerification()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        with(binding) {
            phoneText.text = phoneString
        }
    }

    private fun initObservers() {
        viewModel.tickStringLiveData.observe(viewLifecycleOwner, ::handleTimerTick)
        viewModel.tickLiveData.observe(viewLifecycleOwner, ::handleTick)
    }

    private fun setFakeAccessVerification() {
        binding.insertSmsTextView.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    private fun handleTimerTick(tick: String) {
        binding.smsTimeText.text = getString(R.string.sms_code_fragment_time_text, tick)
    }

    private fun handleTick(tick: Int) {
        if (tick == 56) {
            binding.smsDigit.setText("3854")
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }


    companion object {

        const val TAG = "SMS_CODE_FRAGMENT"
        private const val PHONE = "SMS_PHONE"

        fun newInstance(phoneNumber: String): SmsCodeFragment = SmsCodeFragment().apply {
            arguments = Bundle().apply {
                putString(PHONE, phoneNumber)
            }
        }
    }
}