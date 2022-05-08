package com.example.fitvibe.main.presentation.trainers_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitvibe.R
import com.example.fitvibe.databinding.ItemMainTrainersBinding
import com.example.fitvibe.utils.Trainer

class MainTrainersAdapter(
    private val listener: MainTrainersListener
) : RecyclerView.Adapter<MainTrainersAdapter.TrainersViewHolder>() {

    private var dataList: List<Trainer> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainersViewHolder {
        return TrainersViewHolder(
            ItemMainTrainersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TrainersViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun setList(list: List<Trainer>) {
        dataList = list
    }

    inner class TrainersViewHolder(
        private val binding: ItemMainTrainersBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            initListener()
        }

        fun bind(trainer: Trainer) {
            with (binding) {
                setImage(trainer.image)
                trainerNameTextView.text = trainer.name
                fitnessNameTextView.text = trainer.profession
                trainerStatusTextView.text = if (trainer.status) "Онлайн" else "Оффлайн"
                trainDurationTextView.text = trainer.duration
            }
        }

        private fun setImage(url: String) {
            Glide.with(itemView.context)
                .load(url)
                .error(R.drawable.img_fitness)
                .into(binding.profileImageView)
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

