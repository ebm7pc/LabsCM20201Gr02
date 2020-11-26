package co.edu.udea.compumovil.gr02_20201.lab3.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table WHERE user_email = :e_mail and user_pwd = :password")
    fun getUser(e_mail: String, password:String): User

    @Query("SELECT * FROM user_table WHERE user_email = :e_mail and user_pwd = :password")
    fun bringUser(e_mail: String, password: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(usuario: User)

    @Update
    suspend fun update(usuario: User)

    @Delete
    suspend fun delete(usuario: User)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(usuario: User)

}