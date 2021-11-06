package com.unionz.bokzip.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FilterViewModel(application: Application) : AndroidViewModel(application) {

    private var filterCategory = MutableLiveData<String?>()
    private var sortOption = MutableLiveData<String?>()
    private var isCompleted = MutableLiveData(false)

    fun getFilterCategory(): MutableLiveData<String?> = filterCategory
    fun getSortOption(): LiveData<String?> = sortOption
    fun getCompleted(): LiveData<Boolean> = isCompleted

    fun setFilterCategory(category: String?) {
        filterCategory.value = category
    }

    fun setSortOption(option: String?) {
        sortOption.value = option
    }

    fun setCompleted(isCompleted: Boolean) {
        this.isCompleted.value = isCompleted
    }

    fun reset() {
        filterCategory.value = null
        sortOption.value = null
        isCompleted.value = false
    }
}