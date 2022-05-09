package com.example.fitvibe.main.presentation.trainers_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitvibe.R
import com.example.fitvibe.databinding.ItemMainTrainersBinding
import com.example.fitvibe.utils.Trainer
import com.example.fitvibe.utils.trainersList

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
        fun bind(trainer: Trainer) {
            with (binding) {
                setImage(trainer.image)
                trainerNameTextView.text = trainer.name
                fitnessNameTextView.text = trainer.profession
                trainerStatusTextView.text = if (trainer.status) "Онлайн" else "Оффлайн"
                trainDurationTextView.text = trainer.duration
                favouritesImageView.setImageResource(if (trainer.isFavourite) R.drawable.ic_favourites_selected else R.drawable.ic_favourite_unselected)
                favouritesImageView.setOnClickListener {
                    trainersList[trainersList.indexOf(trainer)].isFavourite = !trainersList[trainersList.indexOf(trainer)].isFavourite
                    notifyItemChanged(adapterPosition)
                }
            }
            initListener(trainer)
        }

        private fun setImage(url: String) {
            Glide.with(itemView.context)
                .load(url)
                .error(R.drawable.img_fitness)
                .into(binding.profileImageView)
        }

        private fun initListener(trainer: Trainer) {
            itemView.setOnClickListener {
                listener.onClick(trainer)
            }
        }
    }
}

interface MainTrainersListener {
    fun onClick(trainer: Trainer)
}

