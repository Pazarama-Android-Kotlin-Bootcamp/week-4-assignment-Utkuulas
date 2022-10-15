package com.utkuulasaltin.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.utkuulasaltin.weatherapp.data.api.ApiClient
import com.utkuulasaltin.weatherapp.data.model.OneCall
import com.utkuulasaltin.weatherapp.data.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavController()
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
    }
}