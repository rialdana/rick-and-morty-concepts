package com.example.rickandmorty.network

import com.example.rickandmorty.network.responses.CharacterDetailResponse
import com.example.rickandmorty.network.responses.CharactersResponse
import com.example.rickandmorty.network.responses.LocationsResponse
import com.example.rickandmorty.utils.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import java.util.concurrent.TimeUnit


/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Http client to set a timeout and to show the request and response JSON body
 */
val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val httpClient = OkHttpClient.Builder()
    .readTimeout(60, TimeUnit.SECONDS)
    .connectTimeout(20, TimeUnit.SECONDS)
    .addInterceptor(logging)
    .build()



/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(httpClient)
    .build()


/**
 * A public interface that exposes the apiCall methods
 */
interface ApiService {

    /*
     * This method returns a list of characters from the endpoint
     */
    @GET("character/")
    fun getCharactersAsync() : Deferred<CharactersResponse>

    /*
     * This method returns a list of characters from the endpoint,
     * it's url is passed as a parameter because this will be used for paging adapter.
     */
    @GET
    fun getPagedCharactersAsync(@Url url: String) : Deferred<CharactersResponse>

    /*
     * This method returns the specific characterId information
     * by sending the characterId as a parameter
     *
     * @param characterId
     * @return
     */
    @GET("character/{characterId}")
    fun getCharacterDetailAsync(@Path("characterId") characterId: Int) : Deferred<CharacterDetailResponse>

    /*
     * This method returns the list of locations from the endpoint
     */
    @GET("location/")
    fun getLocationsAsync() : Deferred<LocationsResponse>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object ShowApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java)}
}