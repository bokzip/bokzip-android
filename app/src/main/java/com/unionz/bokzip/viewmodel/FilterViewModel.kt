package com.unionz.bokzip.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.unionz.bokzip.model.RecommendBokjiItem
import com.unionz.bokzip.service.RemoteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilterViewModel(application: Application) :
    AndroidViewModel(application) {
    private val api = RemoteService.create()
    private var filterCategory = MutableLiveData<String?>()
    private var sortOption = MutableLiveData<String?>()
    private var isCompleted = MutableLiveData(false)
    private val items = MutableLiveData<ArrayList<RecommendBokjiItem>?>(arrayListOf())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getBokjiItem()
        }
    }

    fun setFilterCategory(category: String?) {
        filterCategory.value = category
    }

    fun setSortOption(option: String?) {
        sortOption.value = option
    }

    fun setCompleted(isCompleted: Boolean) {
        this.isCompleted.value = isCompleted
    }

    fun getFilterCategory(): LiveData<String?> = filterCategory
    fun getSortOption(): LiveData<String?> = sortOption
    fun getCompleted(): LiveData<Boolean> = isCompleted
    fun getItems(): LiveData<ArrayList<RecommendBokjiItem>?> = items

    // TODO move FilterRepository
    suspend fun getBokjiItem() {
        val response = api.getRecommendBokji()
        if (response?.isSuccessful) {
            items.postValue(response.body())
        } else {
            Log.e(TAG, "The requested resource could not be found")
        }
    }

    fun getFilterBokjiItem() {
        viewModelScope.launch(Dispatchers.IO) {
            val category = filterCategory.value ?: "지원" // TODO
            val response = api.getCategoryFilterResult(category, null, sortOption.value) // TODO
            if (response.isSuccessful) {
                items.postValue(response.body())
            } else {
                Log.e(TAG, "The requested resource could not be found")
            }
            reset()
        }
    }

    private fun reset() {
        filterCategory.postValue(null)
        sortOption.postValue(null)
        isCompleted.postValue(false)
    }

    companion object {
        const val TAG = "FilterViewModel"
    }
}