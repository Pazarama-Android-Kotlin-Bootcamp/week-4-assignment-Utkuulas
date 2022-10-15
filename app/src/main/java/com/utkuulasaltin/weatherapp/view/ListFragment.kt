package com.utkuulasaltin.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.utkuulasaltin.weatherapp.R
import com.utkuulasaltin.weatherapp.adapter.CitiesAdapter
import com.utkuulasaltin.weatherapp.adapter.CitiesListener
import com.utkuulasaltin.weatherapp.data.CityModel
import com.utkuulasaltin.weatherapp.data.mockCityData

class ListFragment : Fragment(), CitiesListener {
    private lateinit var rvCityList: RecyclerView
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        rvCityList = view.findViewById(R.id.rv_list)
        setupAdapter()
    }

    private fun setupAdapter() {
        rvCityList.adapter = CitiesAdapter(mockCityData, this@ListFragment)
    }

    override fun onClicked(city: CityModel) {
        navController.navigate(R.id.action_listFragment_to_detailsFragment, Bundle().apply {
            putString("cityModel", city.toJson())
        })
    }
}