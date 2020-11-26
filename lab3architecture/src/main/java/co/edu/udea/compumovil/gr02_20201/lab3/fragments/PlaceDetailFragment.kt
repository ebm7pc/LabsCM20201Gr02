package co.edu.udea.compumovil.gr02_20201.lab3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import co.edu.udea.compumovil.gr02_20201.lab3.R
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.Place

class PlaceDetailFragment : Fragment() {
    lateinit var name: TextView
    lateinit var description: TextView
    lateinit var image: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.place_detail_fragment, container, false)
        name = view.findViewById(R.id.nombre_lugar)
        description = view.findViewById(R.id.descripcion_larga)
        image = view.findViewById(R.id.imagen_detalleid)
        //Crear bundle para recibir el objeto enviado por parametro.
        val placeObject = arguments
        var place: Place?
        //validacion para verificar si existen argumentos para mostrar
        if (placeObject != null) {
            place = placeObject.getSerializable("objeto") as Place
            image.setImageResource(place.img)
            name.text = place.name
            description.text = place.long_desc
        }
        return view
    }
}