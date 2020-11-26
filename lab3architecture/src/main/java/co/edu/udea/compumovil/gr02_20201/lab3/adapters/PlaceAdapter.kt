package co.edu.udea.compumovil.gr02_20201.lab3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.Place
import co.edu.udea.compumovil.gr02_20201.lab3.R
import kotlinx.android.synthetic.main.item_list.view.*

class PlaceAdapter(var items: ArrayList<Place>, var clickListener: OnPlaceItemClickListener): RecyclerView.Adapter<PlaceViewHolder>() {
    //private var placeList= mutableListOf<Place>()
    private var places = emptyList<Place>()

    override fun getItemCount()= items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        lateinit var placeViewHolder: PlaceViewHolder
        placeViewHolder = PlaceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
        return placeViewHolder
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        //val place= placeList[position]
        //holder.placename.text= place.name
        //holder.description.text= place.short_des
        //holder.image.setImageResource(place.img)
        holder.initialize(items.get(position), clickListener)
    }

    internal fun setPlaces(places: List<Place>) {
        this.places = places
        notifyDataSetChanged()
    }

}

class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image= itemView.imagen_lugar
    var placename=itemView.lugares
    var description= itemView.descrip_corta

    fun initialize(item:Place, action:OnPlaceItemClickListener){
        image.setImageResource(item.img)
        placename.text= item.name
        description.text= item.short_des

        itemView.setOnClickListener {
            action.onItemClick(item, adapterPosition)
        }
    }
}

interface OnPlaceItemClickListener{
    fun onItemClick(item: Place, position: Int)
}