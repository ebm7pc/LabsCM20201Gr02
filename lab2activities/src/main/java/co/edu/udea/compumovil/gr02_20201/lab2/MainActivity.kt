package co.edu.udea.compumovil.gr02_20201.lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var textUser: EditText
    private lateinit var textPass: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textUser= findViewById<EditText>(R.id.editTextUser)
        textPass= findViewById<EditText>(R.id.editTextPassword)
        findViewById<TextView>(R.id.textViewRegister).setOnClickListener { preparaInfo(it) }
        findViewById<Button>(R.id.buttonStart).setOnClickListener { controlBotonStart(it) }
    }

    private fun controlBotonStart(view: View){
        if (validaDatos()){
            actividadInicio()
            Toast.makeText(this,"Exito!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun preparaInfo(view: View){
        actividadRegistro()
        Toast.makeText(this, "Hecho!", Toast.LENGTH_SHORT).show()
    }

    private fun validaDatos():Boolean{
        var retorno=true
        val c1=textUser.text.toString()
        val c2=textPass.text.toString()
        if(c1.isNullOrEmpty()){
            retorno=false
            textUser.error="Ingrese un usuario"
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()
        }
        if(c2.isNullOrEmpty()){
            retorno=false
            textPass.error="Ingrese una contraseña"
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()
        }
        return retorno
    }



    fun actividadInicio(){
        val intent : Intent = Intent(this, ActivityList::class.java)
        startActivity(intent)
    }
    fun actividadRegistro(){
        val intent : Intent = Intent(this, ActivityRegistry::class.java)
        startActivity(intent)
    }

}
