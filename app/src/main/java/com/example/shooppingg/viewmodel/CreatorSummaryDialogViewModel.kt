package com.example.shooppingg.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shooppingg.model.CreatorModel

class CreatorSummaryDialogViewModel : ViewModel() {
    private val _itemsList = MutableLiveData<Array<CreatorModel>>().apply { value = arrayOf() }
    val itemsList get() = _itemsList

    fun setArray(array: Array<CreatorModel>) {
        itemsList.value = array
    }

    fun setCategory(pos: Int, category : String) {
        val item = _itemsList.value!![pos]
        val list = _itemsList.value!!.toMutableList()
        list.removeAt(pos)
        val newItem = CreatorModel(item.title, item.amount, category)
        list.add(pos, newItem)
        setArray(list.toTypedArray())
    }

    private val _categories = MutableLiveData<ArrayList<String>>().apply {
        value = arrayListOf("Meals", "Fruits&Vegetables", "Bakes", "Dairies", "Sanitation", "Sweets", "Others")
    }
    val categories get() = _categories
}