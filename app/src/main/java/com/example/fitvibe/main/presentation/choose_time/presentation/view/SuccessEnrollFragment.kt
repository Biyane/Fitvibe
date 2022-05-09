package com.example.fitvibe.main.presentation.choose_time.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.fitvibe.R
import com.example.fitvibe.databinding.DialogMainEnrollTrainBinding

class SuccessEnrollFragment : DialogFragment() {

    private var _binding: DialogMainEnrollTrainBinding? = null
    private val binding get() = _binding!!

    private var enrollButtonListener: EnrollButtonListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogMainEnrollTrainBinding.inflate(layoutInflater)
        if (dialog != null && dialog?.window != null) {
            dialog!!.window!!.setBackgroundDrawableResource(R.drawable.bg_rounded_white_10)
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    fun setListener(listener: EnrollButtonListener) {
        if (isAdded) enrollButtonListener = listener
    }

    private fun initListeners() {
        binding.closeImageView.setOnClickListener {
            dismiss()
        }
        binding.enrollButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "success_enroll_fragment"
    }
}

interface EnrollButtonListener {
    fun onEnrollClick()
}