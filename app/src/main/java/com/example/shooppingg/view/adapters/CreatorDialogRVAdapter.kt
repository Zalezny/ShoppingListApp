package com.example.shooppingg.view.adapters

import android.content.Context
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shooppingg.R
import com.example.shooppingg.model.CreatorModel
import com.example.shooppingg.viewmodel.CreatorSummaryDialogViewModel

class CreatorDialogRVAdapter(private val viewModel : CreatorSummaryDialogViewModel, private val ctx : Context) : RecyclerView.Adapter<CreatorDialogRVAdapter.CreatorDialogViewHolder>() {

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

        //set title and amount
        holder.tvTitleAndAmount.text = "${currItem.title} x${currItem.amount}"

        val atvCategory = holder.atvCategory
        //atvCategory adapter lists of categories
        val arrayAdapter = ArrayAdapter<String>(ctx, R.layout.item_dropdown_menu_categories, viewModel.categories.value!!.toList())
        //set adapter in atvCategory
        atvCategory.setAdapter(arrayAdapter)



        atvCategory.setOnItemClickListener { _, _, posAdapter, _ ->
            val value = arrayAdapter.getItem(posAdapter)
            viewModel.setCategory(position, value!!)
        }

    }

    override fun getItemCount() = itemsList.size


    class CreatorDialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitleAndAmount : TextView = itemView.findViewById(R.id.title_and_amount_creator_dialog_rv)
        val atvCategory : AutoCompleteTextView = itemView.findViewById(R.id.atv_creator_dialog_rv)
    }
}