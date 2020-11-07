package co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao

import androidx.room.*
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.Place

@Dao
interface PlaceDao {

    @Query("SELECT * FROM Place")
    fun getPlaces(): Array<Place>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(lugar: Place)

    @Update
    suspend fun update(lugar: Place)

    @Delete
    suspend fun delete(lugar: Place)

}