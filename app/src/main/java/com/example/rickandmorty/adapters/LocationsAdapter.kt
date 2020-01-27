package com.example.rickandmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.GridItemLocationBinding
import com.example.rickandmorty.network.responses.LocationDetailResponse

class LocationsAdapter (private val onClickListener: OnClickListener) :

    ListAdapter<LocationDetailResponse, LocationsAdapter.LocationsViewHolder>(DiffCallBack){


    companion object DiffCallBack : DiffUtil.ItemCallback<LocationDetailResponse>(){
        override fun areItemsTheSame(
            oldItem: LocationDetailResponse,
            newItem: LocationDetailResponse
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: LocationDetailResponse,
            newItem: LocationDetailResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class OnClickListener(val clickListener: (locationDetail: LocationDetailResponse) -> Unit){
        fun onClick(locationDetail: LocationDetailResponse) = clickListener(locationDetail)
    }

    class LocationsViewHolder(private val binding: GridItemLocationBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(location: LocationDetailResponse){
            binding.location = location
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        return LocationsViewHolder(GridItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val location = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(location)
        }
        holder.bind(location)
    }
}