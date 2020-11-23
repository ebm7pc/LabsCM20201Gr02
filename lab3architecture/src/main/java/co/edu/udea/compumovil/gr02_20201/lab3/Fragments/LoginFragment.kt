package co.edu.udea.compumovil.gr02_20201.lab3.Fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab3.Descriptions
import co.edu.udea.compumovil.gr02_20201.lab3.LongDescriptions
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.PlaceDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.DataBase.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.Place
import co.edu.udea.compumovil.gr02_20201.lab3.R
import co.edu.udea.compumovil.gr02_20201.lab3.Repositorio.UserRepository
import co.edu.udea.compumovil.gr02_20201.lab3.ViewModel.UserViewModel
import co.edu.udea.compumovil.gr02_20201.lab3.ViewModel.UserViewModelFactory
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var textEmail: EditText
    private lateinit var textPass: EditText
    private lateinit var buttonLogin: Button
    private lateinit var registerLink: TextView
    private lateinit var dataBase: LabTresDB
    private lateinit var userDao: UserDao
    private lateinit var placeDao: PlaceDao


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        dataBase= Room.databaseBuilder(requireContext(), LabTresDB::class.java, "mi_db").allowMainThreadQueries().build()
        val userDao: UserDao= LabTresDB.getDatabaseInstance(requireContext()).usuarioDao()
        val repository= UserRepository(dataBase,userDao)
        val factory= UserViewModelFactory(repository)
        viewModel= ViewModelProvider(this, factory).get(UserViewModel::class.java)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textEmail= view.findViewById(R.id.editTextCorreo)
        textPass= view.findViewById(R.id.editTextPassword)
        buttonLogin= view.findViewById(R.id.buttonStart)
        registerLink= view.findViewById<TextView>(R.id.textViewRegister)

        //dataBase= Room.databaseBuilder(requireContext(), LabTresDB::class.java, "mi_db").allowMainThreadQueries().build()
        userDao= dataBase.usuarioDao()
        placeDao= dataBase.lugarDao()
        createViewModel()
        buttonLogin.setOnClickListener { controlBotonStart(it) }
        registerLink.setOnClickListener { lanzaFragmentRegistro() }
    }

    private fun createViewModel(){
        val userDao: UserDao= LabTresDB.getDatabaseInstance(requireContext()).usuarioDao()
        val repository= UserRepository(dataBase,userDao)
        val factory= UserViewModelFactory(repository)
        viewModel= ViewModelProvider(this, factory).get(UserViewModel::class.java)
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

    private fun lanzaFragmentRegistro(){
        val fragmentRegistro= RegistryFragment()
        val fragmentManager= requireActivity().supportFragmentManager
        val fragmentTransaction= fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_fragment, fragmentRegistro)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        hideKeyboard()
    }

    private fun lanzaFragmentLugares(){
        val fragmentLugares= LugaresFragment()
        val fragmentManager= requireActivity().supportFragmentManager
        val fragmentTransaction= fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_fragment, fragmentLugares)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    /**
     * Función para obtener los datos de la BD y validar el inicio de sesión
     */
    private fun actividadInicio(){
        val email = editTextCorreo.text.toString().trim { it <= ' ' }
        val password = editTextPassword.text.toString().trim { it <= ' ' }
        val user= viewModel.userLogin(email,password)
        if (user != null) {
            guardarListaLugares()
            lanzaFragmentLugares()
            Toast.makeText(requireActivity(),"Exito!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireActivity(), "Sus datos son incorrectos", Toast.LENGTH_LONG).show()
            textEmail.error="No existe o error al escribir datos"
            textPass.error="No existe o error al escribir datos"
        }
    }

    /**
     * Valida que los campos de inicio de sesión no estén vacíos
     */
    private fun validaDatos():Boolean{
        var retorno=true
        val c1=textEmail.text.toString()
        val c2=textPass.text.toString()
        if(c1.isNullOrEmpty()){
            retorno=false
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

    fun Fragment.hideKeyboard(){
        view?.let { activity?.hideKeyBoard(it) }
    }
    fun Context.hideKeyBoard(view: View){
        val imm= getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun guardarListaLugares(){
        placeDao= dataBase.lugarDao()
        val lugar = listOf<Place>(
            Place(1,"Santorini", Descriptions().santorini, LongDescriptions().santorini, R.drawable.santorini1),
            Place(2,"Naxos", Descriptions().naxos, LongDescriptions().naxos, R.drawable.naxos1),
            Place(3,"Rodas", Descriptions().rodas, LongDescriptions().rodas, R.drawable.rodas1),
            Place(4,"Mykonos", Descriptions().miconos, LongDescriptions().miconos, R.drawable.mykonos1),
            Place(5,"Olimpia", Descriptions().olimpia, LongDescriptions().olimpia, R.drawable.olimpia1)
        )
        if (esTablaLugaresVacia()){ placeDao.insertAll(lugar)}
    }

    fun esTablaLugaresVacia():Boolean{
        var retorno=false
        val lugar= placeDao.getPlaces()
        if (lugar.isEmpty()){retorno=true}
        return retorno
    }
}