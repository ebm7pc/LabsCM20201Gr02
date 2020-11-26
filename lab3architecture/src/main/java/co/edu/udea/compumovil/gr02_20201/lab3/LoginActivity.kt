package co.edu.udea.compumovil.gr02_20201.lab3

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import co.edu.udea.compumovil.gr02_20201.lab3.data.entities.Place
import co.edu.udea.compumovil.gr02_20201.lab3.fragments.LoginFragment
import co.edu.udea.compumovil.gr02_20201.lab3.fragments.PlaceDetailFragment
import co.edu.udea.compumovil.gr02_20201.lab3.fragments.PlaceListFragment
import co.edu.udea.compumovil.gr02_20201.lab3.interfaces.iComunicaFragments
import com.google.android.material.navigation.NavigationView


class LoginActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    iComunicaFragments {
    var drawerLayout: DrawerLayout? = null
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    var toolbar: Toolbar? = null
    var navigationView: NavigationView? = null
    var fragmentManager: FragmentManager? = null
    var fragmentTransaction: FragmentTransaction? = null
    var placeDetailFragment: PlaceDetailFragment? = null

    //private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawer)
        navigationView = findViewById(R.id.navigationView)
        navigationView!!.setNavigationItemSelectedListener(this)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout!!.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.isDrawerIndicatorEnabled = true
        actionBarDrawerToggle!!.syncState()
        //cargar fragment principal en la actividad
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
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
            fragmentTransaction!!.replace(R.id.container_fragment, PlaceListFragment())
            fragmentTransaction!!.commit()
        }
        return false
    }

    override fun onClickSendPlace(lugar: Place) {
        //gracias a haber implementado de la interface "iComunicaFragments" se tiene la implementacion de este metodo.
        placeDetailFragment = PlaceDetailFragment()
        //objeto bundle para transportar la informacion
        val bundleEnvio = Bundle()
        //se manda el objeto que le esta llegando:
        bundleEnvio.putSerializable("objeto", lugar)
        placeDetailFragment!!.arguments = bundleEnvio

        //Cargar fragment en el activity
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.container_fragment, placeDetailFragment!!)
        fragmentTransaction!!.addToBackStack(null)
        fragmentTransaction!!.commit()
    }

}


