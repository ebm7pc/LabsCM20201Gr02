package co.edu.udea.compumovil.gr02_20201.lab3.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.DataBase.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.User
import co.edu.udea.compumovil.gr02_20201.lab3.R

class RegistryFragment : Fragment() {
    lateinit var editTextUsuario: EditText
    lateinit var editTextCorreo: EditText
    lateinit var editTextPass: EditText
    lateinit var buttonRegistro: Button
    private lateinit var userDao: UserDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTextUsuario= view.findViewById(R.id.editTextRegUser)
        editTextCorreo= view.findViewById(R.id.editTextRegEmail)
        editTextPass= view.findViewById(R.id.editTextRegPassword)
        buttonRegistro= view.findViewById(R.id.buttonRegGuardar)
        userDao= Room.databaseBuilder(requireContext(), LabTresDB::class.java, "miBD").allowMainThreadQueries().build().usuarioDao()
        buttonRegistro.setOnClickListener { registraUsuario() }
    }

    private fun registraUsuario(){
        val userName = editTextUsuario.text.toString().trim { it <= ' ' }
        val email = editTextCorreo.text.toString().trim { it <= ' ' }
        val password = editTextPass.text.toString().trim { it <= ' ' }
        val usuario = User(email, password,userName)
        userDao.insert(usuario)
        Toast.makeText(requireActivity(), "¡Registro Exitoso!", Toast.LENGTH_LONG).show()
        val fragmentLogin= LoginFragment()
        val fragmentManager= requireActivity().supportFragmentManager
        val fragmentTransaction= fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_fragment, fragmentLogin)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}