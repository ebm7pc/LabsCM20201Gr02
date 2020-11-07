package co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Place(
    @PrimaryKey
    val id: Int,
    val name: String,
    val short_des: String,
    val long_desc: String,
    val img: Int
)