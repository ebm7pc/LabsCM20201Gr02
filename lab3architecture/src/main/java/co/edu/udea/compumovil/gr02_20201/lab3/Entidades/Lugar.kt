package co.edu.udea.compumovil.gr02_20201.lab3.Entidades

import java.io.Serializable

class Lugar : Serializable {
    var nombre: String? = null
    var descripCorta: String? = null
    var descripLarga: String? = null
    var imagenid = 0

    constructor()
    constructor(nombre: String?, descripCorta: String?, descripLarga: String?, imagenid: Int) {
        this.nombre = nombre
        this.descripCorta = descripCorta
        this.descripLarga = descripLarga
        this.imagenid = imagenid
    }
}