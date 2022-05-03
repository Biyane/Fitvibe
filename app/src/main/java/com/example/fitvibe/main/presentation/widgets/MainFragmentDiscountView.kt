package com.example.fitvibe.main.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.fitvibe.databinding.MainFragmentDiscountViewBinding

internal class MainFragmentDiscountView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet) {

    private val binding: MainFragmentDiscountViewBinding =
        MainFragmentDiscountViewBinding.inflate(LayoutInflater.from(context), this, true)

}