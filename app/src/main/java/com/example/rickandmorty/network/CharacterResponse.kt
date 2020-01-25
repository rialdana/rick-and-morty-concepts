package com.example.rickandmorty.network

import com.squareup.moshi.Json

data class CharacterResponse(
    val info : InfoResponse,
    @Json(name = "results")var charactersList: ArrayList<CharacterInfoResponse>
)