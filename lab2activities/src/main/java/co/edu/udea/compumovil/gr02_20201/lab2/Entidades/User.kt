package co.edu.udea.compumovil.gr02_20201.lab2.Entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val email:String,
    val pwd: String,
    val name:String
)