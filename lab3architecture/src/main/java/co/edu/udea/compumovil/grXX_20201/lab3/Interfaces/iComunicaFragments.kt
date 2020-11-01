package co.edu.udea.compumovil.grXX_20201.lab3.Interfaces

import co.edu.udea.compumovil.grXX_20201.lab3.Entidades.Persona

interface iComunicaFragments {
    //esta interface se encarga de realizar la comunicacion entre la lista de personas y el detalle
    fun enviarPersona(persona: Persona) //se transportara un objeto de tipo persona
    //(En la clase Persona se implementa Serializable para poder transportar un objeteo a otro)
}