package co.edu.udea.compumovil.gr02_20201.lab3.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.compumovil.gr02_20201.lab3.Entidades.Persona
import co.edu.udea.compumovil.gr02_20201.lab3.R
import java.util.*

class AdapterPersonas(context: Context?, model: ArrayList<Persona>) : RecyclerView.Adapter<AdapterPersonas.ViewHolder>(), View.OnClickListener {
    var inflater: LayoutInflater
    var model: ArrayList<Persona>
    private var listener: View.OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.lista_personas, parent, false)
        view.setOnClickListener(this)
        return ViewHolder(view)
    }

    fun setOnclickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nombres = model[position].nombre
        val fechanacimiento = model[position].fechanacimiento
        val imageid = model[position].imagenid
        holder.nombres.text = nombres
        holder.fechancimiento.text = fechanacimiento
        holder.imagen.setImageResource(imageid)
    }

    override fun getItemCount(): Int {
        return model.size
    }

    override fun onClick(view: View) {
        if (listener != null) {
            listener!!.onClick(view)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nombres: TextView
        var fechancimiento: TextView
        var imagen: ImageView

        init {
            nombres = itemView.findViewById(R.id.nombres)
            fechancimiento = itemView.findViewById(R.id.fechanacimiento)
            imagen = itemView.findViewById(R.id.imagen_persona)
        }
    }

    init {
        inflater = LayoutInflater.from(context)
        this.model = model
    }
}