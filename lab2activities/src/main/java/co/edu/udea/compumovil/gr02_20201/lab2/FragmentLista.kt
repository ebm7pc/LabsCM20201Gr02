package co.edu.udea.compumovil.gr02_20201.lab2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FragmentLista : Fragment() {
    private lateinit var lista:RecyclerView
    var escuchador:SitiosListener?=null

    private val contenido = mutableListOf(
        Lugar(R.drawable.paisa2,"Santorini", Descriptions().santorini, LongDescriptions().santorini),
        Lugar(R.drawable.paisa3,"Naxos", Descriptions().naxos, LongDescriptions().naxos),
        Lugar(R.drawable.paisa4,"Rodas", Descriptions().rodas, LongDescriptions().rodas),
        Lugar(R.drawable.paisa5,"Miconos", Descriptions().miconos, LongDescriptions().miconos),
        Lugar(R.drawable.paisa1,"Olimpia", Descriptions().olimpia, LongDescriptions().olimpia)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_lista, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lista = requireView().findViewById(R.id.lista_lugares)
        val adaptador=AdaptadorLista(contenido){
            escuchador?.onLugarSeleccionado(it)
        }
        lista.layoutManager=LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        lista.addItemDecoration(DividerItemDecoration(this.context,DividerItemDecoration.VERTICAL))
        lista.adapter=adaptador
    }

    fun setLugaresListener(listen:SitiosListener){
        escuchador=listen
    }

    fun setLugaresListener(seleccion:(Lugar) -> Unit){
        escuchador=object:SitiosListener{
            override fun onLugarSeleccionado(lugar: Lugar) {
                seleccion(lugar)
            }
        }
    }
}