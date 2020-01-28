package com.example.rickandmorty.fragments.locations


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.example.rickandmorty.R
import com.example.rickandmorty.adapters.LocationsAdapter
import com.example.rickandmorty.databinding.FragmentLocationsBinding
import com.example.rickandmorty.fragments.characters.CharactersViewModel
import com.google.android.material.snackbar.Snackbar

class LocationsFragment : Fragment() {

    private val viewModel: LocationsViewModel by lazy {
        ViewModelProviders.of(this).get(LocationsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentLocationsBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.gridLocations.adapter = LocationsAdapter(LocationsAdapter.OnClickListener {
            Snackbar.make(
                binding.gridLocations,
                it.name, Snackbar.LENGTH_SHORT
            ).show()
        })

        return binding.root
    }


}
