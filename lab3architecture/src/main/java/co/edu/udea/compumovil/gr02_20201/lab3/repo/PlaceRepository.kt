package co.edu.udea.compumovil.gr02_20201.lab3.repo

import androidx.lifecycle.LiveData
import co.edu.udea.compumovil.gr02_20201.lab3.data.dao.PlaceDao
import co.edu.udea.compumovil.gr02_20201.lab3.data.database.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.Place

class PlaceRepository(private val db: LabTresDB, private val placeDao: PlaceDao) {
    val places: LiveData<List<Place>> = placeDao.getAllPlaces()

    fun getPL() = db.plcDao().getPlaces()

    suspend fun insert(place: Place) {
        placeDao.insert(place)
    }
}