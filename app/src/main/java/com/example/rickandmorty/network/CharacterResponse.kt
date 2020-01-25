package com.example.rickandmorty.network

data class CharacterResponse(
    val info : InfoResponse,
    var results: List<CharacterInfoResponse>
)