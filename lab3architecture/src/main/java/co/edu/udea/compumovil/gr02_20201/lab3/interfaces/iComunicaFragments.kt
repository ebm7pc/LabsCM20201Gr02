package co.edu.udea.compumovil.gr02_20201.lab3.interfaces

import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.Place

interface iComunicaFragments {
    //esta interface se encarga de realizar la comunicacion entre la lista de lugares y el detalle
    fun onClickSendPlace(place: Place) //se transportara un objeto de tipo lugar
    //(En la clase Lugar se implementa Serializable para poder transportar un objeteo a otro)
}