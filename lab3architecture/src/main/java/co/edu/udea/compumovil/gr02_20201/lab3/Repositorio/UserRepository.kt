package co.edu.udea.compumovil.gr02_20201.lab3.Repositorio

import android.app.Application
import androidx.lifecycle.LiveData
import co.edu.udea.compumovil.gr02_20201.lab3.Fragments.RegistryFragment
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.PlaceDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.DataBase.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.Place
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.User

//class UserRepository (private val daoUser: UserDao, private val daoPlace: PlaceDao){
//class UserRepository (private val daoUser: UserDao){
class UserRepository (private val db:LabTresDB, private val daoUser:UserDao){

    suspend fun insertaUsuario(user: User) {//=db.usuarioDao().getUser(mail,pwd)
        daoUser.insertUser(user)
    }

    fun getUser(mail:String,pwd:String)= db.usuarioDao().getUser(mail,pwd)

}