package com.example.rickandmorty.fragments.characterDetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.network.ApiStatus
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

    private val _selectedCharacterEpisodesList = MutableLiveData<List<Int>>()
    val selectedCharacterEpisodesList: LiveData<List<Int>>
        get() = _selectedCharacterEpisodesList

    // API call status variables
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        _selectedCharacterId.value = characterId
        getCharacterDetail(_selectedCharacterId.value!!)
    }

    private fun getCharacterDetail(characterId: Int) {
        coroutineScope.launch {
            _status.value = ApiStatus.LOADING
            val getCharacterDetailDeferred = ShowApi.retrofitService.getCharacterDetail(characterId)
            try {
                val characterInfo = getCharacterDetailDeferred.await()

                _selectedCharacterInfo.value = characterInfo
                _status.value = ApiStatus.DONE
                getEpisodesId()
            } catch (e: Exception) {
                _selectedCharacterInfo.value = null
                _status.value = ApiStatus.ERROR
            }
        }
    }

    private fun getEpisodesId(){
        coroutineScope.launch {
            val episodesId: MutableList<Int> = arrayListOf()
            _selectedCharacterInfo.value?.episode?.forEach { episodeUrl ->

                val episodeId: Int =  episodeUrl.replace("https://rickandmortyapi.com/api/episode/", "").trim().toInt()
                Log.i("EpisodeID", episodeId.toString())
                episodesId.add(
                    episodeId
                )
            }
            _selectedCharacterEpisodesList.value = episodesId
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}