package co.edu.udea.compumovil.gr02_20201.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.content.Intent

class ActivityList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val fragLista=supportFragmentManager.findFragmentById(R.id.fragment_lista) as FragmentLista
        fragLista.setLugaresListener {
            val fragDetalle:Fragment?=supportFragmentManager.findFragmentById(R.id.frag_detalle)
            if (fragDetalle!=null) {
                (fragDetalle as FragmentDetalle).mostrarDetalle(it.descripcion_l)
            }
            else{
                val i=Intent(this, ActivityDetail::class.java)
                i.putExtra(ActivityDetail.EXTRA_TEXTO,it.descripcion_l)
                startActivity(i)
            }
        }
    }
}