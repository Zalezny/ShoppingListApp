package com.example.shooppingg.view.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shooppingg.databinding.FragmentCreatorBinding
import com.example.shooppingg.view.adapters.CreatorRVAdapter
import com.example.shooppingg.viewmodel.DashboardViewModel

class CreatorFragment : Fragment() {

    private var _binding: FragmentCreatorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentCreatorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Text View of Creator
        val tvCreator = binding.creatorListTextView
        // Autocomplete Text View of Creator
        val atvCreator = binding.creatorListAutoCompleteTextView
        // Recycler View of Creator
        val rvCreator = binding.creatorListItemRecyclerView
        // Array List of items
        val itemsList : ArrayList<String> = arrayListOf()
        // Adapter of CreatorRVAdapter
        val rvAdapter = CreatorRVAdapter(itemsList)
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
                val item = atvCreator.text.toString()
                // check item is exist?
                if(checkItem(item))
                    {
                        itemsList.add(item)
                        rvAdapter.notifyDataSetChanged()
                        atvCreator.text.clear()
                    }

            }
            false
        }






        
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkItem(item : String) : Boolean {
        //todo: check item is exist?
        return true
    }
}