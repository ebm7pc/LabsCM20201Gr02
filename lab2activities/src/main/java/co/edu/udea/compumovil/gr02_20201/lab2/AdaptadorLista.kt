package co.edu.udea.compumovil.gr02_20201.lab2

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.media.Image
import android.media.ImageReader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView

data class Lugar(val imagen:Int, val lugar: String, val descripcion_c: String, val descripcion_l:String)

class AdaptadorLista(
    private val datos: MutableList<Lugar>,
    private val clickListener : (Lugar) -> Unit) :
    RecyclerView.Adapter<AdaptadorLista.LugaresViewHolder>() {

    class LugaresViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        val nombreLugar = item.findViewById(R.id.lbl_lugar) as TextView
        val miniDescrip = item.findViewById(R.id.lbl_descrip) as TextView
        var imagenView=item.findViewById(R.id.imagen_lista) as ImageView


        fun bindLista(lugar: Lugar){
            nombreLugar.text = lugar.lugar
            miniDescrip.text = lugar.descripcion_c
            imagenView.id=lugar.imagen
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugaresViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_lugares, parent, false) as ConstraintLayout

        return LugaresViewHolder(item)
    }

    override fun onBindViewHolder(holder: LugaresViewHolder, position: Int) {
        val lugar = datos[position]

        holder.bindLista(lugar)

        holder.item.setOnClickListener{clickListener(lugar)};
    }

    override fun getItemCount() = datos.size
}