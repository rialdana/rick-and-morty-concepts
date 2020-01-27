package com.example.rickandmorty.network.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterDetailResponse(
    val id : Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: GeneralDataResponse,
    val location: GeneralDataResponse,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
) : Parcelable