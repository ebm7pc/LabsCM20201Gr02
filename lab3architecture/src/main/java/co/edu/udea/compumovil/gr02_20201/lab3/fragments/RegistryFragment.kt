package co.edu.udea.compumovil.gr02_20201.lab3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import co.edu.udea.compumovil.gr02_20201.lab3.R
import co.edu.udea.compumovil.gr02_20201.lab3.data.dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab3.data.database.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.User
import co.edu.udea.compumovil.gr02_20201.lab3.repo.UserRepository
import co.edu.udea.compumovil.gr02_20201.lab3.viewmodel.UserViewModel
import co.edu.udea.compumovil.gr02_20201.lab3.viewmodel.UserViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class RegistryFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var editTextUser: EditText
    private lateinit var editTextMail: EditText
    private lateinit var editTextPass: EditText
    private lateinit var buttonRegister: Button

    private lateinit var dataBase: LabTresDB

    private var contx: CoroutineContext = Dispatchers.IO
    private lateinit var scope: CoroutineScope

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.registry_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTextUser = view.findViewById(R.id.editTextRegUser)
        editTextMail = view.findViewById(R.id.editTextRegEmail)
        editTextPass = view.findViewById(R.id.editTextRegPassword)
        buttonRegister = view.findViewById(R.id.buttonRegGuardar)

        dataBase = Room.databaseBuilder(requireContext(), LabTresDB::class.java, "mi_db")
            .allowMainThreadQueries().build()

        createViewModel()
        buttonRegister.setOnClickListener { registerUser() }
    }

    private fun createViewModel() {
        scope = CoroutineScope(contx)
        val userDao: UserDao = LabTresDB.getDatabaseInstance(requireContext(), scope).usrDao()
        val repository = UserRepository(dataBase, userDao)
        val factory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
    }


    private fun registerUser() {
        val userName = editTextUser.text.toString().trim { it <= ' ' }
        val email = editTextMail.text.toString().trim { it <= ' ' }
        val password = editTextPass.text.toString().trim { it <= ' ' }
        val usuario = User(email, password, userName)
        viewModel.insert(usuario)
        Toast.makeText(requireActivity(), "¡Registro Exitoso!", Toast.LENGTH_LONG).show()
        createFragment()
    }

    private fun createFragment() {
        val fragmentLogin = LoginFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_fragment, fragmentLogin)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}