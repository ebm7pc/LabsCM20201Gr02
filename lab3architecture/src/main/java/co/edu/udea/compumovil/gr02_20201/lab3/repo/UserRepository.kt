package co.edu.udea.compumovil.gr02_20201.lab3.repo

import co.edu.udea.compumovil.gr02_20201.lab3.data.dao.UserDao
import co.edu.udea.compumovil.gr02_20201.lab3.data.database.LabTresDB
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.User

//class UserRepository (private val daoUser: UserDao){
class UserRepository(private val db: LabTresDB, private val daoUser: UserDao) {

    suspend fun insertUser(user: User) {//=db.usuarioDao().getUser(mail,pwd)
        daoUser.insertUser(user)
    }

    fun getUser(mail: String, pwd: String) = db.usrDao().getUser(mail, pwd)

}