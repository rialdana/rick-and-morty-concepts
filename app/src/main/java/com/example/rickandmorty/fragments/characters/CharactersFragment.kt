package com.example.rickandmorty.fragments.characters


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

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
            //
        })

        return  binding.root
    }


}
