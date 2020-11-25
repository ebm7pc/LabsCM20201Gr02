package co.edu.udea.compumovil.gr02_20201.lab3.fragments

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import co.edu.udea.compumovil.gr02_20201.lab3.Descriptions
import co.edu.udea.compumovil.gr02_20201.lab3.LongDescriptions
import co.edu.udea.compumovil.gr02_20201.lab3.R
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.Place
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.User
import co.edu.udea.compumovil.gr02_20201.lab3.viewmodel.NewPlaceViewModel
import kotlinx.android.synthetic.main.new_place_fragment.*

class NewPlaceFragment : Fragment() {

    companion object {
        fun newInstance() = NewPlaceFragment()
    }

    private lateinit var viewModel: NewPlaceViewModel
    private lateinit var placeNameEditText: EditText
    private lateinit var placeLongDescription: EditText

    private lateinit var addPlacebutton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_place_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        placeNameEditText = input_place_name
        placeLongDescription = input_place_description
        addPlacebutton = new_place_button
        addPlacebutton.setOnClickListener { saveNewPlace() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewPlaceViewModel::class.java)
    }

    private fun saveNewPlace() {
        val placeName = placeNameEditText.text.toString().trim { it <= ' ' }
        val placeDesc = placeLongDescription.text.toString().trim { it <= ' ' }
        val placeShortDesc = viewModel.processShortDescription(placeDesc)
        viewModel.savePlace(
            viewModel.placeData(
                placeName,
                placeShortDesc,
                placeDesc,
                R.drawable.world
            ), requireContext()
        )
        Toast.makeText(requireActivity(), "¡Lugar Agregado!", Toast.LENGTH_LONG).show()
        createFragment()
    }

    private fun createFragment() {
        val fragmentPlaces = PlaceListFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_fragment, fragmentPlaces)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        hideKeyboard()
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyBoard(it) }
    }

    private fun Context.hideKeyBoard(view: View) {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}