package com.uc.week4retrofit.view


import android.media.MediaPlayer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.uc.week4retrofit.adapter.GenreAdapter
import com.uc.week4retrofit.adapter.LanguageAdapter
import com.uc.week4retrofit.adapter.ProductionAdapter
import com.uc.week4retrofit.databinding.ActivityMovieDetailBinding
import com.uc.week4retrofit.helper.Const
import com.uc.week4retrofit.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.os.AsyncTask
import android.util.Log
import com.uc.week4retrofit.adapter.CountryAdapter


@AndroidEntryPoint
class MovieDetail : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var viewModel2: MoviesViewModel
    private lateinit var viewModel3: MoviesViewModel
    private lateinit var viewModel4: MoviesViewModel
    private lateinit var viewModelcountry: MoviesViewModel
    var player: MediaPlayer? = null
    private lateinit var adaptergen: GenreAdapter
    private lateinit var adapterprod: ProductionAdapter
    private lateinit var adapterlang: LanguageAdapter
    private lateinit var adaptercountry: CountryAdapter
//




    override fun onCreate(savedInstanceState: Bundle?) {
        player = MediaPlayer.create(this, com.uc.week4retrofit.R.raw.songkecil);
        AsyncTask.execute(){
//sudah berjalan, jika dicoba di hp. kalau emulator laptop tidak muncul suara
            player?.start()
            //Toast.makeText(this, "Movie id= ${movieid} ", Toast.LENGTH_LONG).show()
            Log.e("mskamda","msadma")//coba
        }

        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val movieid = intent.getIntExtra("movie_id", 0)
//        Toast.makeText(applicationContext, "Movie id= ${movieid} ", Toast.LENGTH_SHORT).show()

// backdrop
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel.getMovieDetail(Const.API_KEY, movieid)
        viewModel.movieDetails.observe(this, Observer { response ->
            binding.tvTitleMovieDetail.apply {
                text = response.title
            }

    Glide.with(applicationContext)
        .load(Const.IMG_URL + response.backdrop_path)
        .into(binding.imgPosterMovieDetail)


            binding.tvDescription.apply {
                text = response.overview
            }



        }
            )
// genre
        viewModel2 = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel2.getGenre(Const.API_KEY, movieid)
        viewModel2.genre.observe(this, Observer { response ->
            binding.rvGenre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adaptergen = GenreAdapter(response)
            binding.rvGenre.adapter = adaptergen
        }
        )

        //production company
        viewModel3 = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel3.getProduction(Const.API_KEY, movieid)
        viewModel3.production.observe(this, Observer { response ->
            binding.rvProductionCompany.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapterprod = ProductionAdapter(response)
            binding.rvProductionCompany.adapter = adapterprod
        }
        )

        //language
        viewModel4 = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel4.getLanguage(Const.API_KEY, movieid)
        viewModel4.language.observe(this, Observer { response ->
            binding.rvLanguage.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapterlang = LanguageAdapter(response)
            binding.rvLanguage.adapter = adapterlang

        }
        )

        //country
        viewModelcountry = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModelcountry.getCountry(Const.API_KEY, movieid)
        viewModel4.country.observe(this, Observer { response ->
            binding.rvCountry.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adaptercountry = CountryAdapter(response)
            binding.rvCountry.adapter = adaptercountry

        }
        )
        player?.pause()
//        player = MediaPlayer.create(this, com.uc.week4retrofit.R.raw.songkecil);
//        player?.start()



//doAsync{
//    player = MediaPlayer.create(this, com.uc.week4retrofit.R.raw.songkecil);
//    player?.start()
//}.execute()



      //  binding.indPosterMovieDetail.setImageURI("moviesViewModel._movieDetails.value?.poster_path.toString()")

    }



}



