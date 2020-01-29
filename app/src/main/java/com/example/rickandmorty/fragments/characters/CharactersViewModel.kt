package com.example.rickandmorty.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.network.ApiStatus
import com.example.rickandmorty.network.responses.CharactersResponse
import com.example.rickandmorty.network.ShowApi
import com.example.rickandmorty.network.responses.CharacterDetailResponse
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
    private val _characters = MutableLiveData<List<CharacterDetailResponse>>()
    val characters: LiveData<List<CharacterDetailResponse>>
        get() = _characters

    // Navigation variable
    private val _navigateToSelectedCharacter = MutableLiveData<CharacterDetailResponse>()
    val navigateToSelectedCharacter: LiveData<CharacterDetailResponse>
        get() = _navigateToSelectedCharacter

    init {
        getCharactersList()
    }

    /*
     * This method will get the list of characters
     * from the API REST
     */

    private fun getCharactersList() {
        coroutineScope.launch {
            val getCharactersDeferred = ShowApi.retrofitService.getCharactersAsync()
            try {
                _status.value = ApiStatus.LOADING
                val charactersList = getCharactersDeferred.await()
                _status.value = ApiStatus.DONE
                _characters.value = charactersList.results
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _characters.value = null
            }

        }
    }

    /*
     * Clearing the viewModelJob to make sure
     * that coroutines won't remain in the system even
     * when the view model is destroyed
     */

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    /*
     * This method helps us to navigate to the selected character
     *
     * @param characterInfoResponse
     */

    fun displayCharacterDetail(characterInfoResponse: CharacterDetailResponse) {
        _navigateToSelectedCharacter.value = characterInfoResponse
    }

    /*
     * This method resets the navigation variable
     * so we don't navigate again to the same destination
     * by mistake
     */

    fun displayCharacterDetailComplete(){
        _navigateToSelectedCharacter.value = null
    }
}