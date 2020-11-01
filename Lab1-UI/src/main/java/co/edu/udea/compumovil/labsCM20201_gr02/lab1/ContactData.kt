package co.edu.udea.compumovil.labsCM20201_gr02.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_contact_data.*

class ContactData : AppCompatActivity() {

    lateinit var Selector:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_data)

        findViewById<Button>(R.id.button_Finalizar).setOnClickListener { agregaInfo(it) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val telefonoText = telefono.text
        outState.putCharSequence("savedText", telefonoText)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val telefonoText = savedInstanceState.getCharSequence("savedText")
        telefono.text = telefonoText as Editable?
    }

    private fun agregaInfo(view: View) {
        val inputTextPhone = findViewById<EditText>(R.id.telefono)
        val inputTextEmail = findViewById<EditText>(R.id.email)
        val inputTextCountry=findViewById<EditText>(R.id.pais)
        val inputTextDir=findViewById<EditText>(R.id.direccion)
        val inputTextCiu=findViewById<EditText>(R.id.ciudad)
        val textPhone= inputTextPhone.text
        val textEmail = inputTextEmail.text
        val textDir= inputTextDir.text
        val textCiu= ciudad.text
        val autoCompCountry=inputTextCountry.text

        if (inputTextPhone.text.toString().isNotEmpty() && inputTextEmail.text.toString().isNotEmpty()&& inputTextCountry.text.toString().isNotEmpty()){
            Log.i("Telefono", textPhone.toString())
            Log.i("Direccion", textDir.toString())
            Log.i("Email", textEmail.toString())
            Log.i("País",autoCompCountry.toString())
            Log.i("Ciudad", textCiu.toString())

        }else {
            Toast.makeText(this, "Debe ingresar campos obligatorios", Toast.LENGTH_SHORT).show()
            return}
        Toast.makeText(this, "Hecho!", Toast.LENGTH_SHORT).show()

    }


}