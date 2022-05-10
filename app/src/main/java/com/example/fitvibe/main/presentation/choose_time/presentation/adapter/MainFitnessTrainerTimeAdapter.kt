package com.example.fitvibe.main.presentation.choose_time.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fitvibe.R
import com.example.fitvibe.databinding.ItemMainFitnessTrainerTimeBinding

class MainFitnessTrainerTimeAdapter(
    private val listener: FitnessTrainerTimeListener
) : RecyclerView.Adapter<MainFitnessTrainerTimeAdapter.FitnessTrainerTimeViewHolder>() {

    private var timeList = listOf<String>()
    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FitnessTrainerTimeViewHolder {
        return FitnessTrainerTimeViewHolder(
            ItemMainFitnessTrainerTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FitnessTrainerTimeViewHolder, position: Int) {
        holder.bind(timeList[position], selectedPosition == position)
    }

    override fun getItemCount(): Int = timeList.size

    fun setList(list: List<String>) {
        timeList = list
    }

    inner class FitnessTrainerTimeViewHolder(
        private val binding: ItemMainFitnessTrainerTimeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(value: String, isSelected: Boolean) {
            binding.timeTextView.text = value
            initListener()
            initBackground(isSelected)
            firstEntrance(value)
        }

        private fun firstEntrance(value: String) {
            listener.onTimeClick(value)
        }

        private fun initListener() {
            itemView.setOnClickListener {
                listener.onTimeClick(binding.timeTextView.text.toString())
                notifyItemChanged(selectedPosition)
                selectedPosition = adapterPosition
                notifyItemChanged(adapterPosition)
            }
        }

        private fun initBackground(isSelected: Boolean) {
            if (isSelected) {
                itemView.background = ContextCompat.getDrawable(
                    itemView.context, R.drawable.bg_nevada_rounded_5
                )
                binding.timeTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            } else {
                itemView.background = ContextCompat.getDrawable(
                    itemView.context, R.drawable.bg_white_rounded_border_nevada_1
                )
                binding.timeTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.nevada_color))
            }
        }
    }
}

interface FitnessTrainerTimeListener {
    fun onTimeClick(value: String)
}