package co.edu.udea.compumovil.gr02_20201.lab3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.Place
import co.edu.udea.compumovil.gr02_20201.lab3.R

class PlaceDetailFragment : Fragment() {
    lateinit var nombre: TextView
    lateinit var descripcion: TextView
    lateinit var imagen: ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.detalle_lugar_fragment, container, false)
        nombre = view.findViewById(R.id.nombre_lugar)
        descripcion = view.findViewById(R.id.descripcion_larga)
        imagen = view.findViewById(R.id.imagen_detalleid)
        //Crear bundle para recibir el objeto enviado por parametro.
        val objetoLugar = arguments
        var lugar: Place?
        //validacion para verificar si existen argumentos para mostrar
        if (objetoLugar != null) {
            lugar = objetoLugar.getSerializable("objeto") as Place
            imagen.setImageResource(lugar.img)
            nombre.text = lugar.name
            descripcion.text = lugar.long_desc
        }
        return view
    }
}