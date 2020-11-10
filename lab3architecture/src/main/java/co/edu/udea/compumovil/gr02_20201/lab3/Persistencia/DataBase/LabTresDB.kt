package co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import co.edu.udea.compumovil.gr02_20201.lab3.Descriptions
import co.edu.udea.compumovil.gr02_20201.lab3.Entidades.Lugar
import co.edu.udea.compumovil.gr02_20201.lab3.LongDescriptions
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.PlaceDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.Place
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.User
import co.edu.udea.compumovil.gr02_20201.lab3.R
import co.edu.udea.compumovil.gr02_20201.lab3.ioThread

@Database(entities = [User::class, Place::class], version = 1)
abstract class LabTresDB : RoomDatabase(){
    abstract fun usuarioDao(): UserDao
    abstract fun lugarDao(): PlaceDao

    companion object{
        @Volatile private var INSTANCE: LabTresDB?= null

        fun getInstance(context: Context): LabTresDB= INSTANCE?: synchronized(this){
            INSTANCE?: buildDatabase(context).also { INSTANCE= it}
        }

        private fun buildDatabase(context: Context)= Room.databaseBuilder(context.applicationContext, LabTresDB::class.java, "miBD.db")
        //Prepoblar base de datos después de que onCreate se haya llamado
            .addCallback(object: Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    //inserta datos en el hilo IO
                    ioThread{
                        getInstance(context).lugarDao().insertAll(DATOS_PREVIOS)
                    }
                }
            }).build()
        val DATOS_PREVIOS= listOf(
            Place(1, "Santorini", Descriptions().santorini, LongDescriptions().santorini, R.drawable.santorini1),
            Place(2, "Naxos", Descriptions().naxos, LongDescriptions().naxos, R.drawable.naxos1),
            Place(3, "Rodas", Descriptions().rodas, LongDescriptions().rodas, R.drawable.rodas1),
            Place(4, "Myconos", Descriptions().miconos, LongDescriptions().miconos, R.drawable.mykonos1),
            Place(5, "Olimpia", Descriptions().olimpia, LongDescriptions().olimpia, R.drawable.olimpia1),
        )
    }
}