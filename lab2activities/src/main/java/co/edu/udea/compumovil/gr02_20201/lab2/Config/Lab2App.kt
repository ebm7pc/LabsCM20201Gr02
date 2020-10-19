package co.edu.udea.compumovil.gr02_20201.lab2.Config

import android.app.Application
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab2.DataBase.LaboratorioDosDB

//Prueba base de datos
class Lab2App : Application(){
    val room=Room.databaseBuilder(this, LaboratorioDosDB::class.java, "usuario").build()
}

