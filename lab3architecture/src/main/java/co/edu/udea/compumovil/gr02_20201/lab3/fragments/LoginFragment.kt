package co.edu.udea.compumovil.gr02_20201.lab3.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.room.CoroutinesRoom
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab3.data.dao.PlaceDao
import co.edu.udea.compumovil.gr02_20201.lab3.data.dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab3.data.database.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.R
import co.edu.udea.compumovil.gr02_20201.lab3.repo.UserRepository
import co.edu.udea.compumovil.gr02_20201.lab3.viewmodel.UserViewModel
import co.edu.udea.compumovil.gr02_20201.lab3.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class LoginFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var textEmail: EditText
    private lateinit var textPass: EditText
    private lateinit var buttonLogin: Button
    private lateinit var registerLink: TextView
    private lateinit var dataBase: LabTresDB

    private var contx: CoroutineContext = Dispatchers.IO
    private lateinit var scope: CoroutineScope

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBase = Room.databaseBuilder(requireContext(), LabTresDB::class.java, "mi_db")
            .allowMainThreadQueries().build()

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textEmail = view.findViewById(R.id.editTextCorreo)
        textPass= view.findViewById(R.id.editTextPassword)
        buttonLogin= view.findViewById(R.id.buttonStart)
        registerLink= view.findViewById<TextView>(R.id.textViewRegister)
        createViewModel()
        buttonLogin.setOnClickListener { controlBotonStart(it) }
        registerLink.setOnClickListener { lanzaFragmentRegistro() }
    }

    private fun createViewModel() {
        scope = CoroutineScope(contx)
        val userDao: UserDao = LabTresDB.getDatabaseInstance(requireContext(), scope).usuarioDao()
        val repository = UserRepository(dataBase, userDao)
        val factory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
    }

    /**
     * Acciones que se realizan al presionar el botón de iniciar sesión
     */
    private fun controlBotonStart(view: View){
        if (validaDatos()){
            actividadInicio()
            hideKeyboard()
        }
    }

    /**
     * Función para obtener los datos a través del viewModel y validar el inicio de sesión
     * También guarda la lista de lugares por defecto por primera vez
     */
    private fun actividadInicio(){
        val email = editTextCorreo.text.toString().trim { it <= ' ' }
        val password = editTextPassword.text.toString().trim { it <= ' ' }
        val user= viewModel.userLogin(email, password)
        if (user != null) {
            lanzaFragmentLugares()
            //Toast.makeText(requireActivity(),"Exito!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireActivity(), "Sus datos son incorrectos", Toast.LENGTH_LONG).show()
            textEmail.error = "No existe o error al escribir datos"
            textPass.error = "No existe o error al escribir datos"
        }
    }

    /**
     * Prepara el fragment con la lista de lugares
     */
    private fun lanzaFragmentLugares() {
        val fragmentLugares = PlaceListFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_fragment, fragmentLugares)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    /**
     * Prepara el fragment con el formulario de registro
     */
    private fun lanzaFragmentRegistro() {
        val fragmentRegistro = RegistryFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_fragment, fragmentRegistro)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        hideKeyboard()
    }

    /**
     * Valida que los campos de inicio de sesión no estén vacíos
     */
    private fun validaDatos(): Boolean {
        var retorno = true
        val c1 = textEmail.text.toString()
        val c2 = textPass.text.toString()
        if (c1.isNullOrEmpty()) {
            retorno = false
            textEmail.error="Ingrese un correo"
            Toast.makeText(requireActivity(), "Faltan datos", Toast.LENGTH_SHORT)
        }
        if(c2.isNullOrEmpty()){
            retorno=false
            textPass.error="Ingrese una contraseña"
            Toast.makeText(requireContext(), "Faltan datos", Toast.LENGTH_SHORT).show()
        }
        return retorno
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyBoard(it) }
    }

    private fun Context.hideKeyBoard(view: View) {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}