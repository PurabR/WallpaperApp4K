package com.mrigankar.wallpaperapp.MainActivity

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.homedrop.common.base.BaseActivity
import com.homedrop.common.ktx.gone
import com.homedrop.common.ktx.visible
import com.mrigankar.wallpaperapp.R
import com.mrigankar.wallpaperapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {



    private lateinit var navController: NavController
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)

    }

    override fun setUpViews() {
        super.setUpViews()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.homeFragment) {
                binding.ivMarvel.visible()
                binding.bottomNavigation.visible()
            } else {
                binding.bottomNavigation.gone()
                binding.ivMarvel.gone()
            }
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            if (it.itemId == R.id.action_home) {
                navController.navigate(R.id.homeFragment)
            }
            if (it.itemId == R.id.action_downloads) {
               navController.navigate(R.id.action_homeFragment_to_downloadFragment)
            }
            if (it.itemId == R.id.action_face_recog) {
                navController.navigate(R.id.action_homeFragment_to_moodFragment)
            }



            return@setOnItemSelectedListener true
        }
    }


}