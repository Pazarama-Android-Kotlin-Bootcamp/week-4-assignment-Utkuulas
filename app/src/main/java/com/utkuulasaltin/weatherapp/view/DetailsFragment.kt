package com.utkuulasaltin.weatherapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.utkuulasaltin.weatherapp.R
import com.utkuulasaltin.weatherapp.data.CityModel
import com.utkuulasaltin.weatherapp.data.api.ApiClient
import com.utkuulasaltin.weatherapp.data.model.OneCall
import com.utkuulasaltin.weatherapp.data.model.Temp
import com.utkuulasaltin.weatherapp.data.model.Weather
import com.utkuulasaltin.weatherapp.data.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsFragment : Fragment() {

    private lateinit var navController: NavController

    private var tempsList: MutableList<Temp> = mutableListOf()
    private var weathersList: MutableList<Weather> = mutableListOf()

    private var cityLat: Double = 0.00
    private var cityLon: Double = 0.00

    private lateinit var tvCity: TextView
    private lateinit var ivIconDay0: ImageView
    private lateinit var tvWeatherDay0: TextView

    private lateinit var tvDay1: TextView
    private lateinit var ivIconDay1: ImageView
    private lateinit var tvWeatherMorningDay1: TextView
    private lateinit var tvWeatherNightDay1: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController()

        //Provides to handling onBackPressed actions
        activity?.onBackPressedDispatcher?.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navController.navigate(R.id.action_detailsFragment_to_listFragment)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       setupViews(view)
    }

    private fun setupViews(view: View) {
        tvCity = view.findViewById(R.id.tv_details_city)
        tvWeatherDay0 = view.findViewById(R.id.tv_day0_weather)

        arguments?.let {
            val cityData = it.getString("cityModel")

            cityData?.let { safeCityData ->
                val city = CityModel.fromJson(safeCityData)

                tvCity.text = city.name

                cityLat = city.latitude
                cityLon = city.longitude
            }

            var value = getOneCall(cityLat,cityLon)

            if (value == 0) {
                Log.d("Return Value", tempsList.size.toString())
            }

        }

    }

    private fun getOneCall(lat: Double, lon: Double) : Int {

        ApiClient.getApiService()
            .getOneCallWeather(lat, lon, Constants.LANGUAGE, Constants.EXCLUDE, Constants.UNITS)
            .enqueue(object : Callback<OneCall> {
                override fun onResponse(call: Call<OneCall>, response: Response<OneCall>) {

                    if(response.isSuccessful) {
                        val oneCall = response.body()
                        oneCall?.let {
                            it.daily?.forEach { daily ->
                                daily.temp?.let { temps -> tempsList.add(temps) }
                            }
                            it.daily?.forEach { daily ->
                                daily.weather?.let { weathers -> weathersList.addAll(0, weathers) }
                            }
                        }
                        Log.d("Succesfull", tempsList.toString())
                    }
                }

                override fun onFailure(call: Call<OneCall>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
        return 0
    }
}