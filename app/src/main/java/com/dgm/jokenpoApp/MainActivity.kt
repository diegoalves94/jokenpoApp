package com.dgm.jokenpoApp

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dgm.jokenpoApp.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: MaterialToolbar

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var drawer: DrawerLayout
    private lateinit var navDrawer: NavigationView
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("LifeCycle", "onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        setupToolbar()
        setupNavigation()
        setupAppBar()
        setupDrawer()
        setupBottomNavigation()
    }

    private fun initComponents() {
        toolbar = binding.mtToolbar
        drawer = binding.root
        navDrawer = binding.navView
        bottomNav = binding.bnvMenuBottom

    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frag_container_view) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id){
                R.id.startFragment ->{
                    binding.bnvMenuBottom.visibility = View.GONE
                }
                else ->{
                    binding.bnvMenuBottom.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupAppBar() {
        appBarConfiguration = AppBarConfiguration(setOf(R.id.playFragment, R.id.resultFragment), drawer)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupDrawer() {
        navDrawer.setupWithNavController(navController)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        toolbar.navigationIcon = null
//        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        toolbar.overflowIcon?.colorFilter =
            PorterDuffColorFilter(getColor(R.color.teal_200), PorterDuff.Mode.SRC_ATOP)
    }

    private fun setupBottomNavigation() {
        bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle", "onResume")

     }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle", "onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle", "onDestroy")

    }
}