package com.example.fitvibe.registration.presentation

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitvibe.R
import com.example.fitvibe.databinding.SmsCodeFragmentBinding
import com.example.fitvibe.registration.presentation.viewmodel.SmsCodeViewModel
import timber.log.Timber

class SmsCodeFragment : Fragment() {

    private lateinit var viewModel: SmsCodeViewModel
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