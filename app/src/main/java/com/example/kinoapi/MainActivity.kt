package com.example.kinoapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kinoapi.databinding.ActivityMainBinding
import com.example.kinoapi.start.TopFilmFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val chose = arrayOf(301,749540,263531,538225,255129)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(binding.root.id, TopFilmFragment())
            .commit()
    }
}