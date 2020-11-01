package co.edu.udea.compumovil.grXX_20201.lab3.Entidades

import java.io.Serializable

class Persona : Serializable {
    var nombre: String? = null
    var fechanacimiento: String? = null
    var imagenid = 0

    constructor()
    constructor(nombre: String?, fechanacimiento: String?, imagenid: Int) {
        this.nombre = nombre
        this.fechanacimiento = fechanacimiento
        this.imagenid = imagenid
    }
}