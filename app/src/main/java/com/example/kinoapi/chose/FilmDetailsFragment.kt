package com.example.kinoapi.chose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.kinoapi.Network
import com.example.kinoapi.databinding.FragmentFilmDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmDetailsFragment(val _id:Int) : Fragment() {

    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!

    private val filmFramesAdapter = FilmFramesAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.filmFramesView.adapter = filmFramesAdapter
        val filmDataCall = Network.apiService.getFilmData(_id)
        filmDataCall.enqueue(object : Callback<RestFilmDataResponse> {
            override fun onResponse(
                call: Call<RestFilmDataResponse>,
                response: Response<RestFilmDataResponse>,
            ) {
                val filmData = response.body() ?: return
                binding.filmDetailsLayout.filmNameRus.text = filmData.film.nameRus
                binding.filmDetailsLayout.filmNameEng.text = filmData.film.nameEng
                binding.filmDetailsLayout.filmSlogan.text = filmData.film.slogan
                binding.filmDetailsLayout.filmYear.text = filmData.film.year
                binding.filmDetailsLayout.filmLength.text = filmData.film.filmLength
                binding.filmDescription.text = filmData.film.disctiption
                Glide.with(this@FilmDetailsFragment).load(filmData.film.Preview)
                    .into(binding.filmPosterLayout.filmPoster)
                binding.filmDetailsLayout.filmLength.text = filmData.film.filmLength
                var temp = ""
                for (t in filmData.film.genres) {
                    temp = temp + t.genre + ", "
                }
                binding.filmDetailsLayout.filmGenres.text = temp
            }

            override fun onFailure(call: Call<RestFilmDataResponse>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })
        val filmPosterCall = Network.apiService.getFilmPoster(_id)
        filmPosterCall.enqueue(object : Callback<RestFilmPoster> {
            override fun onResponse(
                call: Call<RestFilmPoster>,
                response: Response<RestFilmPoster>
            ) {
                val filmData = response.body() ?: return
                //binding.filmDetailsLayout.filmNameRus.text = filmData.film.nameRus
                var temp: ArrayList<String> = arrayListOf()
                for (t in filmData.frames) {
                    temp.add(t.preview)
                }

                val a = filmData.frames.map {
                    it.preview
                }
                filmFramesAdapter.setItems(a)

            }

            override fun onFailure(call: Call<RestFilmPoster>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })
    }
}