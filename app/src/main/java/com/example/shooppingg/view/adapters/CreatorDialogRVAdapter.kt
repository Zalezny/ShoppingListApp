package com.example.shooppingg.view.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shooppingg.model.CreatorModel

class CreatorDialogRVAdapter(private val itemsList : ArrayList<CreatorModel>) : RecyclerView.Adapter<CreatorDialogRVAdapter.CreatorDialogViewHolder> {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorDialogViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CreatorDialogViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    class CreatorDialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}