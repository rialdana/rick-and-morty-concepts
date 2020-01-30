package com.example.rickandmorty.fragments.characterDetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * View model factory that helps us
 * to pass parameters to the viewModel constructor
 *
 * @property characterId
 * @property app
 */

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