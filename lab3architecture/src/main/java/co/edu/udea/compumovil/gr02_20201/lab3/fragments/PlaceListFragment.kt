package co.edu.udea.compumovil.gr02_20201.lab3.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab3.adapters.OnPlaceItemClickListener
import co.edu.udea.compumovil.gr02_20201.lab3.adapters.PlaceAdapter
import co.edu.udea.compumovil.gr02_20201.lab3.interfaces.iComunicaFragments
import co.edu.udea.compumovil.gr02_20201.lab3.data.dao.PlaceDao
import co.edu.udea.compumovil.gr02_20201.lab3.data.database.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.Place
import co.edu.udea.compumovil.gr02_20201.lab3.R
import co.edu.udea.compumovil.gr02_20201.lab3.repo.PlaceRepository
import co.edu.udea.compumovil.gr02_20201.lab3.viewmodel.PlaceViewModel
import co.edu.udea.compumovil.gr02_20201.lab3.viewmodel.PlaceViewModelFactory
import kotlinx.android.synthetic.main.fragment_lugares.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class PlaceListFragment : Fragment(), OnPlaceItemClickListener {
    private lateinit var listaLugares: ArrayList<Place>
    private lateinit var placeViewModel: PlaceViewModel
    private lateinit var adapter: PlaceAdapter

    //Crear referencias para poder realizar la comunicación entre el fragment detalle
    private lateinit var actividad: Activity
    private lateinit var interfaceComunicaFragments: iComunicaFragments
    private lateinit var dataBase: LabTresDB

    private var contx: CoroutineContext = Dispatchers.IO
    private lateinit var scope: CoroutineScope

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBase = Room.databaseBuilder(requireContext(), LabTresDB::class.java, "mi_db")
            .allowMainThreadQueries().build()
        return inflater.inflate(R.layout.fragment_lugares, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //createViewModel()
        //placeViewModel.guardarListaLugares(requireContext())
        //listaLugares=placeViewModel.cargarLista(requireContext())
        //adapter= PlaceAdapter(listaLugares,this)
        placesListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        placesListRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), 1))
        placesListRecyclerView.adapter = PlaceAdapter(listaLugares, this)
        val fab = fab
        fab.setOnClickListener {
            lanzaFragmentNewPlace()
        }
        //observableLiveData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createViewModel()
        placeViewModel.wholePlaces.observe(viewLifecycleOwner, Observer {
            adapter.setPlaces(it)
        })
    }

    /**
     * Se instancia el viewModel para poder usarlo
     */
    private fun createViewModel() {
        scope = CoroutineScope(contx)
        val placeDao: PlaceDao = LabTresDB.getDatabaseInstance(requireContext(), scope).lugarDao()
        val repository = PlaceRepository(dataBase, placeDao)
        val factory = PlaceViewModelFactory(repository)
        placeViewModel = ViewModelProvider(this, factory).get(PlaceViewModel::class.java)
    }

    /*private fun observableLiveData(){
        placeViewModel.allPlaces.observe(viewLifecycleOwner, Observer{place ->
            place?.let { adapter.setPlaces(it as ArrayList<Place>) }
        })
    }*/

    override fun onItemClick(item: Place, position: Int) {
        Toast.makeText(requireContext(), item.name, Toast.LENGTH_SHORT).show()
        interfaceComunicaFragments.enviarLugar(listaLugares[position])

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

    /**
     * Prepara el fragment con el formulario de agregar lugar
     */
    private fun lanzaFragmentNewPlace() {
        val fragmentNewPlace = NewPlaceFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_fragment, fragmentNewPlace)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}
