package com.example.rickandmorty.network.responses

data class CharacterResponse(
    val info : InfoResponse,
    var results: List<CharacterInfoResponse>
)