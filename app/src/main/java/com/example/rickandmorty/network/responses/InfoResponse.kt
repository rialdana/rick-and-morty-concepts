package com.example.rickandmorty.network.responses

data class InfoResponse(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)