package co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import co.edu.udea.compumovil.gr02_20201.lab3.Descriptions
import co.edu.udea.compumovil.gr02_20201.lab3.LongDescriptions
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.PlaceDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.Place
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.User
import co.edu.udea.compumovil.gr02_20201.lab3.R
import co.edu.udea.compumovil.gr02_20201.lab3.ioThread
import kotlinx.coroutines.CoroutineScope

@Database(entities = [User::class, Place::class], version = 1)
abstract class LabTresDB : RoomDatabase(){
    abstract fun usuarioDao(): UserDao
    abstract fun lugarDao(): PlaceDao

    /*
     *singleton para evitar varias instancias de la bd
     */
    companion object{
        @Volatile
        private var INSTANCE: LabTresDB?= null
        fun getDatabaseInstance(context: Context):LabTresDB{
            synchronized(this){
                var instance= INSTANCE
                if(instance==null){
                    instance= Room.databaseBuilder(context.applicationContext, LabTresDB::class.java, "mi_db").allowMainThreadQueries().build()
                }
                return instance
            }
        }
    }
    //fin de companion object
}