package com.example.rickandmorty.fragments.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ListItemCharacterProfileBinding
import com.example.rickandmorty.network.responses.CharacterInfoResponse

class CharactersAdapter (val onClickListener: OnClickListener) :
    ListAdapter<CharacterInfoResponse, CharactersAdapter.CharacterViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<CharacterInfoResponse>(){
        override fun areItemsTheSame(
            oldItem: CharacterInfoResponse,
            newItem: CharacterInfoResponse
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: CharacterInfoResponse,
            newItem: CharacterInfoResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class CharacterViewHolder(private val binding: ListItemCharacterProfileBinding) :
            RecyclerView.ViewHolder(binding.root){

        fun bind(characterInfo: CharacterInfoResponse){
            binding.character = characterInfo
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (characterInfo: CharacterInfoResponse) -> Unit){
        fun onClick(characterInfo: CharacterInfoResponse) = clickListener(characterInfo)
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