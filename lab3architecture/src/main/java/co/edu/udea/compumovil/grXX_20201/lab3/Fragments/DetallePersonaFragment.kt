package co.edu.udea.compumovil.grXX_20201.lab3.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.edu.udea.compumovil.grXX_20201.lab3.Entidades.Persona
import co.edu.udea.compumovil.grXX_20201.lab3.R

class DetallePersonaFragment : Fragment() {
    var nombre: TextView? = null
    var imagen: ImageView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.detalle_persona_fragment, container, false)
        nombre = view.findViewById(R.id.nombre_detalle)
        imagen = view.findViewById(R.id.imagen_detalleid)
        //Crear bundle para recibir el objeto enviado por parametro.
        val objetoPersona = arguments
        var persona: Persona? = null
        //validacion para verificar si existen argumentos para mostrar
        if (objetoPersona != null) {
            persona = objetoPersona.getSerializable("objeto") as Persona
            imagen!!.setImageResource(persona.imagenid)
            nombre!!.text = persona.nombre
        }
        return view
    }
}