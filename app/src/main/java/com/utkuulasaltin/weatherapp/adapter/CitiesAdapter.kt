package com.utkuulasaltin.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utkuulasaltin.weatherapp.R
import com.utkuulasaltin.weatherapp.data.CityModel

class CitiesAdapter(
    private val cityList: MutableList<CityModel>,
    private val listener: CitiesListener
) :
    RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return CitiesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        holder.bind(cityList[position], listener)
    }

    class CitiesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvCity = view.findViewById<TextView>(R.id.tv_row_item)
        private val ivCity = view.findViewById<ImageView>(R.id.iv_row_item)

        fun bind(city: CityModel, listener: CitiesListener) {
            tvCity.text = city.name
            ivCity.setImageResource(city.image)

            itemView.setOnClickListener{
                listener.onClicked(city)
            }
        }

    }

}

interface CitiesListener {
    fun onClicked(city: CityModel)
}