package com.example.shooppingg.view.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.shooppingg.R
import com.example.shooppingg.databinding.FragmentCreatorBinding
import com.example.shooppingg.databinding.FragmentCreatorSummaryDialogBinding
import com.example.shooppingg.viewmodel.CreatorSummaryDialogViewModel

class CreatorSummaryDialogFragment : DialogFragment() {

    private lateinit var viewModel: CreatorSummaryDialogViewModel

    private var _binding: FragmentCreatorSummaryDialogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreatorSummaryDialogBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val itemsList = arguments?.getString("itemsList")
        viewModel = ViewModelProvider(this).get(CreatorSummaryDialogViewModel::class.java)

        val rvSummaryDialog = binding.rvCreatorSummaryDialog


        return root
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {




        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle("SUMMARY")
                .setMessage("Is it everything? Choose a category")
                .setView(R.layout.fragment_creator_summary_dialog)
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

