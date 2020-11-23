package co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.Place

@Dao
interface PlaceDao {

    @Query("SELECT * FROM place_table")
    fun getPlaces(): Array<Place>

    @Query("SELECT * FROM place_table")
    fun getAllPlaces():LiveData<List<Place>>//No requiere corrutina debido a que con Room se retorna un LiveData desde este nivel

    //@Query("SELECT * FROM Place")
    //LiveData<List<Place>> getAllPlaces

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(lugar: Place)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(lugar: List<Place>)

    @Update
    suspend fun update(lugar: Place)

    @Delete
    suspend fun delete(lugar: Place)

}