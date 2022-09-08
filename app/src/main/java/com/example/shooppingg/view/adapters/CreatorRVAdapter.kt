package com.example.shooppingg.view.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CreatorRVAdapter(private val hourList : ArrayList<String>) : RecyclerView.Adapter<CreatorRVAdapter.CreatorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CreatorViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return hourList.size
    }

    class CreatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}