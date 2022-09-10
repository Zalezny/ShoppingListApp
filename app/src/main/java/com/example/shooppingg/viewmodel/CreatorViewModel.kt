package com.example.shooppingg.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shooppingg.model.CreatorModel
import com.example.shooppingg.view.adapters.CreatorRVAdapter

class CreatorViewModel : ViewModel() {

    private val _recyclerViewAdapter = MutableLiveData<CreatorRVAdapter>()

    fun getRecyclerViewAdapter() = _recyclerViewAdapter
    fun setRecyclerViewAdapter(rvAdapter : CreatorRVAdapter) {
        _recyclerViewAdapter.value = rvAdapter
    }

    private val _atvText = MutableLiveData<String>().apply { value = "" }
    fun getAtvText() = _atvText
    fun setAtvText(text : String) { _atvText.value = text }

    private val _itemsList = MutableLiveData<ArrayList<CreatorModel>>().apply { value = arrayListOf()}
    val itemsList get() = _itemsList

    fun addItem(item: CreatorModel) {
        _itemsList.value!!.add(item)
    }

}