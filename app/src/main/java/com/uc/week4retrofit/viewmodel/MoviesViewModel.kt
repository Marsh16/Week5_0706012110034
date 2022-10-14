package com.uc.week4retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.week4retrofit.model.*
import com.uc.week4retrofit.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository): ViewModel() {
    //get now playing data
    val _nowPlaying: MutableLiveData<ArrayList<Result>> by lazy {
        MutableLiveData<ArrayList<Result>>()
    }

    val nowPlaying: LiveData<ArrayList<Result>>
        get() = _nowPlaying

    fun getNowPlaying(apiKey: String, language: String, page: Int) = viewModelScope.launch {
        repository.getNowPlayingData(apiKey, language, page).let { response ->
            if (response.isSuccessful) {
                _nowPlaying.postValue(
                    response.body()?.results as
                            ArrayList<Result>?
                )
            } else {
                Log.e("Get Data", "Failed!")
            }
        }
    }

    // get movie details
    val _movieDetails: MutableLiveData<MovieDetails> by lazy {
        MutableLiveData<MovieDetails>()
    }

    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetails



    fun getMovieDetail(apiKey: String, movieId: Int) = viewModelScope.launch {
        repository.getMovieDetailResults(apiKey, movieId).let { response ->
            if (response.isSuccessful) {

                _movieDetails.postValue(response.body() as MovieDetails)

            } else {
                Log.e("Get Movie Details Data", "Failed!")
            }
        }
    }

    // get genre
    val _genre: MutableLiveData<ArrayList<Genre>> by lazy {
        MutableLiveData<ArrayList<Genre>>()
    }

    val genre: LiveData<ArrayList<Genre>>
        get() = _genre

    fun getGenre(apiKey: String, movieId: Int) = viewModelScope.launch {
        repository.getMovieDetailResults(apiKey, movieId ).let { response ->
            if (response.isSuccessful) {
                _genre.postValue(
                    response.body()?.genres as
                            ArrayList<Genre>?
                )
            } else {
                Log.e("Get Data", "Failed!")
            }
        }
    }

    // get production
    val _production: MutableLiveData<ArrayList<ProductionCompany>> by lazy {
        MutableLiveData<ArrayList<ProductionCompany>>()
    }

    val production: LiveData<ArrayList<ProductionCompany>>
        get() = _production

    fun getProduction(apiKey: String, movieId: Int) = viewModelScope.launch {
        repository.getMovieDetailResults(apiKey, movieId ).let { response ->
            if (response.isSuccessful) {
                _production.postValue(
                    response.body()?.production_companies as
                            ArrayList<ProductionCompany>?
                )
            } else {
                Log.e("Get Data", "Failed!")
            }
        }
    }

    // get lang
    val _language: MutableLiveData<ArrayList<SpokenLanguage>> by lazy {
        MutableLiveData<ArrayList<SpokenLanguage>>()
    }

    val language: LiveData<ArrayList<SpokenLanguage>>
        get() = _language

    fun getLanguage(apiKey: String, movieId: Int) = viewModelScope.launch {
        repository.getMovieDetailResults(apiKey, movieId ).let { response ->
            if (response.isSuccessful) {
                _language.postValue(
                    response.body()?.spoken_languages as
                            ArrayList<SpokenLanguage>?
                )
            } else {
                Log.e("Get Data", "Failed!")
            }
        }
    }

    //get country
    // get lang
    val _country: MutableLiveData<ArrayList<ProductionCountry>> by lazy {
        MutableLiveData<ArrayList<ProductionCountry>>()
    }

    val country: LiveData<ArrayList<ProductionCountry>>
        get() = _country

    fun getCountry(apiKey: String, movieId: Int) = viewModelScope.launch {
        repository.getMovieDetailResults(apiKey, movieId ).let { response ->
            if (response.isSuccessful) {
                _country.postValue(
                    response.body()?.production_countries as
                            ArrayList<ProductionCountry>?
                )
            } else {
                Log.e("Get Data", "Failed!")
            }
        }
    }
}