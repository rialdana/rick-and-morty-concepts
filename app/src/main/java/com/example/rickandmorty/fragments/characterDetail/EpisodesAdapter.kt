package com.example.rickandmorty.fragments.characterDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ListItemEpisodeBinding

class EpisodesAdapter (val onClickListener: OnClickListener):
    ListAdapter<Int, EpisodesAdapter.EpisodeViewHolder>(DiffCallBack)
{

    companion object DiffCallBack : DiffUtil.ItemCallback<Int>(){
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

    }
    class OnClickListener(val clickListener: (episodeId: Int) -> Unit){
        fun onClick(episodeId: Int) = clickListener(episodeId)
    }

    class EpisodeViewHolder(private val binding: ListItemEpisodeBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(episodeId: Int){
            binding.episodeId = episodeId
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(ListItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episodeId = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(episodeId)
        }
        holder.bind(episodeId)
    }
}