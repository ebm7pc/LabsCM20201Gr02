package co.edu.udea.compumovil.gr02_20201.lab3.Fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.compumovil.gr02_20201.lab3.Adaptadores.AdapterLugares
import co.edu.udea.compumovil.gr02_20201.lab3.Descriptions
import co.edu.udea.compumovil.gr02_20201.lab3.Entidades.Lugar
import co.edu.udea.compumovil.gr02_20201.lab3.Interfaces.iComunicaFragments
import co.edu.udea.compumovil.gr02_20201.lab3.LongDescriptions


import co.edu.udea.compumovil.gr02_20201.lab3.R
import java.util.ArrayList

class LugaresFragment : Fragment() {
    //private OnFragmentInteractionListener mListener;
    var adapterLugares: AdapterLugares? = null
    var recyclerViewLugares: RecyclerView? = null
    var listaLugares: ArrayList<Lugar>? = null
    var txtnombre: EditText? = null
    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    var actividad: Activity? = null
    var interfaceComunicaFragments: iComunicaFragments? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lugares, container, false)
        txtnombre = view.findViewById(R.id.txtlugar)
        recyclerViewLugares = view.findViewById(R.id.recyclerView)
        listaLugares = ArrayList()
        cargarLista()
        mostrarData()
        return view
    }

    fun cargarLista() {
        listaLugares!!.add(Lugar("Santorini", Descriptions().santorini, LongDescriptions().santorini, R.drawable.santorini1))
        listaLugares!!.add(Lugar("Naxos", Descriptions().naxos, LongDescriptions().naxos, R.drawable.naxos1))
        listaLugares!!.add(Lugar("Rodas", Descriptions().rodas, LongDescriptions().rodas, R.drawable.rodas1))
        listaLugares!!.add(Lugar("Myconos", Descriptions().miconos, LongDescriptions().miconos, R.drawable.mykonos1))
        listaLugares!!.add(Lugar("Olimpia", Descriptions().olimpia, LongDescriptions().olimpia, R.drawable.olimpia1))
    }

    private fun mostrarData() {
        recyclerViewLugares!!.layoutManager = LinearLayoutManager(context)
        adapterLugares = AdapterLugares(context, listaLugares!!)
        recyclerViewLugares!!.adapter = adapterLugares
        adapterLugares!!.setOnclickListener { view ->
            val nombre = listaLugares!![recyclerViewLugares!!.getChildAdapterPosition(view)].nombre
            txtnombre!!.setText(nombre)
            Toast.makeText(context, "Seleccionó: " + listaLugares!![recyclerViewLugares!!.getChildAdapterPosition(view)].nombre, Toast.LENGTH_SHORT).show()
            //enviar mediante la interface el objeto seleccionado al detalle
            //se envia el objeto completo
            //se utiliza la interface como puente para enviar el objeto seleccionado
            interfaceComunicaFragments!!.enviarLugar(listaLugares!![recyclerViewLugares!!.getChildAdapterPosition(view)])
            //luego en el mainactivity se hace la implementacion de la interface para implementar el metodo enviarpersona
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //esto es necesario para establecer la comunicacion entre la lista y el detalle
        //si el contexto que le esta llegando es una instancia de un activity:
        if (context is Activity) {
            //voy a decirle a mi actividad que sea igual a dicho contexto. casting correspondiente:
            actividad = context
            ////que la interface icomunicafragments sea igual ese contexto de la actividad:
            interfaceComunicaFragments = actividad as iComunicaFragments?
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }
    }
}