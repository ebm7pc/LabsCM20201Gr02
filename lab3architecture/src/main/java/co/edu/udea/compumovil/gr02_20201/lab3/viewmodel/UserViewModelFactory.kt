package co.edu.udea.compumovil.gr02_20201.lab3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.compumovil.gr02_20201.lab3.repo.UserRepository

@Suppress("UNCHECKED CAST")
class UserViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {//ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}