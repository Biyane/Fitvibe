package com.example.fitvibe.calendar.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fitvibe.R
import com.example.fitvibe.calendar.presentation.view.Day
import com.example.fitvibe.databinding.ItemCalendarDayBinding
import com.example.fitvibe.utils.getDaysList

class CalendarDayAdapter(
    private val listener: CalendarDayListener
) : RecyclerView.Adapter<CalendarDayAdapter.DayViewHolder>() {

    private val daysList = getDaysList()
    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(
            ItemCalendarDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(daysList[position], selectedPosition == position)
    }

    override fun getItemCount(): Int = daysList.size


    inner class DayViewHolder(
        private val binding: ItemCalendarDayBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(day: Day, isSelected: Boolean) {
            with (binding) {
                dayValue.text = day.day
                dayNumber.text = day.time
            }
            firstEntrance(day)
            initListener(day)
            initBackGround(isSelected)
        }

        private fun firstEntrance(day: Day) {
//            listener.onDayClick(day)
        }

        private fun initBackGround(isSelected: Boolean) {
            if (isSelected) {
                itemView.background = ContextCompat.getDrawable(itemView.context,
                R.drawable.bg_yellow_rounded_10)
                binding.dayNumber.setTextColor(itemView.resources.getColor(R.color.white))
                binding.dayValue.setTextColor(itemView.resources.getColor(R.color.white))
            } else {
                itemView.background = null
                binding.dayNumber.setTextColor(itemView.resources.getColor(R.color.nevada_color))
                binding.dayValue.setTextColor(itemView.resources.getColor(R.color.nevada_color))
            }
        }

        private fun initListener(day: Day) {
            itemView.setOnClickListener {
                listener.onDayClick(day)
                notifyItemChanged(selectedPosition)
                selectedPosition = adapterPosition
                notifyItemChanged(adapterPosition)
            }
        }
    }
}

interface CalendarDayListener {
    fun onDayClick(day: Day)
}