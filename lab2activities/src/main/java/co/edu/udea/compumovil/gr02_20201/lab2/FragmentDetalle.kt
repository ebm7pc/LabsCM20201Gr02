package co.edu.udea.compumovil.gr02_20201.lab2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FragmentDetalle:Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_detalle,container,false)
    }

    fun mostrarDetalle(texto: String?){
        val txtDetalle = requireView().findViewById(R.id.textView_descripcion) as TextView
        txtDetalle.text=texto
    }
}