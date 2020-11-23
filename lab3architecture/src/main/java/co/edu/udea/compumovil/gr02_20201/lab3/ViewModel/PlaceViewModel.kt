package co.edu.udea.compumovil.gr02_20201.lab3.ViewModel

import android.app.Application
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.*
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.PlaceDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.DataBase.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.Place
import co.edu.udea.compumovil.gr02_20201.lab3.Repositorio.PlaceRepository
import co.edu.udea.compumovil.gr02_20201.lab3.Repositorio.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

//class PlaceViewModel(private val repository: UserRepository): ViewModel() {
class PlaceViewModel(application: Application): AndroidViewModel(application) {
    private val repository: PlaceRepository
    val places: LiveData<List<Place>>
    val placeDao:PlaceDao
    private lateinit var listaLugares: ArrayList<Place>
    val inputNombreLugar= MutableLiveData<String>()
    val inputDescrCorta= MutableLiveData<String>()
    val inputDescrLarga= MutableLiveData<String>()
    val inputImg= MutableLiveData<Int>()

    init {
        placeDao= LabTresDB.getDatabaseInstance(application).lugarDao()
        repository= PlaceRepository(placeDao)
        places= repository.places
    }

    fun insert(place: Place)= viewModelScope.launch(Dispatchers.IO){
        repository.insert(place)
    }

    fun cargarLista(){
        var nombre: String?
        var desC: String?
        var desL: String?
        var img: Int
        //dataBase= Room.databaseBuilder(requireContext(), LabTresDB::class.java, "mi_db").allowMainThreadQueries().build()
        //placeDao= dataBase.lugarDao()
        val lugares= placeDao.getPlaces()
        val tama= placeDao.getPlaces().size
        //Toast.makeText(context, "el tamaño es " + tama, Toast.LENGTH_SHORT).show()
        for (i in 0 until tama){
            nombre= lugares[i].name
            desC= lugares[i].short_des
            desL= lugares[i].long_desc
            img= lugares[i].img
            listaLugares.add(Place(0,nombre, desC, desL, img))
        }
    }

}