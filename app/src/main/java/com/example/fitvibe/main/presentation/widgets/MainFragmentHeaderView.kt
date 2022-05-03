package com.example.fitvibe.main.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fitvibe.databinding.MainFragmentHeaderViewBinding

internal class MainFragmentHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding: MainFragmentHeaderViewBinding =
        MainFragmentHeaderViewBinding.inflate(LayoutInflater.from(context), this)
}