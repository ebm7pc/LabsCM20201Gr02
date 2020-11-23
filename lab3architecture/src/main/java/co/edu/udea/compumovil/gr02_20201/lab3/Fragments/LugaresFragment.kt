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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab3.Adaptadores.AdapterLugares
import co.edu.udea.compumovil.gr02_20201.lab3.Interfaces.iComunicaFragments
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.PlaceDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.DataBase.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.Place
import co.edu.udea.compumovil.gr02_20201.lab3.R
import co.edu.udea.compumovil.gr02_20201.lab3.ViewModel.PlaceViewModel
import kotlinx.android.synthetic.main.fragment_lugares.*
import kotlin.collections.ArrayList

class LugaresFragment : Fragment() {
    private lateinit var placeViewModel: PlaceViewModel

    private lateinit var adapterLugares: AdapterLugares
    private lateinit var recyclerViewLugares: RecyclerView
    private lateinit var listaLugares: ArrayList<Place>
    private lateinit var txtnombre: EditText
    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    private lateinit var actividad: Activity
    private lateinit var interfaceComunicaFragments: iComunicaFragments
    private lateinit var dataBase: LabTresDB
    private lateinit var placeDao: PlaceDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lugares, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        placeViewModel= ViewModelProvider(this).get(PlaceViewModel::class.java)
        txtnombre = view.findViewById(R.id.txtlugar)
        //recyclerViewLugares = view.findViewById(R.id.recyclerView)//111111
        listaLugares = ArrayList()
        //cargarLista()
        //mostrarRecyclerData()
        initRecycler()
        observableLiveData()
    }

    private fun initRecycler(){
        recyclerViewLugares = recyclerView
        adapterLugares= AdapterLugares(requireContext(), listaLugares)
        recyclerViewLugares.adapter=adapterLugares
        recyclerViewLugares.layoutManager= LinearLayoutManager(requireContext())
        adapterLugares.setOnclickListener {
            val nombre = listaLugares[recyclerViewLugares.getChildAdapterPosition(it)].name
            txtnombre.setText(nombre)
            Toast.makeText(context, "Seleccionó: " + listaLugares[recyclerViewLugares.getChildAdapterPosition(it)].name, Toast.LENGTH_SHORT).show()
            interfaceComunicaFragments.enviarLugar(listaLugares[recyclerViewLugares.getChildAdapterPosition(it)])
        }

    }

    private fun observableLiveData(){
        placeViewModel.places.observe(viewLifecycleOwner, Observer{place ->
            place?.let { adapterLugares.setPlaces(it as ArrayList<Place>) }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //esto es necesario para establecer la comunicacion entre la lista y el detalle
        //si el contexto que le esta llegando es una instancia de un activity:
        if (context is Activity) {
            //voy a decirle a mi actividad que sea igual a dicho contexto. casting correspondiente:
            actividad = context
            ////que la interface icomunicafragments sea igual ese contexto de la actividad:
            interfaceComunicaFragments = actividad as iComunicaFragments
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }
    }




    /*fun cargarLista(){
        var nombre: String?
        var desC: String?
        var desL: String?
        var img: Int
        dataBase= Room.databaseBuilder(requireContext(), LabTresDB::class.java, "mi_db").allowMainThreadQueries().build()
        placeDao= dataBase.lugarDao()
        val lugares= placeDao.getPlaces()
        val tama= placeDao.getPlaces().size
        //Toast.makeText(context, "el tamaño es " + tama, Toast.LENGTH_SHORT).show()
        for (i in 0 until tama){
            nombre= lugares[i].name
            desC= lugares[i].short_des
            desL= lugares[i].long_desc
            img= lugares[i].img
            listaLugares.add(Place(0,nombre, desC, desL, img))
        }
    }

    private fun mostrarRecyclerData() {
        recyclerViewLugares.layoutManager = LinearLayoutManager(context)
        adapterLugares = AdapterLugares(context, listaLugares)
        recyclerViewLugares.adapter = adapterLugares
        adapterLugares.setOnclickListener { view ->
            val nombre = listaLugares[recyclerViewLugares.getChildAdapterPosition(view)].name
            txtnombre.setText(nombre)
            Toast.makeText(context, "Seleccionó: " + listaLugares[recyclerViewLugares.getChildAdapterPosition(view)].name, Toast.LENGTH_SHORT).show()
            //enviar mediante la interface el objeto seleccionado al detalle
            //se envia el objeto completo
            //se utiliza la interface como puente para enviar el objeto seleccionado
            interfaceComunicaFragments.enviarLugar(listaLugares[recyclerViewLugares.getChildAdapterPosition(view)])
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
            interfaceComunicaFragments = actividad as iComunicaFragments
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }
    }*/
}
