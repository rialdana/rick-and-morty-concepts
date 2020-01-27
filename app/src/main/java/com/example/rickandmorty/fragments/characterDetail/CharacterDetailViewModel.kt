package com.example.rickandmorty.fragments.characterDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.network.ShowApi
import com.example.rickandmorty.network.responses.CharacterInfoResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CharacterDetailViewModel(characterId: Int, app: Application) : AndroidViewModel(app) {
    private val _selectedCharacterId = MutableLiveData<Int>()

    private val _selectedCharacterInfo = MutableLiveData<CharacterInfoResponse>()
    val selectedCharacterInfo: LiveData<CharacterInfoResponse>
        get() = _selectedCharacterInfo

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        _selectedCharacterId.value = characterId
        getCharacterDetail(_selectedCharacterId.value!!)
    }

    private fun getCharacterDetail(characterId: Int) {
        coroutineScope.launch {
            val getCharacterDetailDeferred = ShowApi.retrofitService.getCharacterDetail(characterId)
            try {
                val characterInfo = getCharacterDetailDeferred.await()

                _selectedCharacterInfo.value = characterInfo
            } catch (e: Exception) {
                _selectedCharacterInfo.value = null
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}