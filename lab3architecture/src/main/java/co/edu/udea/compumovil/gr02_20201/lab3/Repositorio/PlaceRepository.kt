package co.edu.udea.compumovil.gr02_20201.lab3.Repositorio

import androidx.lifecycle.LiveData
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.PlaceDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.Place

class PlaceRepository(private val placeDao: PlaceDao) {
    val places: LiveData<List<Place>> = placeDao.getAllPlaces()

    suspend fun insert(place: Place){
        placeDao.insert(place)
    }
}