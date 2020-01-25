package com.example.rickandmorty.network.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeneralDataResponse(
    var name: String,
    val url: String
) : Parcelable