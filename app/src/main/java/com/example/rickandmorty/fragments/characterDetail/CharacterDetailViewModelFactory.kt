package com.example.rickandmorty.fragments.characterDetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CharacterDetailViewModelFactory (
    private val characterId: Int,
    private val app: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterDetailViewModel::class.java)){
            return CharacterDetailViewModel(characterId, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}