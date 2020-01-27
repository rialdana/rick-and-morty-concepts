package com.example.rickandmorty.fragments.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ListItemCharacterProfileBinding
import com.example.rickandmorty.network.responses.CharacterDetailResponse

class CharactersAdapter (val onClickListener: OnClickListener) :
    ListAdapter<CharacterDetailResponse, CharactersAdapter.CharacterViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<CharacterDetailResponse>(){
        override fun areItemsTheSame(
            oldItem: CharacterDetailResponse,
            newItem: CharacterDetailResponse
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: CharacterDetailResponse,
            newItem: CharacterDetailResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class CharacterViewHolder(private val binding: ListItemCharacterProfileBinding) :
            RecyclerView.ViewHolder(binding.root){

        fun bind(characterInfo: CharacterDetailResponse){
            binding.character = characterInfo
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (characterInfo: CharacterDetailResponse) -> Unit){
        fun onClick(characterInfo: CharacterDetailResponse) = clickListener(characterInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(ListItemCharacterProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(character)
        }
        holder.bind(character)
    }
}