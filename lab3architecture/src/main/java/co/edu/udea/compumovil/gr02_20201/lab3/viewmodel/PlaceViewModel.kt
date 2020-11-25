package co.edu.udea.compumovil.gr02_20201.lab3.viewmodel

import android.content.Context
import androidx.lifecycle.*
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab3.Descriptions
import co.edu.udea.compumovil.gr02_20201.lab3.LongDescriptions
import co.edu.udea.compumovil.gr02_20201.lab3.R
import co.edu.udea.compumovil.gr02_20201.lab3.data.dao.PlaceDao
import co.edu.udea.compumovil.gr02_20201.lab3.data.database.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.Place
import co.edu.udea.compumovil.gr02_20201.lab3.data.remote.RetrofitFactory
import co.edu.udea.compumovil.gr02_20201.lab3.repo.PlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class PlaceViewModel(private var repository: PlaceRepository): ViewModel() {
    //class PlaceViewModel(application: Application): AndroidViewModel(application) {
    private var apiService = RetrofitFactory.apiService()
    private lateinit var dataBase: LabTresDB
    private lateinit var placeDao: PlaceDao
    private var listaLugares: ArrayList<Place>
    private var places = MutableLiveData<List<Place>>()
    var wholePlaces: LiveData<List<Place>> = places
    val allPlaces: LiveData<List<Place>>

    init {
        getPosts()
        listaLugares = ArrayList()
        allPlaces = repository.places
    }

    fun insert(place: Place) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(place)
    }

    fun guardarListaLugares(context: Context) {
        dataBase = Room.databaseBuilder(context.applicationContext, LabTresDB::class.java, "mi_db")
            .allowMainThreadQueries().build()
        placeDao = dataBase.lugarDao()
        val lugar = listOf<Place>(
            Place(
                1,
                "Santorini",
                Descriptions().santorini,
                LongDescriptions().santorini,
                R.drawable.santorini1
            ),
            Place(2, "Naxos", Descriptions().naxos, LongDescriptions().naxos, R.drawable.naxos1),
            Place(3, "Rodas", Descriptions().rodas, LongDescriptions().rodas, R.drawable.rodas1),
            Place(
                4,
                "Mykonos",
                Descriptions().miconos,
                LongDescriptions().miconos,
                R.drawable.mykonos1
            ),
            Place(
                5,
                "Olimpia",
                Descriptions().olimpia,
                LongDescriptions().olimpia,
                R.drawable.olimpia1
            )
        )
        if (esTablaLugaresVacia()) {
            placeDao.insertAll(lugar)
        }
    }

    fun esTablaLugaresVacia(): Boolean {
        var retorno = false
        val lugar = placeDao.getPlaces()
        if (lugar.isEmpty()) {
            retorno = true
        }
        return retorno
    }

    fun cargarLista(context: Context): ArrayList<Place> {
        var nombre: String?
        var desC: String?
        var desL: String?
        var img: Int
        listaLugares = ArrayList()
        dataBase = Room.databaseBuilder(context.applicationContext, LabTresDB::class.java, "mi_db")
            .allowMainThreadQueries().build()
        placeDao = dataBase.lugarDao()
        val lugares = placeDao.getPlaces()
        val tama= placeDao.getPlaces().size
        for (i in 0 until tama) {
            nombre = lugares[i].name
            desC = lugares[i].short_des
            desL = lugares[i].long_desc
            img = lugares[i].img
            listaLugares.add(Place(0, nombre, desC, desL, img))
        }
        return listaLugares
    }

    fun getPosts() {
        viewModelScope.launch {
            places.value = requestPlaces()
        }
    }

    private suspend fun requestPlaces(): List<Place> {
        return withContext(Dispatchers.IO) {
            apiService.requestPlaces()
        }
    }

}