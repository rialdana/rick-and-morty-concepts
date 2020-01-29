package com.example.rickandmorty.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.rickandmorty.database.RmDatabase
import com.example.rickandmorty.database.asDomainModel
import com.example.rickandmorty.network.ApiService
import com.example.rickandmorty.network.ShowApi
import com.example.rickandmorty.network.responses.CharacterDetailResponse
import com.example.rickandmorty.network.responses.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RmRepository (private val database: RmDatabase){
    val characters: LiveData<List<CharacterDetailResponse>> = Transformations.map(database.rmDao.getCharacters()){
        it.asDomainModel()
    }

    suspend fun refreshCharacters(){
        withContext(Dispatchers.IO){
            val characters = ShowApi.retrofitService.getCharactersAsync().await()
            database.rmDao.insertAll(*characters.asDatabaseModel())
        }
    }
}