package co.edu.udea.compumovil.gr02_20201.lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab2.Config.Lab2App
import co.edu.udea.compumovil.gr02_20201.lab2.Dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab2.DataBase.LaboratorioDosDB
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Actividad principal, en esta actividad se incia sesión para ir a la lista y se va hacia el
 * registro de un nuevo usuariuo
 */
class MainActivity : AppCompatActivity() {
    private lateinit var textEmail: EditText
    private lateinit var textPass: EditText
    private lateinit var buttonLogin: Button
    private lateinit var db: UserDao
    private lateinit var dataBase:LaboratorioDosDB

    /**
     * Se inicializan variables y base de datos
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textEmail= findViewById<EditText>(R.id.editTextCorreo)
        textPass= findViewById<EditText>(R.id.editTextPassword)
        buttonLogin=findViewById(R.id.buttonStart)
        dataBase=Room.databaseBuilder(this, LaboratorioDosDB::class.java, "miBD").allowMainThreadQueries().build()
        db=dataBase.usuarioDao()
        findViewById<TextView>(R.id.textViewRegister).setOnClickListener { preparaInfo(it) }
        buttonLogin.setOnClickListener { controlBotonStart(it) }
    }

    /**
     * Acciones que se realizan al presionar el botón de iniciar sesión
     */
    private fun controlBotonStart(view: View){
        if (validaDatos()){
            actividadInicio()
            Toast.makeText(this,"Exito!", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Función que llama a otra función para inciar la actividadd de crear registro
     */
    private fun preparaInfo(view: View){
        actividadRegistro()
        Toast.makeText(this, "Hecho!", Toast.LENGTH_SHORT).show()
    }

    /**
     * Función para obtener los datos de la BD y validar el inicio de sesión
     */
    private fun actividadInicio(){
        val email = editTextCorreo.text.toString().trim { it <= ' ' }
        val password = editTextPassword.text.toString().trim { it <= ' ' }
        val user = db.getUser(email, password)
        if (user != null) {
            val i = Intent(this@MainActivity, ActivityList::class.java)
            startActivity(i)
            finish()
        } else {
            Toast.makeText(this@MainActivity, "Sus datos son incorrectos", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Valida que los campos de inicio de sesión no estén vacíoss
     */
    private fun validaDatos():Boolean{
        var retorno=true
        val c1=textEmail.text.toString()
        val c2=textPass.text.toString()
        if(c1.isNullOrEmpty()){
            retorno=false
            textEmail.error="Ingrese un usuario"
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()
        }
        if(c2.isNullOrEmpty()){
            retorno=false
            textPass.error="Ingrese una contraseña"
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()
        }
        return retorno
    }

    /**
     * Llamado a la actividad de crear registro
     */
    fun actividadRegistro(){
        val intent : Intent = Intent(this, ActivityRegistry::class.java)
        startActivity(intent)
    }

}
