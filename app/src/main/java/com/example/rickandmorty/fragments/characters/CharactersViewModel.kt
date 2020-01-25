package com.example.rickandmorty.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.network.ApiStatus
import com.example.rickandmorty.network.CharacterResponse
import com.example.rickandmorty.network.ShowApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception


class CharactersViewModel : ViewModel() {

    // Kotlin coroutines related variables
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // API call status variables
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    // Characters list variables
    private val _characters = MutableLiveData<CharacterResponse>()
    val characters: LiveData<CharacterResponse>
        get() = _characters

    init {
        getCharactersList()
    }

    private fun getCharactersList() {
        coroutineScope.launch {
            val getCharactersDeferred = ShowApi.retrofitService.getCharactersAsync()
            try {
                _status.value = ApiStatus.LOADING
                val charactersList = getCharactersDeferred.await()
                _status.value = ApiStatus.DONE
                _characters.value = charactersList
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _characters.value = null
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}