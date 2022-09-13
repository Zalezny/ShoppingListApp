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
}