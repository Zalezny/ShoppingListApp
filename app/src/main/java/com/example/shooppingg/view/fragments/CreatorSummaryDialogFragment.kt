package com.example.shooppingg.view.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shooppingg.R
import com.example.shooppingg.databinding.FragmentCreatorSummaryDialogBinding


import com.example.shooppingg.model.CreatorModel
import com.example.shooppingg.view.adapters.CreatorDialogRVAdapter
import com.example.shooppingg.viewmodel.CreatorSummaryDialogViewModel
import com.google.gson.Gson

class CreatorSummaryDialogFragment : DialogFragment() {

    private lateinit var viewModel: CreatorSummaryDialogViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //init viewmodel for this fragment
        viewModel = ViewModelProvider(this).get(CreatorSummaryDialogViewModel::class.java)
        //init gson (need it to serialize)
        val gson = Gson()
        //take itemsList like json
        val itemsListJson = arguments?.getString("itemsListJson")
        //set array in viewmodel of list items
        viewModel.setArray(gson.fromJson(itemsListJson, Array<CreatorModel>::class.java))


        return activity?.let {
            //set view
            val view = requireActivity().layoutInflater.inflate(R.layout.fragment_creator_summary_dialog, null)
            //set RecyclerView of this dialog fragment
            val rv : RecyclerView = view.findViewById(R.id.rvCreatorSummaryDialog)
            rv.layoutManager = LinearLayoutManager(context)
            rv.adapter = CreatorDialogRVAdapter(viewModel, this.requireActivity())
            rv.setHasFixedSize(true)


            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle("SUMMARY")
                .setMessage("Is it everything? Choose a category")
                .setView(view)
                .setPositiveButton("YES",
                    DialogInterface.OnClickListener { dialog, id ->
                        // START THE GAME!
                    })
                .setNegativeButton("NO",
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()!!.cancel()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

