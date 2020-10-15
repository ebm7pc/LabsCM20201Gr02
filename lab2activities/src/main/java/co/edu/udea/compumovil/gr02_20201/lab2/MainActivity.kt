package co.edu.udea.compumovil.gr02_20201.lab2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        findViewById<Button>(R.id.buttonStart).setOnClickListener { validaDatos(it) }
    }


    private fun preparaInfo(view: View){
        siguienteActividad()
        Toast.makeText(this, "Hecho!", Toast.LENGTH_SHORT).show()
    }

    private fun validaDatos(view: View){
        //val retorno=true
        val c1=textUser.toString()
        val c2=textPass.toString()
        if(c1.isNullOrEmpty()||c2.isNullOrEmpty()){
            textUser.error="jodete"
            textPass.error="pass malo"
        }
        //return retorno
    }



    
    
    fun siguienteActividad(){
        val intent : Intent = Intent(this, ActivityRegistry::class.java)
        startActivity(intent)
    }

}
