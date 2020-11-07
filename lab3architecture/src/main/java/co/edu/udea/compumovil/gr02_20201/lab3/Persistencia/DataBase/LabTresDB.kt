package co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.PlaceDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.Place
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.User

@Database(entities = [User::class, Place::class], version = 1)
abstract class LabTresDB : RoomDatabase(){
    abstract fun usuarioDao(): UserDao
    abstract fun lugarDao(): PlaceDao
}