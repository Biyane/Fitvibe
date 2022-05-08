package com.example.fitvibe.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitvibe.databinding.ItemSearchBinding

class SearchAdapter(
    private val listener: OnSearchListener
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var dataList = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size


    fun setList(list: List<String>) {
        dataList = list

        notifyDataSetChanged()
    }

    inner class SearchViewHolder(
        private val binding: ItemSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            initListener()
        }

        fun bind(value: String) {
            binding.name.text = value
        }

        private fun initListener() {
            itemView.setOnClickListener {
                listener.onSearchClick(binding.name.text.toString())
            }
        }
    }
}

interface OnSearchListener {
    fun onSearchClick(value: String)
}