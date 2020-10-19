package co.edu.udea.compumovil.gr02_20201.lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab2.Dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab2.DataBase.LaboratorioDosDB
import co.edu.udea.compumovil.gr02_20201.lab2.Entidades.User

class ActivityRegistry : AppCompatActivity() {

    lateinit var editTextUsuario:EditText
    lateinit var editTextCorreo:EditText
    lateinit var editTextPass:EditText
    lateinit var buttonRegistro:Button
    private lateinit var userDao:UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry)

        editTextUsuario=findViewById(R.id.editTextRegUser)
        editTextCorreo=findViewById(R.id.editTextRegEmail)
        editTextPass=findViewById(R.id.editTextRegPassword)
        buttonRegistro=findViewById(R.id.buttonRegGuardar)
        userDao = Room.databaseBuilder(this, LaboratorioDosDB::class.java, "miBD").allowMainThreadQueries().build().usuarioDao()
        buttonRegistro.setOnClickListener(View.OnClickListener {
            val userName = editTextUsuario.text.toString().trim { it <= ' ' }
            val email = editTextCorreo.text.toString().trim { it <= ' ' }
            val password = editTextPass.text.toString().trim { it <= ' ' }
            val usuario = User(email, password,userName)
            userDao.insert(usuario)
            val irALogin=Intent(this@ActivityRegistry, MainActivity::class.java)
            Toast.makeText(this@ActivityRegistry, "¡Registro Exitoso!", Toast.LENGTH_LONG).show()
            startActivity(irALogin)
        }
        )
    }
}