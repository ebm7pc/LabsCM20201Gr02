package co.edu.udea.compumovil.gr02_20201.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.compumovil.gr02_20201.lab3.Fragments.DetalleLugarFragment
import co.edu.udea.compumovil.gr02_20201.lab3.Fragments.LoginFragment
import co.edu.udea.compumovil.gr02_20201.lab3.Fragments.LugaresFragment
import co.edu.udea.compumovil.gr02_20201.lab3.Interfaces.iComunicaFragments
import co.edu.udea.compumovil.gr02_20201.lab3.Persistencia.Entidades.Place
import co.edu.udea.compumovil.gr02_20201.lab3.ViewModel.UserViewModel
import com.google.android.material.navigation.NavigationView


class LoginActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, iComunicaFragments {
    var drawerLayout: DrawerLayout? = null
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    var toolbar: Toolbar? = null
    var navigationView: NavigationView? = null
    var fragmentManager: FragmentManager? = null
    var fragmentTransaction: FragmentTransaction? = null
    var detalleLugarFragment: DetalleLugarFragment? = null

    //private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //viewModel= ViewModelProvider(this).get(UserViewModel::class.java)

        toolbar = findViewById(R.id.toolbar)
        //setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer)
        navigationView = findViewById(R.id.navigationView)
        //lo sgt se implementa luego de haber implementado NavigationView.OnNavigationItemSelectedListener
        navigationView!!.setNavigationItemSelectedListener(this)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout!!.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.isDrawerIndicatorEnabled = true
        actionBarDrawerToggle!!.syncState()
        //cargar fragment principal en la actividad
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        //fragmentTransaction!!.add(R.id.container_fragment, LoginFragment())
        fragmentTransaction!!.replace(R.id.container_fragment, LoginFragment())
        fragmentTransaction!!.commit()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        //para cerrar automaticamente el menu
        drawerLayout!!.closeDrawer(GravityCompat.START)
        if (menuItem.itemId == R.id.home) {
            fragmentManager = supportFragmentManager
            fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction!!.replace(R.id.container_fragment, LoginFragment())
            fragmentTransaction!!.commit()
        }
        if (menuItem.itemId == R.id.configuracion) {
            fragmentManager = supportFragmentManager
            fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction!!.replace(R.id.container_fragment, LugaresFragment())
            fragmentTransaction!!.commit()
        }
        return false
    }

    override fun enviarLugar(lugar: Place) {
        //gracias a haber implementado de la interface "iComunicaFragments" se tiene la implementacion del metodo enviarLugar
        //o mejor dicho este metodo.
        //Aqui se realiza toda la logica necesaria para poder realizar el envio
        detalleLugarFragment = DetalleLugarFragment()
        //objeto bundle para transportar la informacion
        val bundleEnvio = Bundle()
        //se manda el objeto que le esta llegando:
        bundleEnvio.putSerializable("objeto", lugar)
        detalleLugarFragment!!.arguments = bundleEnvio

        //Cargar fragment en el activity
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.container_fragment, detalleLugarFragment!!)
        fragmentTransaction!!.addToBackStack(null)
        fragmentTransaction!!.commit()
    }

}


