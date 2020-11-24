package co.edu.udea.compumovil.gr02_20201.lab3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.compumovil.gr02_20201.lab3.repo.PlaceRepository

class PlaceViewModelFactory(private val repository: PlaceRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaceViewModel::class.java)){
            return PlaceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}