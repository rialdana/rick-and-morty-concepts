package com.example.rickandmorty.fragments.characterDetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class CharacterDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentCharacterDetailBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val characterId = CharacterDetailFragmentArgs.fromBundle(arguments!!).characterId

        val viewModelFactory = CharacterDetailViewModelFactory(characterId, application)

        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(CharacterDetailViewModel::class.java)

        return binding.root
    }


}
