package com.example.rickandmorty.network.responses

import android.os.Parcelable
import com.example.rickandmorty.database.DatabaseCharacterDetail
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharactersResponse(
    val info : InfoResponse,
    var results: List<CharacterDetailResponse>
) : Parcelable

fun CharactersResponse.asDatabaseModel(): Array<DatabaseCharacterDetail> {
    return results.map {
        DatabaseCharacterDetail(
            id = it.id,
            name = it.name,
            status = it.status,
            species = it.species,
            type = it.type,
            gender = it.gender,
            originName = it.origin.name,
            originUrl = it.origin.url,
            locationName = it.location.name,
            locationUrl = it.location.url,
            image = it.image,
            episode = it.episode,
            url = it.url,
            created = it.created
        )
    }.toTypedArray()
}