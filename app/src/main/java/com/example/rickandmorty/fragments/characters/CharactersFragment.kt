package com.example.rickandmorty.fragments.characters


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.example.rickandmorty.adapters.CharactersAdapter
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.network.ApiStatus

/**
 * A simple [Fragment] subclass.
 */
class CharactersFragment : Fragment() {

    private val viewModel: CharactersViewModel by lazy {
        val activity = requireNotNull(this.activity){

        }
        ViewModelProviders.of(this, CharactersViewModel.Factory(activity.application))
            .get(CharactersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentCharactersBinding.inflate(inflater)

        // Setting the lifecycle owner to make sure
        // that Live data will be updated

        binding.lifecycleOwner = this

        // Sending the viewmodel to the binding object
        binding.viewModel = viewModel

        // Setting the click listener for each character in the recycler view
        binding.charactersList.adapter =
            CharactersAdapter(
                CharactersAdapter.OnClickListener {
                    viewModel.displayCharacterDetail(it)
                })

        // Listening to the navigation variable
        // that comes from the viewModel
        viewModel.navigateToSelectedCharacter.observe(viewLifecycleOwner, Observer { character ->
            character?.let {
                this.findNavController().navigate(

                    // Navigating to the detail fragment, and also sending
                    // the parameter as required through safe args

                    CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(character.id)
                )

                // Once we navigate, the navigateToSelectedCharacter property is cleared
                // So if the user goes back by pressing the back button, this fragment
                // won't navigate by accident

                viewModel.displayCharacterDetailComplete()
            }

        })

        viewModel.characters.observe(viewLifecycleOwner, Observer { list ->
            if (list == null || list.isEmpty()){
                viewModel.setApiStatus(ApiStatus.LOADING)
            }else{
                viewModel.setApiStatus(ApiStatus.DONE)
            }

        })

        return  binding.root
    }


}
