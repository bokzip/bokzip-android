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
    private var filterLocation = MutableLiveData<String?>()
    private var sortOption = MutableLiveData<String?>()
    private var isCompleted = MutableLiveData(false)
    private val items = MutableLiveData<ArrayList<RecommendBokjiItem>?>(arrayListOf())
    private val generalItems = MutableLiveData<ArrayList<RecommendBokjiItem>?>(arrayListOf())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getBokjiItem()
            getGeneralBokjiItem()
        }
    }

    fun setFilterCategory(category: String?) {
        filterCategory.value = category
    }

    fun setFilterLocation(location: String?) {
        filterLocation.value = location
    }

    fun setSortOption(option: String?) {
        sortOption.value = option
    }

    fun setCompleted(isCompleted: Boolean) {
        this.isCompleted.value = isCompleted
    }

    fun getFilterLocation(): LiveData<String?> = filterLocation
    fun getCompleted(): LiveData<Boolean> = isCompleted
    fun getItems(): LiveData<ArrayList<RecommendBokjiItem>?> = items
    fun getGeneralItems(): LiveData<ArrayList<RecommendBokjiItem>?> = generalItems

    // TODO move FilterRepository
   private suspend fun getBokjiItem() {
        val response = api.getRecommendBokji()
        if (response?.isSuccessful) {
            items.postValue(response.body())
        } else {
            Log.e(TAG, "The requested resource could not be found")
        }
    }

    private suspend fun getGeneralBokjiItem() {
        val response = api.getGeneralBokji()
        if (response?.isSuccessful) {
            generalItems.postValue(response.body())
        } else {
            Log.e(TAG, "The requested resource could not be found")
        }
    }

    fun getFilterBokjiItem() {
        viewModelScope.launch(Dispatchers.IO) {
            val category = filterCategory.value ?: "지원" // TODO
            val response = api.getCategoryFilterResult(
                category,
                filterLocation.value,
                sortOption.value
            ) // TODO
            if (response.isSuccessful) {
                items.postValue(response.body())
            } else {
                Log.e(TAG, "The requested resource could not be found")
            }
        }
    }

    fun reset() {
        filterCategory.postValue(null)
        filterLocation.postValue(null)
        sortOption.postValue(null)
        isCompleted.postValue(false)
    }

    companion object {
        const val TAG = "FilterViewModel"
    }
}