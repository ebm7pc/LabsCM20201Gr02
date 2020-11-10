package co.edu.udea.compumovil.gr02_20201.lab3.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.edu.udea.compumovil.gr02_20201.lab3.Entidades.Lugar
import co.edu.udea.compumovil.gr02_20201.lab3.R

class DetalleLugarFragment : Fragment() {
    var nombre: TextView? = null
    var descripcion: TextView? = null
    var imagen: ImageView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.detalle_lugar_fragment, container, false)
        nombre = view.findViewById(R.id.nombre_lugar)
        descripcion = view.findViewById(R.id.descripcion_larga)
        imagen = view.findViewById(R.id.imagen_detalleid)
        //Crear bundle para recibir el objeto enviado por parametro.
        val objetoLugar = arguments
        var lugar: Lugar?
        //validacion para verificar si existen argumentos para mostrar
        if (objetoLugar != null) {
            lugar = objetoLugar.getSerializable("objeto") as Lugar
            imagen!!.setImageResource(lugar.imagenid)
            nombre!!.text = lugar.nombre
            descripcion!!.text = lugar.descripLarga
        }
        return view
    }
}