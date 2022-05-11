package com.example.fitvibe.calendar.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitvibe.R
import com.example.fitvibe.databinding.ItemCalendarTrainingBinding
import com.example.fitvibe.utils.Trainer

class CalendarTrainingAdapter(
    private val listener: CalendarTrainingListener
) : RecyclerView.Adapter<CalendarTrainingAdapter.TrainingViewHolder>() {

    private var dataList: List<Trainer> = mutableListOf()
    private var lastSelectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        return TrainingViewHolder(
            ItemCalendarTrainingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        holder.bind(dataList[position], position == lastSelectedPosition)
    }

    override fun getItemCount(): Int = dataList.size


    fun setList(data: List<Trainer>) {
        dataList = data
        notifyDataSetChanged()
    }

    inner class TrainingViewHolder(
        private val binding: ItemCalendarTrainingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(trainer: Trainer, isSelected: Boolean) {
            with (binding) {
                fitnessName.text = trainer.profession
                fitnessDuration.text = trainer.duration
                setImage(trainer.image)
                fitnessName.text = trainer.name
                fitnessStatus.text = if (trainer.status) "Онлайн" else "Оффлайн"
                initBackground(isSelected)
            }
            itemView.setOnClickListener {
                notifyItemChanged(lastSelectedPosition)
                lastSelectedPosition = adapterPosition
                notifyItemChanged(adapterPosition)
            }
            binding.menuButton.setOnClickListener {
                listener.onDeleteClick(trainer)
            }
        }

        private fun initBackground(isSelected: Boolean) {
            if (isSelected) {
                itemView.background = ContextCompat.getDrawable(itemView.context,
                R.drawable.bg_nevada_rounded_5)
                binding.fitnessName.setTextColor(itemView.resources.getColor(R.color.white))
                binding.trainerName.setTextColor(itemView.resources.getColor(R.color.white))
            } else {
                itemView.background = ContextCompat.getDrawable(itemView.context,
                    R.drawable.bg_white_rounded_border_1_nevada_15)
                binding.fitnessName.setTextColor(itemView.resources.getColor(R.color.nevada_color))
                binding.trainerName.setTextColor(itemView.resources.getColor(R.color.nevada_color))
            }
        }

        private fun setImage(url: String) {
            Glide.with(itemView)
                .load(url)
                .error(R.drawable.img_fitness)
                .into(binding.fitnessImage)
        }
    }
}

interface CalendarTrainingListener {
    fun onDeleteClick(trainer: Trainer)
}

