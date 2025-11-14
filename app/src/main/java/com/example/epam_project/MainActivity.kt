package com.example.epam_project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.preference.PreferenceManager
import com.example.epam_project.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val mode = if (prefs.getBoolean("dark_mode", false))
            AppCompatDelegate.MODE_NIGHT_YES
        else
            AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
                as NavHostFragment? ?: NavHostFragment.create(R.navigation.nav_graph).also {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_container, it)
                .setPrimaryNavigationFragment(it)
                .commitNow()
        }
        val navController = navHost.navController

        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)

        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> navController.navigate(R.id.articleListFragment)
                R.id.nav_settings -> startActivity(Intent(this, SettingsActivity::class.java))
                R.id.nav_profile -> navController.navigate(R.id.profileFragment)
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
                as NavHostFragment
        return navHost.navController.navigateUp(binding.drawerLayout)
    }
}
