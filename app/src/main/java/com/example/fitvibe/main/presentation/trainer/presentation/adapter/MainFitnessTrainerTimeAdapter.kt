package com.example.fitvibe.main.presentation.trainer.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitvibe.databinding.ItemMainFitnessTrainerTimeBinding

class MainFitnessTrainerTimeAdapter(
    private val listener: FitnessTrainerTimeListener
) : RecyclerView.Adapter<MainFitnessTrainerTimeAdapter.FitnessTrainerTimeViewHolder>() {

    private var timeList = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FitnessTrainerTimeViewHolder {
        return FitnessTrainerTimeViewHolder(
            ItemMainFitnessTrainerTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FitnessTrainerTimeViewHolder, position: Int) {
        holder.bind(timeList[position])
    }

    override fun getItemCount(): Int = timeList.size

    fun setList(list: List<String>) {
        timeList = list
    }

    inner class FitnessTrainerTimeViewHolder(
        private val binding: ItemMainFitnessTrainerTimeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            initListener()
        }

        fun bind(value: String) {
            binding.timeTextView.text = value
        }

        private fun initListener() {
            itemView.setOnClickListener {
                listener.onTimeClick(binding.timeTextView.text.toString())
            }
        }
    }
}

interface FitnessTrainerTimeListener {
    fun onTimeClick(value: String)
}