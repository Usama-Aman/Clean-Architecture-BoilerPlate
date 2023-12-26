package com.sam.cleanarchitecturekickstart.data.repository

import com.sam.cleanarchitecturekickstart.data.remote.dto.ApiService
import com.sam.cleanarchitecturekickstart.domain.repository.Repository
import io.ktor.client.HttpClient
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService, // For Retrofit
    private val httpClient: HttpClient // For Ktor
) : Repository {


    //region Ktor Client Api call
//    companion object {
//        const val NOW_PLAYING_MOVIES_URL = "${Constants.BASE_URL}/now_playing"
//    }
//
//    override suspend fun getCurrentMovies(): NowPlayingMoviesDTO {
//        return httpClient.get(NOW_PLAYING_MOVIES_URL).body()
//    }

    //endregion Ktor Client Api call

    //region Retrofit client api call

    // Implement the same as use case for ktor client used

    //endregion Retrofit client api call

}