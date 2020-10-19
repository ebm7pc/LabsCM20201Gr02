package co.edu.udea.compumovil.gr02_20201.lab2.Dao

import androidx.room.*
import co.edu.udea.compumovil.gr02_20201.lab2.Entidades.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE email = :e_mail and pwd = :password")
    fun getUser(e_mail: String, password:String): User

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(usuario: User)

    @Update
    suspend fun update(usuario: User)

    @Delete
    suspend fun delete(usuario: User)
}