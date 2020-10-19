package co.edu.udea.compumovil.gr02_20201.lab2.EntidadesYDaos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val email:String,
    val pwd: String
)