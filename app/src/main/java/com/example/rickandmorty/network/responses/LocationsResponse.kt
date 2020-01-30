package com.example.rickandmorty.network.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationsResponse(
    val info: InfoResponse,
    val results: List<LocationDetailResponse>
) : Parcelable