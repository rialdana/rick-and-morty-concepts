package com.example.rickandmorty.network.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterResponse(
    val info : InfoResponse,
    var results: List<CharacterInfoResponse>
) : Parcelable