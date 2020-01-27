package com.example.rickandmorty.fragments.characterDetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.network.ApiStatus
import com.example.rickandmorty.network.ShowApi
import com.example.rickandmorty.network.responses.CharacterDetailResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CharacterDetailViewModel(characterId: Int, app: Application) : AndroidViewModel(app) {

    // Variable that holds the character ID from the safe args and constructor
    private val _selectedCharacterId = MutableLiveData<Int>()

    // Object that holds the character Info from the API REST
    private val _selectedCharacterInfo = MutableLiveData<CharacterDetailResponse>()
    val selectedCharacterInfo: LiveData<CharacterDetailResponse>
        get() = _selectedCharacterInfo

    // List that contains the ID of the episodes that this character has been involved in
    private val _selectedCharacterEpisodesList = MutableLiveData<List<Int>>()
    val selectedCharacterEpisodesList: LiveData<List<Int>>
        get() = _selectedCharacterEpisodesList

    // API call status variables
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    // Kotlin coroutines related variables
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        _selectedCharacterId.value = characterId
        getCharacterDetail(_selectedCharacterId.value!!)
    }

    /*
        This function will launch a corutine that will try to retrieve
        the character detail from the API rest, in case it's not possible,
        it will throw an exception
     */

    private fun getCharacterDetail(characterId: Int) {
        coroutineScope.launch {
            _status.value = ApiStatus.LOADING
            val getCharacterDetailDeferred = ShowApi.retrofitService.getCharacterDetailAsync(characterId)
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

    /*
        This function will order the episodes Id, it will remove
        the URL from the String and get only the Integer value
     */
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

    /*
        This method is also used to cancel the viewModelJob, which
        will close or cancel any open coroutine
     */

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}