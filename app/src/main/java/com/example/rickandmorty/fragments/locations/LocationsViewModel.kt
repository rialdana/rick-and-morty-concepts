package com.example.rickandmorty.fragments.locations

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.network.ApiStatus
import com.example.rickandmorty.network.ShowApi
import com.example.rickandmorty.network.responses.CharactersResponse
import com.example.rickandmorty.network.responses.LocationsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class LocationsViewModel : ViewModel(){
    // Kotlin coroutines related variables
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // API call status variables
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    // Characters list variables
    private val _locations = MutableLiveData<LocationsResponse>()
    val locations: LiveData<LocationsResponse>
        get() = _locations

    init {
        getLocationsList()
    }

    private fun getLocationsList(){
        coroutineScope.launch {
            val getLocationsDeferred = ShowApi.retrofitService.getLocationsAsync()
            try {
                _status.value = ApiStatus.LOADING
                val locationsList = getLocationsDeferred.await()
                _status.value = ApiStatus.DONE
                _locations.value = locationsList

            }catch (ex: Exception){
                Log.e("LocationsViewModel", "Error getting the locations", ex)
                _status.value = ApiStatus.ERROR
            }
        }
    }
}