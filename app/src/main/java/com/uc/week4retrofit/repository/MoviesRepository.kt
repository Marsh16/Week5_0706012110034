package com.uc.week4retrofit.repository

import com.uc.week4retrofit.retrofit.EndPointApi
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: EndPointApi){

    suspend fun getNowPlayingData(apiKey: String, language: String,
                                 page: Int) = api.getNowPlaying(apiKey,language,page)

    suspend fun getMovieDetailResults(apiKey: String, movieId: Int) = api.getMovieDetails(movieId, apiKey)
//    suspend fun getGenreResults(apiKey: String, language: String,
//                                page: Int) = api.getGenre(apiKey,language,page)

}