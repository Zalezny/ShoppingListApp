package com.example.shooppingg.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shooppingg.R
import com.example.shooppingg.model.CreatorModel
import com.example.shooppingg.viewmodel.CreatorViewModel

class CreatorRVAdapter(private val viewModel : CreatorViewModel) : RecyclerView.Adapter<CreatorRVAdapter.CreatorViewHolder>() {

    private val itemsList : ArrayList<CreatorModel> = viewModel.itemsList.value!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_creator_rv,
            parent, false
        )
        return CreatorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CreatorViewHolder, position: Int) {
        val currItem = itemsList[position]
        holder.tvItemCreator.text = currItem.title
        holder.tvAmountCreator.text = currItem.amount.toString()


        holder.ivDeleteCreator.setOnClickListener {
            removeItem(position)
        }

        val amount = holder.tvAmountCreator
        var amountInList = itemsList[position].amount
        var amountInt = amount.text.toString().toInt()

        holder.ivRemoveAmountCreator.setOnClickListener {
            if(amountInt == 1) {
                removeItem(position)
                return@setOnClickListener
            }
            amountInt -= 1
            val item = CreatorModel(itemsList[position].title, amountInt)
            viewModel.removeItemAt(position)
            viewModel.addItemAt(position, item)
            notifyItemChanged(position, item)
            amount.text = amountInList.toString()
        }
        holder.ivAddAmountCreator.setOnClickListener { v ->

            if(amountInt >= 9) {
                Toast.makeText(v.context, "That's a max amount!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            amountInt += 1
            val item = CreatorModel(itemsList[position].title, amountInt)
            viewModel.removeItemAt(position)
            viewModel.addItemAt(position, item)
            notifyItemChanged(position, item)
//            amountInList = amountInt
            amount.text = amountInList.toString()

        }



    }

    override fun getItemCount() = itemsList.size

    private fun removeItem(pos : Int) {
        viewModel.removeItemAt(pos)
        notifyItemRemoved(pos)
        notifyDataSetChanged()
    }


    class CreatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemCreator : TextView
        val ivDeleteCreator : ImageView
        val ivRemoveAmountCreator : ImageView
        val tvAmountCreator : TextView
        val ivAddAmountCreator : ImageView

        init {
            tvItemCreator = itemView.findViewById(R.id.tv_name_creator_item)
            ivDeleteCreator = itemView.findViewById(R.id.iv_delete_creator)
            ivRemoveAmountCreator = itemView.findViewById(R.id.iv_remove_amount_creator_item)
            tvAmountCreator = itemView.findViewById(R.id.tv_amount_creator_item)
            ivAddAmountCreator = itemView.findViewById(R.id.iv_add_amount_creator_item)
        }
    }
}