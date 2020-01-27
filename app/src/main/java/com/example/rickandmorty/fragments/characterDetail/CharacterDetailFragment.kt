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

        // Setting the lifecycleOwner to make sure that
        // LiveData will be automatically updated
        binding.lifecycleOwner = this

        // Retrieving the character Id that we sent
        // through safe args from CharactersFragment

        val characterId = CharacterDetailFragmentArgs.fromBundle(arguments!!).characterId

        // Creating the viewModelFactory to send parameters to the viewModel constructor

        val viewModelFactory = CharacterDetailViewModelFactory(characterId, application)

        // Sending the viewModel to the view using data binding
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(CharacterDetailViewModel::class.java)

        // Setting the onClickListener for the items in the grid
        binding.gridEpisodes.adapter = EpisodesAdapter(EpisodesAdapter.OnClickListener{

        })

        return binding.root
    }


}
