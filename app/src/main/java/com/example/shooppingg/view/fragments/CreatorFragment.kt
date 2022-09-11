package com.example.shooppingg.view.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shooppingg.R
import com.example.shooppingg.databinding.FragmentCreatorBinding
import com.example.shooppingg.model.CreatorModel
import com.example.shooppingg.view.adapters.CreatorRVAdapter
import com.example.shooppingg.viewmodel.CreatorViewModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class CreatorFragment : Fragment() {
    private val viewModel: CreatorViewModel by activityViewModels()

    private var _binding: FragmentCreatorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCreatorBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val exFabCreator = binding.creatorExtendedFab
        // Text View of Creator
        val tvCreator = binding.creatorListTextView
        // Autocomplete Text View of Creator
        val atvCreator = binding.creatorListAutoCompleteTextView
        // Recycler View of Creator
        val rvCreator = binding.creatorListItemRecyclerView
        // Adapter of CreatorRVAdapter
        val rvAdapter = CreatorRVAdapter(viewModel)

        // init observers ui
        initObservers(atvCreator, exFabCreator)

        viewModel.setRecyclerViewAdapter(rvAdapter)
        // Set layout manager of rvCreator
        rvCreator.layoutManager = LinearLayoutManager(context)
        // Set adapter of rvCreator
        rvCreator.adapter = rvAdapter
        // Set is fixed size on true
        rvCreator.setHasFixedSize(true)

        // Key listener of atvCreator (check is enter clicked)
        atvCreator.setOnKeyListener { _, i, keyEvent ->
            if(keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                // selected item/ write item in atvCreator
                viewModel.setAtvText(atvCreator.text.toString())
                val item = viewModel.getAtvText().value!!

                // check item is exist?
                if(checkItem(item))
                    {
                        val obj = CreatorModel(item)
                        viewModel.addItem(obj)
                        rvAdapter.notifyDataSetChanged()
                        viewModel.setAtvText("")

                    }

            }
            false
        }

        exFabCreator.setOnClickListener {
            val newDialog = CreatorSummaryDialogFragment()
            val bundleDialog = bundleOf("itemsList" to viewModel.itemsList.value)
            newDialog.arguments = bundleDialog
            newDialog.show(requireActivity().supportFragmentManager, "summary")
        }

        
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers(atvCreator : AutoCompleteTextView, exFabCreator : ExtendedFloatingActionButton) {
        //set text on the start of atvText
        atvCreator.setText(viewModel.getAtvText().value)
        //observer of atvText
        viewModel.getAtvText().observe(viewLifecycleOwner){
            atvCreator.setText(it)
        }
        //Expanded Floating Button visibility
        viewModel.itemsList.observe(viewLifecycleOwner) {
            exFabCreator.visibility = if(it.isNullOrEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun checkItem(item : String) : Boolean {
        //check is empty or null
        if(item.isNullOrEmpty()) {
            toastMessage("Cannot be null!!")
            return false
        }
        //todo: check item is exist?
        return true
    }

    //toast message scheme (default value of shortMsg is true
    private fun toastMessage(message : String, shortMsg: Boolean = true) {
        if(shortMsg) Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        else Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}