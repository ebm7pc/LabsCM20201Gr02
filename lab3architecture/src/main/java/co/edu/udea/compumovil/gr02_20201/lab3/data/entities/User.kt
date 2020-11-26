package co.edu.udea.compumovil.gr02_20201.lab3.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey
    @ColumnInfo(name = "user_email")
    val email: String,
    @ColumnInfo(name = "user_pwd")
    val pwd: String,
    @ColumnInfo(name = "user_name")
    val name: String
)