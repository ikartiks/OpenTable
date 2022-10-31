package com.kartik.grevocab

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.kartik.grevocab.base.ActivityBase
import com.kartik.grevocab.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.nav_host_fragment
import org.koin.core.component.KoinComponent

/**
 * @author kartikshah
 *
 */
class ActivityMain : ActivityBase(), NavigationView.OnNavigationItemSelectedListener, KoinComponent {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.white))
        binding.navHostFragment.post {
            // avoids crash in findNavController
            navController = findNavController(nav_host_fragment)
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.fragmentSplash,
                    R.id.fragmentReservations
                )
            )

            binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        }
    }

    fun setToolbarVisibility(visibility: Int) {
        binding.toolbar.visibility = visibility
    }

    override fun onOptionsItemSelected(item: MenuItem) = item.onNavDestinationSelected(navController) ||
        super.onOptionsItemSelected(item)

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(nav_host_fragment)
        when (item.itemId) {
            else -> {
                item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
            }
        }
        return true
    }
}
