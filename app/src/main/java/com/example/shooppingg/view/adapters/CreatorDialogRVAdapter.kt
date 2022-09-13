package com.example.shooppingg.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shooppingg.R
import com.example.shooppingg.model.CreatorModel
import com.example.shooppingg.viewmodel.CreatorSummaryDialogViewModel

class CreatorDialogRVAdapter(private val viewModel : CreatorSummaryDialogViewModel) : RecyclerView.Adapter<CreatorDialogRVAdapter.CreatorDialogViewHolder>() {

    private val itemsList = viewModel.itemsList.value!!


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorDialogViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_dialog_creator_rv,
            parent, false
        )
        return CreatorDialogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CreatorDialogViewHolder, position: Int) {
        val currItem = itemsList[position]

        holder.tvTitleAndAmount.text = "${currItem.title} x${currItem.amount}"
    }

    override fun getItemCount() = itemsList.size


    class CreatorDialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitleAndAmount : TextView = itemView.findViewById(R.id.title_and_amount_creator_dialog_rv)
        val atvCategory : AutoCompleteTextView = itemView.findViewById(R.id.atv_creator_dialog_rv)

//        init {
//            tvTitleAndAmount = itemView.findViewById(R.id.title_and_amount_creator_dialog_rv)
//            atvCategory = itemView.findViewById(R.id.atv_creator_dialog_rv)
//        }
    }
}