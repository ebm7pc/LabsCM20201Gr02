package co.edu.udea.compumovil.gr02_20201.lab3.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.edu.udea.compumovil.gr02_20201.lab3.data.dao.PlaceDao
import co.edu.udea.compumovil.gr02_20201.lab3.data.dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.Place
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.User
import kotlinx.coroutines.CoroutineScope

@Database(entities = [User::class, Place::class], version = 1)
abstract class LabTresDB : RoomDatabase(){
    abstract fun usrDao(): UserDao
    abstract fun plcDao(): PlaceDao

    /*
     *singleton para evitar varias instancias de la bd
     */
    companion object {
        @Volatile
        private var INSTANCE: LabTresDB? = null
        fun getDatabaseInstance(context: Context, scope: CoroutineScope): LabTresDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LabTresDB::class.java,
                        "mi_db"
                    ).allowMainThreadQueries().build()
                }
                return instance
            }
        }
    }
    //fin de companion object
}