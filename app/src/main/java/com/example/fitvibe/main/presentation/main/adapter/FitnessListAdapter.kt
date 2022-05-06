package com.example.fitvibe.main.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitvibe.databinding.ItemFitnessListBinding

class FitnessListAdapter(
    private val listener: FitnessClickListener
) : RecyclerView.Adapter<FitnessListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFitnessListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 3

    inner class ViewHolder(
        private val binding: ItemFitnessListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            initListener()
        }

        private fun initListener() {
            itemView.setOnClickListener{
                listener.onFitnessClick(binding.fitnessValueTextView.text.toString())
            }
        }
    }

}

interface FitnessClickListener {
    fun onFitnessClick(fitnessValue: String)
}