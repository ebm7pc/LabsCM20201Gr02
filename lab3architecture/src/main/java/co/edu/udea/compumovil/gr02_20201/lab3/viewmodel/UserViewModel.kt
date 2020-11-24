package co.edu.udea.compumovil.gr02_20201.lab3.viewmodel

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab3.Descriptions
import co.edu.udea.compumovil.gr02_20201.lab3.LongDescriptions
import co.edu.udea.compumovil.gr02_20201.lab3.data.dao.PlaceDao
import co.edu.udea.compumovil.gr02_20201.lab3.data.dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab3.data.database.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.Place
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.User
import co.edu.udea.compumovil.gr02_20201.lab3.R
import co.edu.udea.compumovil.gr02_20201.lab3.repo.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository): ViewModel() {
//class UserViewModel(application: Application): AndroidViewModel(application) {
    var username= ObservableField("")
    var userpwd= ObservableField("")
    var resultData= MutableLiveData<String>()
    private lateinit var placeDao: PlaceDao
    private lateinit var userDao: UserDao
    private lateinit var dataBase: LabTresDB

    fun insertaDesdeVM(){
        //repository.insertar(user)
        Log.d("Mensaje","entró a la función del view model")
    }

    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO) {
            repository.insertaUsuario(user)
        }

    fun userLogin(usernameP:String,userpwdP:String)= repository.getUser(usernameP,userpwdP)

    fun setViewModelParam(context: Context):UserViewModelFactory{
        dataBase= Room.databaseBuilder(context.applicationContext, LabTresDB::class.java, "mi_db").allowMainThreadQueries().build()
        userDao= dataBase.usuarioDao()
        val repository= UserRepository(dataBase,userDao)
        val factory= UserViewModelFactory(repository)
        return factory
    }

    fun guardarListaLugares(context: Context){
        dataBase= Room.databaseBuilder(context.applicationContext, LabTresDB::class.java, "mi_db").allowMainThreadQueries().build()
        placeDao= dataBase.lugarDao()
        val lugar = listOf<Place>(
            Place(1,"Santorini", Descriptions().santorini, LongDescriptions().santorini, R.drawable.santorini1),
            Place(2,"Naxos", Descriptions().naxos, LongDescriptions().naxos, R.drawable.naxos1),
            Place(3,"Rodas", Descriptions().rodas, LongDescriptions().rodas, R.drawable.rodas1),
            Place(4,"Mykonos", Descriptions().miconos, LongDescriptions().miconos, R.drawable.mykonos1),
            Place(5,"Olimpia", Descriptions().olimpia, LongDescriptions().olimpia, R.drawable.olimpia1)
        )
        if (esTablaLugaresVacia()){ placeDao.insertAll(lugar)}
    }

    fun esTablaLugaresVacia():Boolean{
        var retorno=false
        val lugar= placeDao.getPlaces()
        if (lugar.isEmpty()){retorno=true}
        return retorno
    }
}
