package com.example.fitvibe.main.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitvibe.R
import com.example.fitvibe.databinding.ItemFitnessListBinding
import com.example.fitvibe.utils.Fitness

class FitnessListAdapter(
    private val listener: FitnessClickListener
) : RecyclerView.Adapter<FitnessListAdapter.ViewHolder>() {

    private var dataList: List<Fitness> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFitnessListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun setList(list: List<Fitness>) {
        dataList = list
    }

    inner class ViewHolder(
        private val binding: ItemFitnessListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            initListener()
        }

        fun bind(data: Fitness) {
            setImage(data.picture)
            binding.fitnessValueTextView.text = data.name
        }

        private fun setImage(url: String) {
            Glide.with(itemView.context)
                .load(url)
                .error(R.drawable.img_fitness)
                .into(binding.image)
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