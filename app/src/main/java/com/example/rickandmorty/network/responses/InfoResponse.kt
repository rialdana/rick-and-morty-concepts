package com.example.rickandmorty.network.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InfoResponse(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
) : Parcelable