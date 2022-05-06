package com.example.fitvibe.main.presentation.trainer.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitvibe.databinding.ItemMainFitnessTrainerDayBinding

class MainFitnessTrainerDayAdapter(
    private val listener: FitnessTrainerDayListener
) : RecyclerView.Adapter<MainFitnessTrainerDayAdapter.FitnessTrainerDayViewHolder>() {

    private var dayList = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FitnessTrainerDayViewHolder {
        return FitnessTrainerDayViewHolder(
            ItemMainFitnessTrainerDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FitnessTrainerDayViewHolder, position: Int) {
        holder.bind(dayList[position])
    }

    override fun getItemCount(): Int = dayList.size

    fun setList(list: List<String>) {
        dayList = list
    }

    inner class FitnessTrainerDayViewHolder(
        private val binding: ItemMainFitnessTrainerDayBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            initListener()
        }

        fun bind(value: String) {
            binding.dayTextView.text = value
        }

        private fun initListener() {
            itemView.setOnClickListener {
                listener.onDayClick(binding.dayTextView.text.toString())
            }
        }
    }
}

interface FitnessTrainerDayListener {
    fun onDayClick(value: String)
}