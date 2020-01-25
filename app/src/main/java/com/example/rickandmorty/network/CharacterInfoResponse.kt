package com.example.rickandmorty.network

data class CharacterInfoResponse(
    val id : Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginResponse,
    val location: OriginResponse,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)