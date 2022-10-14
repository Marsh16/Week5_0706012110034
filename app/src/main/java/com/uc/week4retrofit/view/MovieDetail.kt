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


@AndroidEntryPoint
class MovieDetail : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var viewModel2: MoviesViewModel
    private lateinit var viewModel3: MoviesViewModel
    private lateinit var viewModel4: MoviesViewModel
    var player: MediaPlayer? = null
    private lateinit var adapter: GenreAdapter
    private lateinit var adapter2: ProductionAdapter
    private lateinit var adapter4: LanguageAdapter
//




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        player = MediaPlayer.create(this, com.uc.week4retrofit.R.raw.songkecil);
        player?.pause()
        //jika tidak di pause audio akan terus bermain
        // kalau mau coba audio menyala atau tidak, line 46 di comment
        binding.rvGenre.visibility = View.VISIBLE
        binding.tvTitleMovieDetail.visibility = View.VISIBLE
        binding.imgPosterMovieDetail.visibility = View.VISIBLE
        binding.rvLanguage.visibility = View.VISIBLE
        binding.rvProductionCompany.visibility= View.VISIBLE
        binding.tvDescription.visibility = View.VISIBLE
        binding.progressBar.visibility= View.INVISIBLE
        binding.lang.visibility=View.VISIBLE

        val movieid = intent.getIntExtra("movie_id", 0)
//        Toast.makeText(applicationContext, "Movie id= ${movieid} ", Toast.LENGTH_SHORT).show()

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

        viewModel2 = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel2.getGenre(Const.API_KEY, movieid)
        viewModel2.genre.observe(this, Observer { response ->

            binding.rvGenre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapter = GenreAdapter(response)
            binding.rvGenre.adapter = adapter


        }
        )
        viewModel3 = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel3.getProduction(Const.API_KEY, movieid)
        viewModel3.production.observe(this, Observer { response ->

            binding.rvProductionCompany.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapter2 = ProductionAdapter(response)
            binding.rvProductionCompany.adapter = adapter2


        }
        )
        viewModel4 = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel4.getLanguage(Const.API_KEY, movieid)
        viewModel4.language.observe(this, Observer { response ->

            binding.rvLanguage.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapter4 = LanguageAdapter(response)
            binding.rvLanguage.adapter = adapter4


        }
        )
//        player = MediaPlayer.create(this, com.uc.week4retrofit.R.raw.songkecil);
//        player?.start()
        AsyncTask.execute(){
//sudah berjalan, jika dicoba di hp di emulator laptop tidak muncul suara, jika tidak di pause
    player?.start()
            //Toast.makeText(this, "Movie id= ${movieid} ", Toast.LENGTH_LONG).show()
            Log.e("mskamda","msadma")//coba
        }



//doAsync{
//    player = MediaPlayer.create(this, com.uc.week4retrofit.R.raw.songkecil);
//    player?.start()
//}.execute()



      //  binding.indPosterMovieDetail.setImageURI("moviesViewModel._movieDetails.value?.poster_path.toString()")

    }



}


