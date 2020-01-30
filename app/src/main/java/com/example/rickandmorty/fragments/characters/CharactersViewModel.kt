package com.example.rickandmorty.fragments.characters

import android.app.Application
import androidx.lifecycle.*
import com.example.rickandmorty.database.getDatabase
import com.example.rickandmorty.network.ApiStatus
import com.example.rickandmorty.network.responses.CharactersResponse
import com.example.rickandmorty.network.ShowApi
import com.example.rickandmorty.network.responses.CharacterDetailResponse
import com.example.rickandmorty.repository.RmRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException


class CharactersViewModel(application: Application) : AndroidViewModel(application) {

    // Kotlin coroutines related variables
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // API call status variables
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    // Navigation variable
    private val _navigateToSelectedCharacter = MutableLiveData<CharacterDetailResponse>()
    val navigateToSelectedCharacter: LiveData<CharacterDetailResponse>
        get() = _navigateToSelectedCharacter

    private val database = getDatabase(application)
    private val charactersRepository = RmRepository(database)
    init {
        // getCharactersList()
        coroutineScope.launch {
            charactersRepository.refreshCharacters()
        }
    }

    val characters = charactersRepository.characters

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

    fun setApiStatus(status: ApiStatus) {
        _status.value = status
    }

    class Factory (val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CharactersViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return CharactersViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewModel")
        }

    }
}