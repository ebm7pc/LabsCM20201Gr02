package co.edu.udea.compumovil.gr02_20201.lab3.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab3.LoginActivity
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.DataBase.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    private lateinit var textEmail: EditText
    private lateinit var textPass: EditText
    private lateinit var buttonLogin: Button
    private lateinit var dataBase: LabTresDB
    private lateinit var db: UserDao


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textEmail= view.findViewById(R.id.editTextCorreo)
        textPass= view.findViewById(R.id.editTextPassword)
        buttonLogin= view.findViewById(R.id.buttonStart)
        dataBase= Room.databaseBuilder(requireContext(), LabTresDB::class.java, "miBD").allowMainThreadQueries().build()
        db= dataBase.usuarioDao()
        view.findViewById<TextView>(R.id.textViewRegister).setOnClickListener {  }
        buttonLogin.setOnClickListener { controlBotonStart(it) }

    }

    /**
     * Acciones que se realizan al presionar el botón de iniciar sesión
     */
    private fun controlBotonStart(view: View){
        if (validaDatos()){
            actividadInicio()
            Toast.makeText(requireActivity(),"Exito!", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Función para obtener los datos de la BD y validar el inicio de sesión
     */
    private fun actividadInicio(){
        val email = editTextCorreo.text.toString().trim { it <= ' ' }
        val password = editTextPassword.text.toString().trim { it <= ' ' }
        val user = db.getUser(email, password)
        if (user != null) {
            val i = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(i)
            activity?.finish()
        } else {
            Toast.makeText(requireActivity(), "Sus datos son incorrectos", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Llamado a la actividad de crear registro
     */
    fun actividadRegistro(){
        val intent : Intent = Intent(requireActivity(), LoginActivity::class.java)
        startActivity(intent)
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
            Toast.makeText(requireActivity(), "Faltan datos", Toast.LENGTH_SHORT)
        }
        if(c2.isNullOrEmpty()){
            retorno=false
            textPass.error="Ingrese una contraseña"
            Toast.makeText(requireContext(), "Faltan datos", Toast.LENGTH_SHORT).show()
        }
        return retorno
    }
}