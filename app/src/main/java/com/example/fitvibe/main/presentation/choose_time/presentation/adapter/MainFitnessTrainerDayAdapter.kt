package com.example.fitvibe.main.presentation.choose_time.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fitvibe.R
import com.example.fitvibe.databinding.ItemMainFitnessTrainerDayBinding

class MainFitnessTrainerDayAdapter(
    private val listener: FitnessTrainerDayListener
) : RecyclerView.Adapter<MainFitnessTrainerDayAdapter.FitnessTrainerDayViewHolder>() {

    private var dayList = listOf<String>()
    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FitnessTrainerDayViewHolder {
        return FitnessTrainerDayViewHolder(
            ItemMainFitnessTrainerDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FitnessTrainerDayViewHolder, position: Int) {
        holder.bind(dayList[position], position == selectedPosition)
    }

    override fun getItemCount(): Int = dayList.size

    fun setList(list: List<String>) {
        dayList = list
    }

    inner class FitnessTrainerDayViewHolder(
        private val binding: ItemMainFitnessTrainerDayBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(value: String, isSelected: Boolean) {
            binding.dayTextView.text = value
            initListener()
            initBackground(isSelected)
        }

        private fun initListener() {
            itemView.setOnClickListener {
                listener.onDayClick(binding.dayTextView.text.toString())
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
                binding.dayTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            } else {
                itemView.background = ContextCompat.getDrawable(
                    itemView.context, R.drawable.bg_white_rounded_border_nevada_1
                )
                binding.dayTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.nevada_color))
            }
        }
    }
}

interface FitnessTrainerDayListener {
    fun onDayClick(value: String)
}