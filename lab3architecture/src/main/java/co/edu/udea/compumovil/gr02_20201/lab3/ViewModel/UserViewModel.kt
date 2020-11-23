package co.edu.udea.compumovil.gr02_20201.lab3.ViewModel

import android.app.Application
import android.content.Context
import android.database.Observable
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.DataBase.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.User
import co.edu.udea.compumovil.gr02_20201.lab3.Repositorio.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class UserViewModel(private val repository: UserRepository): ViewModel() {
//class UserViewModel(application: Application): AndroidViewModel(application) {
    var username= ObservableField("")
    var userpwd= ObservableField("")
    var resultData= MutableLiveData<String>()

    fun insertaDesdeVM(){
        //repository.insertar(user)
        Log.d("Mensaje","entró a la función del view model")
    }
    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO) {
            repository.insertaUsuario(user)
        }

    //fun userLogin(usernameP:String,userpwdP:String)= viewModelScope.launch(Dispatchers.IO){
    //    repository.getUser(usernameP,userpwdP)
    //}

    fun userLogin(usernameP:String,userpwdP:String)= repository.getUser(usernameP,userpwdP)

    fun loginCall(usernameP:String,userpwdP:String)
    {
        username.set(usernameP)
        userpwd.set(userpwdP)
    }

    fun getResultLogin(): MutableLiveData<String>{
        return resultData
    }


}
