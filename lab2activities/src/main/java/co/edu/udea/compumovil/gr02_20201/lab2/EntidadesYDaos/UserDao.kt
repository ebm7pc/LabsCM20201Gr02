package co.edu.udea.compumovil.gr02_20201.lab2.EntidadesYDaos

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAll():List<User>

    @Query("SELECT * FROM User WHERE id = :id")
    fun getById(id:Int):User

    @Update
    fun update(usuario:User)

    @Insert
    fun insert(usuario: User)

    @Delete
    fun delete(usuario: User)
}