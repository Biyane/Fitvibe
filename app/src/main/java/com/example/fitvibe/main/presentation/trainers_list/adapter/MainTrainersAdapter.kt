package com.example.fitvibe.main.presentation.trainers_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitvibe.databinding.ItemMainTrainersBinding

class MainTrainersAdapter(
    private val listener: MainTrainersListener
) : RecyclerView.Adapter<MainTrainersAdapter.TrainersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainersViewHolder {
        return TrainersViewHolder(
            ItemMainTrainersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TrainersViewHolder, position: Int) {}

    override fun getItemCount(): Int = 3

    inner class TrainersViewHolder(
        private val binding: ItemMainTrainersBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            initListener()
        }

        private fun initListener() {
            itemView.setOnClickListener {
                listener.onClick(binding.fitnessNameTextView.text.toString())
            }
        }
    }
}

interface MainTrainersListener {
    fun onClick(name: String)
}

