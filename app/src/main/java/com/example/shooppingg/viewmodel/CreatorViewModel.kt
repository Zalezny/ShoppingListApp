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
        val list = _itemsList.value
        list!!.add(item)
        _itemsList.value = list
    }
    fun removeItemAt(pos : Int) {
        val list = _itemsList.value
        list!!.removeAt(pos)
        _itemsList.value = list
    }
    fun addItemAt(pos: Int, item: CreatorModel) {
        val list = _itemsList.value
        list!!.add(pos, item)
        _itemsList.value = list
    }


}