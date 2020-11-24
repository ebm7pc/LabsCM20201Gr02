package co.edu.udea.compumovil.gr02_20201.lab3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab3.data.dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab3.data.database.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.User
import co.edu.udea.compumovil.gr02_20201.lab3.R
import co.edu.udea.compumovil.gr02_20201.lab3.repo.UserRepository
import co.edu.udea.compumovil.gr02_20201.lab3.viewmodel.UserViewModel
import co.edu.udea.compumovil.gr02_20201.lab3.viewmodel.UserViewModelFactory

class RegistryFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var editTextUsuario: EditText
    private lateinit var editTextCorreo: EditText
    private lateinit var editTextPass: EditText
    private lateinit var buttonRegistro: Button

    private lateinit var dataBase: LabTresDB

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

        dataBase= Room.databaseBuilder(requireContext(), LabTresDB::class.java, "mi_db").allowMainThreadQueries().build()

        createViewModel()
        buttonRegistro.setOnClickListener { registraUsuario()}
    }

    private fun createViewModel(){
        val userDao: UserDao= LabTresDB.getDatabaseInstance(requireContext()).usuarioDao()
        val repository= UserRepository(dataBase, userDao)
        val factory= UserViewModelFactory(repository)
        viewModel= ViewModelProvider(this, factory).get(UserViewModel::class.java)
    }



    private fun registraUsuario(){
        val userName = editTextUsuario.text.toString().trim { it <= ' ' }
        val email = editTextCorreo.text.toString().trim { it <= ' ' }
        val password = editTextPass.text.toString().trim { it <= ' ' }
        val usuario = User(email, password,userName)
        viewModel.insert(usuario)
        Toast.makeText(requireActivity(), "¡Registro Exitoso!", Toast.LENGTH_LONG).show()
        createFragment()
    }

    private fun createFragment(){
        val fragmentLogin= LoginFragment()
        val fragmentManager= requireActivity().supportFragmentManager
        val fragmentTransaction= fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_fragment, fragmentLogin)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}