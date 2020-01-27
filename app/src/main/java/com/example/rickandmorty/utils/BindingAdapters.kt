package com.example.rickandmorty.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.fragments.characters.CharactersAdapter
import com.example.rickandmorty.network.responses.CharacterInfoResponse

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CharacterInfoResponse>?){
    val adapter = recyclerView.adapter as CharactersAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?){
    url?.let {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}