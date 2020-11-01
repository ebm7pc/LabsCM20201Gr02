package co.edu.udea.compumovil.labsCM20201_gr02.lab1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_personal_data.*
import java.util.*

class PersonalData : AppCompatActivity() {
    lateinit var seleccion:Spinner
    lateinit var resultado:String
    var fechaLog = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        spinnerEstudio()
        findViewById<Button>(R.id.button_siguiente).setOnClickListener { agregaInfo(it) }
    }

    /*override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
            newConfig.orientation = Configuration.ORIENTATION_LANDSCAPE
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
            newConfig.orientation = Configuration.ORIENTATION_PORTRAIT
        }
    }*/

    /**
     * Funciones para guardar y restaurar el estado de views en un cambio de configuración
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val userText = editTextDate.text
        outState.putCharSequence("savedText", userText)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val userText = savedInstanceState.getCharSequence("savedText")
        editTextDate.text = userText
        fechaLog = userText.toString()
    }

    /***
     * Función que llama todos los datos de las views y a una nueva actividad para almacenarlos y
     * luego procesarlos en una acción (al presionar un botón)
     */
    private fun agregaInfo(view: View){
        val inputTextNombre = findViewById<EditText>(R.id.inputText_nombre)
        val inputTextApellido = findViewById<EditText>(R.id.inputText_apellido)
        val textNombre = inputTextNombre.text
        val textApellido = inputTextApellido.text
        /*val textoPrueba = findViewById<TextView>(R.id.texto_prueba)
        val textoPruebados = findViewById<TextView>(R.id.texto_prueba2)
        textoPrueba.text = inputTextNombre.text
        textoPruebados.text = inputTextApellido.text*/
        if (fechaLog != "empty" && inputTextNombre.text.toString().isNotEmpty() && inputTextApellido.text.toString().isNotEmpty()){
            Log.i("Nombre", textNombre.toString() +" "+ textApellido.toString() )
            selecSexo()
            Log.i("Nació el",fechaLog.toString())
            if(resultado != "Grado de escolaridad"){
                Log.i("Escolaridad", resultado)
            }
            siguienteActividad()
        }else {
            Toast.makeText(this, "Debe ingresar campos obligatorios", Toast.LENGTH_SHORT).show()
            return}
        Toast.makeText(this, "Hecho!", Toast.LENGTH_SHORT).show()
    }

    /**
     * Función usada para llamar un date picker, seleccionar
     * una fecha y almacenar sus datos y
     * luego mostrarlos en un campo de texto de la interfaz de usuario
     */
    @SuppressLint("SetTextI18n")
    fun muestraFecha(view: View){
        val cal = Calendar.getInstance()
        val dia = cal.get(Calendar.DAY_OF_MONTH)
        val mes = cal.get(Calendar.MONTH)
        val year = cal.get(Calendar.YEAR)
        val picker = DatePickerDialog(this, android.R.style.Theme_DeviceDefault, DatePickerDialog.OnDateSetListener { view : DatePicker, ano:Int, mesAnoMalo:Int, diaMes:Int ->
            val mesAno = mesAnoMalo + 1
            val fecha = "$diaMes/$mesAno/$ano"
            editTextDate.text = fecha
            fechaLog = fecha
        }, year, mes, dia)
        picker.show()
    }

    /**
     * Función usada para obtener la información seleccionada
     * de un spinner
     */
    fun spinnerEstudio(){
        seleccion = findViewById<Spinner>(R.id.spinner_estudio)
        //resultado = findViewById(R.id.textViewSpinner) as TextView
        val opciones = arrayOf("Grado de escolaridad","Primaria","Secundaria","Universitaria", "Otro")
        seleccion.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opciones)

        seleccion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                resultado = opciones.get(p2)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    /**
     * Función usada para verificar el tipo de
     * selección hecha en un grupo de radio buttons
     */
    fun selecSexo(){
        val hombre = radio_hombre.isChecked
        val mujer = radio_mujer.isChecked
        if (hombre || mujer){
            if (hombre){
                Log.i("-","Masculino")
            }else Log.i("-","Femenino")
        }
    }

    /**
     * Función usada para pasar a una siguiente activity
     */
    fun siguienteActividad(){
        val intent : Intent = Intent(this, ContactData::class.java)
        startActivity(intent)
    }
}