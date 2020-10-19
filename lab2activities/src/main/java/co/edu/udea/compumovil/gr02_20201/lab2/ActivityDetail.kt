package co.edu.udea.compumovil.gr02_20201.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ActivityDetail : AppCompatActivity() {

    companion object{
        val EXTRA_TEXTO : String = "co.edu.udea.compumovil.gr02_20201.lab2.EXTRA_TEXTO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val detalle=supportFragmentManager.findFragmentById(R.id.frag_detalle) as FragmentDetalle
        detalle.mostrarDetalle(intent.getStringExtra(EXTRA_TEXTO))
    }
}