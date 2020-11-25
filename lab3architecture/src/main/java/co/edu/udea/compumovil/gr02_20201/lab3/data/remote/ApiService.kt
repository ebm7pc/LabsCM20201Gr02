package co.edu.udea.compumovil.gr02_20201.lab3.data.remote

import android.telecom.Call
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.Place
import retrofit2.http.GET

interface ApiService {
    @GET("places")
    suspend fun requestPlaces(): List<Place>

}