package co.edu.udea.compumovil.gr02_20201.lab3.viewmodel

import android.content.Context
import androidx.lifecycle.*
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab3.data.dao.PlaceDao
import co.edu.udea.compumovil.gr02_20201.lab3.data.database.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.Place
import co.edu.udea.compumovil.gr02_20201.lab3.repo.PlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class PlaceViewModel(private var repository: PlaceRepository): ViewModel() {
//class PlaceViewModel(application: Application): AndroidViewModel(application) {
    private lateinit var dataBase: LabTresDB
    private lateinit var placeDao:PlaceDao
    private lateinit var listaLugares: ArrayList<Place>

    init {
        listaLugares= ArrayList()
    }

    fun insert(place: Place)= viewModelScope.launch(Dispatchers.IO){
        repository.insert(place)
    }

    fun cargarLista(context: Context):ArrayList<Place>{
        var nombre: String?
        var desC: String?
        var desL: String?
        var img: Int
        listaLugares=ArrayList()
        dataBase= Room.databaseBuilder(context.applicationContext, LabTresDB::class.java, "mi_db").allowMainThreadQueries().build()
        placeDao= dataBase.lugarDao()
        val lugares= placeDao.getPlaces()
        val tama= placeDao.getPlaces().size
        for (i in 0 until tama){
            nombre= lugares[i].name
            desC= lugares[i].short_des
            desL= lugares[i].long_desc
            img= lugares[i].img
            listaLugares.add(Place(0,nombre, desC, desL, img))
        }
        return listaLugares
    }

}