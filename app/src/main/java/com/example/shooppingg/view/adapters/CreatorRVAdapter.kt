package com.example.shooppingg.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shooppingg.R

class CreatorRVAdapter(private val itemsList : ArrayList<String>) : RecyclerView.Adapter<CreatorRVAdapter.CreatorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_creator_rv,
            parent, false
        )
        return CreatorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CreatorViewHolder, position: Int) {
        val currItem = itemsList[position]
        holder.tvItemCreator.text = currItem
    }

    override fun getItemCount() = itemsList.size


    class CreatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemCreator : TextView
        init {
            tvItemCreator = itemView.findViewById(R.id.tv_item_creator)
        }
    }
}