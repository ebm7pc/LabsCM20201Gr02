package co.edu.udea.compumovil.gr02_20201.lab3.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.compumovil.gr02_20201.lab3.Interfaces.iComunicaFragments
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.Place
import co.edu.udea.compumovil.gr02_20201.lab3.R
import java.util.*
import kotlin.collections.ArrayList

class AdapterLugares(context: Context?, model:ArrayList<Place>) : RecyclerView.Adapter<AdapterLugares.PlaceViewHolder>(), View.OnClickListener {
    private var inflater: LayoutInflater
    private var model: ArrayList<Place>
    private var listener: View.OnClickListener?=null


    /**
     * Clase interna donde se crean los campos(datos) de un item del recyclerView
     */
    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nombres: TextView = itemView.findViewById(R.id.lugares)
        var descCorta: TextView = itemView.findViewById(R.id.descrip_corta)
        var imagen: ImageView = itemView.findViewById(R.id.imagen_lugar)
        init {
            nombres = itemView.findViewById(R.id.lugares)
            descCorta = itemView.findViewById(R.id.descrip_corta)
            imagen = itemView.findViewById(R.id.imagen_lugar)
        }
    }

    /**
     * Se infla el viewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
    val view = inflater.inflate(R.layout.lista_lugares, parent, false)
    view.setOnClickListener(this)
    return PlaceViewHolder(view)
    }

    /**
     * Aquí uso las referencias de la clase interna
     */
    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val nombres = model[position].name
        val descripCorta = model[position].short_des
        val imageid = model[position].img
        holder.nombres.text = nombres
        holder.descCorta.text = descripCorta
        holder.imagen.setImageResource(imageid)
    }

    /*
    //override fun getItemCount(): Int {
    //    return model.size
    //}*/

    override fun getItemCount()= model.size

    override fun onClick(view: View) {
        if (listener != null) {
            listener!!.onClick(view)
        }
    }

    fun setOnclickListener(listener: View.OnClickListener) {
        this.listener = listener
    }

    init {
        inflater = LayoutInflater.from(context)
        this.model = model
    }

    internal fun setPlaces(places: ArrayList<Place>){
        this.model= places
        notifyDataSetChanged()
    }
}