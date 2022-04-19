package com.example.fitvibe.registration.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitvibe.R
import com.example.fitvibe.databinding.RegistrationSmsFragmentBinding
import com.example.fitvibe.registration.presentation.viewmodel.RegistrationSmsViewModel
import com.example.fitvibe.utils.phonemask.PhoneMaskDelegate
import com.example.fitvibe.utils.phonemask.PhoneMaskDelegateImpl


class RegistrationSmsFragment : Fragment(),
    PhoneMaskDelegate by PhoneMaskDelegateImpl() {

    private lateinit var viewModel: RegistrationSmsViewModel

    private var _binding: RegistrationSmsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegistrationSmsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEditText()
        initListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initEditText() {
        setPhoneMask(binding.phoneEditText)
    }

    private fun initListeners() {
        binding.smsButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.registration_activity_fragment_container,
                    SmsCodeFragment.newInstance(getPhoneNumber()),
                    SmsCodeFragment.TAG
                )
                .addToBackStack(SmsCodeFragment.TAG)
                .commit()
        }
    }

    private fun getPhoneNumber(): String {
        return binding.phoneEditText.text.toString().replace("\\p{P}".toRegex(), "")
    }

    companion object {
        const val TAG = "REGISTRATION_SMS_FRAGMENT"

        fun newInstance() = RegistrationSmsFragment()
    }

}