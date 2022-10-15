package com.utkuulasaltin.weatherapp.data

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityModel(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val image: Int
) : Parcelable {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(jsonValue: String): CityModel {
            return Gson().fromJson(jsonValue, CityModel::class.java)
        }
    }
}

val mockCityData = mutableListOf<CityModel>(
    CityModel(
        "1",
        "ANKARA",
        39.9272,
        32.8644,
        2131165270
    ),

    CityModel(
        "2",
        "ANTALYA",
        36.892,
        30.6944,
        2131165271
        ),

    CityModel(
        "3",
        "ÇANAKKALE",
        40.1477,
        26.4106,
        2131165282
        ),

    CityModel(
        "4",
        "İSTANBUL",
        41.0122,
        28.976,
        2131165300
        ),

    CityModel(
        "5",
        "İZMİR",
        38.4333,
        27.15,
        2131165301
        ),

    CityModel(
        "6",
        "MERSİN",
        36.8,
        34.6333,
        2131165320
        ),

    CityModel(
        "7",
        "SAMSUN",
        41.2903,
        36.3336,
        2131165344
        ),

    CityModel(
        "8",
        "TRABZON",
        41.0052,
        39.7179,
        2131165349
        ),
)