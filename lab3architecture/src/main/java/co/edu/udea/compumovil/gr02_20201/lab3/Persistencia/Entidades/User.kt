package co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey
    val email: String,
    val pwd: String,
    val name: String
)