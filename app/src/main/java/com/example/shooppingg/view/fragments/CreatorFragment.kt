package com.example.shooppingg.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

        val tab = arrayListOf("test", "test2")

        atvCreator.addTextChangedListener {
            rvCreator.adapter = CreatorRVAdapter(tab)
        }

        
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}