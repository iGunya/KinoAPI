package com.example.kinoapi.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.kinoapi.Network
import com.example.kinoapi.chose.FilmDetailsFragment
import com.example.kinoapi.databinding.FragmentStartPageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopFilmFragment: Fragment() {
    private var _binding: FragmentStartPageBinding? = null
    private val binding get() = _binding!!


    private val top250FrameAdapter  = TopFrameAdapter(this::onFilmClick)
    private val top100FrameAdapter  = TopFrameAdapter(this::onFilmClick)
    private val topAwaitFrameAdapter  = TopFrameAdapter(this::onFilmClick)
    @IdRes
    private var conteinerResId:Int = 0

    val TOP250 = "TOP_250_BEST_FILMS"
    val TOP100 = "TOP_100_POPULAR_FILMS"
    val TOP_AWAIT = "TOP_AWAIT_FILMS"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        container?.let { conteinerResId = it.id }
        _binding = FragmentStartPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.top250Film.topRecycler.adapter = top250FrameAdapter
        val top250FilmCall = Network.apiService.getTop(TOP250)
        top250FilmCall.enqueue(object : Callback<RestTop> {
            override fun onResponse(
                call: Call<RestTop>,
                response: Response<RestTop>,
            ){
                val topData = response.body() ?:return
                val data = topData.top.map {
                    it
                }
                top250FrameAdapter.setItems(data)
            }
            override fun onFailure(call: Call<RestTop>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })
        binding.top100Film.topRecycler.adapter= top100FrameAdapter
        val top100FilmCall = Network.apiService.getTop(TOP100)
        top100FilmCall.enqueue(object : Callback<RestTop> {
            override fun onResponse(
                call: Call<RestTop>,
                response: Response<RestTop>,
            ){
                val topData: RestTop = response.body() ?:return
                top100FrameAdapter.setItems(topData.top)
            }
            override fun onFailure(call: Call<RestTop>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })
        binding.topAwaitFilm.topRecycler.adapter= topAwaitFrameAdapter
        val topAwaitFilmCall = Network.apiService.getTop(TOP_AWAIT)
        topAwaitFilmCall.enqueue(object : Callback<RestTop> {
            override fun onResponse(
                call: Call<RestTop>,
                response: Response<RestTop>,
            ){
                val topData = response.body() ?:return
                val data = topData.top.map {
                    it
                }
                topAwaitFrameAdapter.setItems(data)
            }
            override fun onFailure(call: Call<RestTop>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun onFilmClick(filmId:Int){
        parentFragmentManager.beginTransaction()
            .replace(conteinerResId, FilmDetailsFragment(filmId))
            .addToBackStack(null)
            .commit()
    }
}