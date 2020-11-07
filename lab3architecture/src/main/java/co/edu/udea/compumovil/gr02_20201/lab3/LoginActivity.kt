package co.edu.udea.compumovil.gr02_20201.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import co.edu.udea.compumovil.gr02_20201.lab3.Entidades.Persona
import co.edu.udea.compumovil.gr02_20201.lab3.Fragments.DetallePersonaFragment
import co.edu.udea.compumovil.gr02_20201.lab3.Fragments.LoginFragment
import co.edu.udea.compumovil.gr02_20201.lab3.Fragments.PersonasFragment
import co.edu.udea.compumovil.gr02_20201.lab3.Interfaces.iComunicaFragments
import com.google.android.material.navigation.NavigationView


class LoginActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, iComunicaFragments {
    var drawerLayout: DrawerLayout? = null
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    var toolbar: Toolbar? = null
    var navigationView: NavigationView? = null
    var fragmentManager: FragmentManager? = null
    var fragmentTransaction: FragmentTransaction? = null
    var detallePersonaFragment: DetallePersonaFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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
            fragmentTransaction!!.replace(R.id.container_fragment, PersonasFragment())
            fragmentTransaction!!.commit()
        }
        return false
    }

    override fun enviarPersona(persona: Persona) {
        //gracias a hbaer implementado de la interface "iComunicaFragments" se tiene la implementacion del metodo enviarPersona
        //o mejor dicho este metodo.
        //Aqui se realiza toda la logica necesaria para poder realizar el envio
        detallePersonaFragment = DetallePersonaFragment()
        //objeto bundle para transportar la informacion
        val bundleEnvio = Bundle()
        //se manda el objeto que le esta llegando:
        bundleEnvio.putSerializable("objeto", persona)
        detallePersonaFragment!!.arguments = bundleEnvio

        //Cargar fragment en el activity
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.container_fragment, detallePersonaFragment!!)
        fragmentTransaction!!.addToBackStack(null)
        fragmentTransaction!!.commit()

        /*
         getSupportFragmentManager().beginTransaction()
                 .replace(R.id.container_fragment, detallePersonaFragment)
                 .addToBackStack(null).commit();
        */
        //***Luego pasar a programar al fragmentdetalle
    }
}

/*
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}*/
