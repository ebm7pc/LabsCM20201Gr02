package co.edu.udea.compumovil.gr02_20201.lab3.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import java.util.*

class PlaceViewModel(private var repository: PlaceRepository) : ViewModel() {
    //class PlaceViewModel(application: Application): AndroidViewModel(application) {
    private var apiService = RetrofitFactory.apiService()
    private lateinit var dataBase: LabTresDB
    private lateinit var placeDao: PlaceDao
    private var listPlaces: ArrayList<Place>
    private var places = MutableLiveData<List<Place>>()
    var wholePlaces: LiveData<List<Place>> = places
    val allPlaces: LiveData<List<Place>>

    init {
        getPosts()
        listPlaces = ArrayList()
        allPlaces = repository.places
    }

    fun insert(place: Place) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(place)
    }

    fun savePlaceList(context: Context) {
        dataBase = Room.databaseBuilder(context.applicationContext, LabTresDB::class.java, "mi_db")
            .allowMainThreadQueries().build()
        placeDao = dataBase.plcDao()
        val place = listOf<Place>(
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
        if (isPlacesListEmpty()) {
            placeDao.insertAll(place)
        }
    }

    fun isPlacesListEmpty(): Boolean {
        var ret = false
        val places = placeDao.getPlaces()
        if (places.isEmpty()) {
            ret = true
        }
        return ret
    }

    fun loadList(context: Context): ArrayList<Place> {
        var name: String?
        var Sdesc: String?
        var Ldesc: String?
        var img: Int
        listPlaces = ArrayList()
        dataBase = Room.databaseBuilder(context.applicationContext, LabTresDB::class.java, "mi_db")
            .allowMainThreadQueries().build()
        placeDao = dataBase.plcDao()
        val places = placeDao.getPlaces()
        val size = placeDao.getPlaces().size
        for (i in 0 until size) {
            name = places[i].name
            Sdesc = places[i].short_des
            Ldesc = places[i].long_desc
            img = places[i].img
            listPlaces.add(Place(0, name, Sdesc, Ldesc, img))
        }
        return listPlaces
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