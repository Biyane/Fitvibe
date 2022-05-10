package com.example.fitvibe.calendar.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitvibe.databinding.ItemCalendarTimeBinding

class CalendarTimeAdapter : RecyclerView.Adapter<CalendarTimeAdapter.TimeViewHolder>() {

    private var dataList: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        return TimeViewHolder(
            ItemCalendarTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun setList(data: List<String>) {
        dataList = data
        notifyDataSetChanged()
    }

    inner class TimeViewHolder(
        private val binding: ItemCalendarTimeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(time: String) {
            binding.time.text = time
        }
    }
}