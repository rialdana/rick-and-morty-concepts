package com.example.rickandmorty.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.network.responses.CharacterDetailResponse
import com.example.rickandmorty.network.responses.GeneralDataResponse

@Entity
data class DatabaseCharacterDetail constructor(
    @PrimaryKey
    val id : Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val originName: String,
    val originUrl:String,
    val locationName: String,
    val locationUrl: String,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

fun List<DatabaseCharacterDetail>.asDomainModel(): List<CharacterDetailResponse> {
    return map {
        CharacterDetailResponse(
            id = it.id,
            name = it.name,
            status = it.status,
            species = it.species,
            type = it.type,
            gender = it.gender,
            origin = GeneralDataResponse(it.originName, it.originUrl),
            location = GeneralDataResponse(it.locationName, it.locationUrl),
            image = it.image,
            episode = it.episode,
            url = it.url,
            created = it.created
        )
    }
}