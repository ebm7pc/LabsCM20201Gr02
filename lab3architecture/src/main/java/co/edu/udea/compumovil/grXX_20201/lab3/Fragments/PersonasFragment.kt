package co.edu.udea.compumovil.grXX_20201.lab3.Fragments

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
import co.edu.udea.compumovil.grXX_20201.lab3.Adaptadores.AdapterPersonas
import co.edu.udea.compumovil.grXX_20201.lab3.Entidades.Persona
import co.edu.udea.compumovil.grXX_20201.lab3.Interfaces.iComunicaFragments


import co.edu.udea.compumovil.grXX_20201.lab3.R
import java.util.ArrayList

class PersonasFragment : Fragment() {
    //private OnFragmentInteractionListener mListener;
    var adapterPersonas: AdapterPersonas? = null
    var recyclerViewPersonas: RecyclerView? = null
    var listaPersonas: ArrayList<Persona>? = null
    var txtnombre: EditText? = null

    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    var actividad: Activity? = null
    var interfaceComunicaFragments: iComunicaFragments? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_personas, container, false)
        txtnombre = view.findViewById(R.id.txtnombre)
        recyclerViewPersonas = view.findViewById(R.id.recyclerView)
        listaPersonas = ArrayList()
        cargarLista()
        mostrarData()
        return view
    }

    fun cargarLista() {
        listaPersonas!!.add(Persona("Gohan", "31-05-1994", R.drawable.gohan_cara1))
        listaPersonas!!.add(Persona("Goku", "31-05-1994", R.drawable.goku_cara2))
        listaPersonas!!.add(Persona("Goten", "31-05-1994", R.drawable.goten_cara3))
        listaPersonas!!.add(Persona("Krilin", "31-05-1994", R.drawable.krilin_cara4))
        listaPersonas!!.add(Persona("Picoro", "31-05-1994", R.drawable.picoro_cara5))
        listaPersonas!!.add(Persona("Trunks", "31-05-1994", R.drawable.trunks_cara6))
        listaPersonas!!.add(Persona("Vegueta", "31-05-1994", R.drawable.vegueta_cara7))
    }

    private fun mostrarData() {
        recyclerViewPersonas!!.layoutManager = LinearLayoutManager(context)
        adapterPersonas = AdapterPersonas(context, listaPersonas!!)
        recyclerViewPersonas!!.adapter = adapterPersonas
        adapterPersonas!!.setOnclickListener { view ->
            val nombre = listaPersonas!![recyclerViewPersonas!!.getChildAdapterPosition(view)].nombre
            txtnombre!!.setText(nombre)
            Toast.makeText(context, "Seleccionó: " + listaPersonas!![recyclerViewPersonas!!.getChildAdapterPosition(view)].nombre, Toast.LENGTH_SHORT).show()
            //enviar mediante la interface el objeto seleccionado al detalle
            //se envia el objeto completo
            //se utiliza la interface como puente para enviar el objeto seleccionado
            interfaceComunicaFragments!!.enviarPersona(listaPersonas!![recyclerViewPersonas!!.getChildAdapterPosition(view)])
            //luego en el mainactivity se hace la implementacion de la interface para implementar el metodo enviarpersona
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //esto es necesario para establecer la comunicacion entre la lista y el detalle
        //si el contexto que le esta llegando es una instancia de un activity:
        if (context is Activity) {
            //voy a decirle a mi actividad que sea igual a dicho contesto. castin correspondiente:
            actividad = context
            ////que la interface icomunicafragments sea igual ese contexto de la actividad:
            interfaceComunicaFragments = actividad as iComunicaFragments?
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }

        /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

}