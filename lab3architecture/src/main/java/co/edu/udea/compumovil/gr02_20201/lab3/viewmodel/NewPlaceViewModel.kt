package co.edu.udea.compumovil.gr02_20201.lab3.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab3.data.database.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.Place
import co.edu.udea.compumovil.gr02_20201.lab3.repo.PlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewPlaceViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var repository: PlaceRepository

    fun savePlace(place: Place, context: Context) = viewModelScope.launch(Dispatchers.IO) {
        val dataBase =
            Room.databaseBuilder(context.applicationContext, LabTresDB::class.java, "mi_db")
                .allowMainThreadQueries().build()
        val placeDao = dataBase.plcDao()
        repository = PlaceRepository(dataBase, placeDao)
        repository.insert(place)
    }

    fun placeData(placeName: String, placeShortDesc: String, placeDesc: String, img: Int): Place {
        val place = Place(0, placeName, placeShortDesc, placeDesc, img)
        return place
    }

    fun processShortDescription(longDesc: String): String {
        return longDesc.substring(0, 20) + "..."
    }
}