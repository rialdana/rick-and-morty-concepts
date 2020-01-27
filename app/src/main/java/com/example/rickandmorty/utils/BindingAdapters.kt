package com.example.rickandmorty.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.fragments.characterDetail.EpisodesAdapter
import com.example.rickandmorty.fragments.characters.CharactersAdapter
import com.example.rickandmorty.network.ApiStatus
import com.example.rickandmorty.network.responses.CharacterInfoResponse

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CharacterInfoResponse>?){
    val adapter = recyclerView.adapter as CharactersAdapter
    adapter.submitList(data)
}

@BindingAdapter("gridEpisodesData")
fun bindGridView(recyclerView: RecyclerView, data: List<Int>?){
    data?.let {
        val adapter = recyclerView.adapter as EpisodesAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, url: String?){
    url?.let {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}

@BindingAdapter("apiCallStatus")
fun bindStatus(progressBar: ProgressBar, status: ApiStatus?){
    when(status){
        ApiStatus.DONE -> {
            progressBar.visibility = View.GONE
        }
        ApiStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        ApiStatus.ERROR -> {
            progressBar.visibility = View.GONE
        }
    }
}