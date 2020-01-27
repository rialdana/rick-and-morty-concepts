package com.example.rickandmorty.network.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationDetailResponse(
    val id: Int,
    val name: String,
    val dimensions: String,
    val residents: List<String>,
    val url: String,
    val created: String
) : Parcelable