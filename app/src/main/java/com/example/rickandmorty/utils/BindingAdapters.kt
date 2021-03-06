package com.example.rickandmorty.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.adapters.LocationsAdapter
import com.example.rickandmorty.adapters.EpisodesAdapter
import com.example.rickandmorty.adapters.CharactersAdapter
import com.example.rickandmorty.network.ApiStatus
import com.example.rickandmorty.network.responses.CharacterDetailResponse
import com.example.rickandmorty.network.responses.LocationDetailResponse

/**
 * This binding adapter sets a list of [CharacterDetailResponse]
 * into a recyclerView that is injected by the XML view when
 * it calls the binding adapter. It used [CharactersAdapter]
 *
 * @param recyclerView
 * @param data
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CharacterDetailResponse>?){
    val adapter = recyclerView.adapter as CharactersAdapter
    adapter.submitList(data)
}

/**
 * This binding adapter changes the visibility of any [View] based on
 * the [ApiStatus] parameter, this is helpful to hide detailed views
 * when the api is being called, so that we do not show textviews with
 * NULL value to our user
 */
@BindingAdapter("apiVisibility")
fun bindApiVisibility(view: View, status: ApiStatus?){
    when(status){
        ApiStatus.DONE -> {
            view.visibility = View.VISIBLE
        }
        ApiStatus.LOADING -> {
            view.visibility = View.GONE
        }
        ApiStatus.ERROR -> {
            view.visibility = View.GONE
        }
    }
}

/**
 * This binding adapter sets a list of IDs
 * to a recyclerView with GridLayoutManager using [EpisodesAdapter]
 *
 * @param recyclerView
 * @param data
 */
@BindingAdapter("gridEpisodesData")
fun bindGridView(recyclerView: RecyclerView, data: List<Int>?){
    data?.let {
        val adapter = recyclerView.adapter as EpisodesAdapter
        adapter.submitList(data)
    }
}

/**
 * This binding adapter sets a list of [LocationDetailResponse] objects
 * to a recyclerView with GridLayoutManager using [LocationsAdapter]
 *
 * @param recyclerView
 * @param data
 */
@BindingAdapter("gridLocationsData")
fun bindGridViewLocations(recyclerView: RecyclerView, data: List<LocationDetailResponse>?){
    data?.let {
        val adapter = recyclerView.adapter as LocationsAdapter
        adapter.submitList(data)
    }
}

/**
 * This binding adapter loads an image from the web
 * using [Glide] into an imageView that is injected
 *
 * @param imageView
 * @param url
 */
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, url: String?){
    url?.let {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}

/**
 * This binding adapter receives a progress bar
 * and changes its visibility according to the [ApiStatus]
 * that is injected through the XML View
 *
 * @param progressBar
 * @param status
 */
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