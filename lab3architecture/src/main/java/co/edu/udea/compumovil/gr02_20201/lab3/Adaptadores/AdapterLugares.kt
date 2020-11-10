package co.edu.udea.compumovil.gr02_20201.lab3.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.compumovil.gr02_20201.lab3.Entidades.Lugar
import co.edu.udea.compumovil.gr02_20201.lab3.R
import java.util.*

class AdapterLugares(context: Context?, model: ArrayList<Lugar>) : RecyclerView.Adapter<AdapterLugares.ViewHolder>(), View.OnClickListener {
    var inflater: LayoutInflater
    var model: ArrayList<Lugar>
    private var listener: View.OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.lista_lugares, parent, false)
        view.setOnClickListener(this)
        return ViewHolder(view)
    }

    fun setOnclickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nombres = model[position].nombre
        val descripCorta = model[position].descripCorta
        val imageid = model[position].imagenid
        holder.nombres.text = nombres
        holder.descCorta.text = descripCorta
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
        var descCorta: TextView
        var imagen: ImageView

        init {
            nombres = itemView.findViewById(R.id.lugares)
            descCorta = itemView.findViewById(R.id.descrip_corta)
            imagen = itemView.findViewById(R.id.imagen_lugar)
        }
    }

    init {
        inflater = LayoutInflater.from(context)
        this.model = model
    }
}