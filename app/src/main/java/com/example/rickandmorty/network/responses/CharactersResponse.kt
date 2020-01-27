package com.example.rickandmorty.network.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharactersResponse(
    val info : InfoResponse,
    var results: List<CharacterDetailResponse>
) : Parcelable