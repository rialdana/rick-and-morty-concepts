package com.example.rickandmorty.fragments.characters


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersBinding

/**
 * A simple [Fragment] subclass.
 */
class CharactersFragment : Fragment() {

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProviders.of(this).get(CharactersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentCharactersBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.charactersList.adapter = CharactersAdapter(CharactersAdapter.OnClickListener{
            viewModel.displayCharacterDetail(it)
        })

        viewModel.navigateToSelectedCharacter.observe(viewLifecycleOwner, Observer { character ->
            character?.let {
                this.findNavController().navigate(
                    CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(character)
                )
                viewModel.displayCharacterDetailComplete()
            }

        })

        return  binding.root
    }


}
