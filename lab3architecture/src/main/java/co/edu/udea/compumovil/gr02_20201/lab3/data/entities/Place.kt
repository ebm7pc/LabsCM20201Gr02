package co.edu.udea.compumovil.gr02_20201.lab3.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "place_table")
data class Place (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val short_des: String,
    val long_desc: String,
    val img: Int
): Serializable