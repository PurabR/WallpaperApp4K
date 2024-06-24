package com.mrigankar.wallpaperapp.MainActivity

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.homedrop.common.base.BaseActivity
import com.mrigankar.wallpaperapp.R
import com.mrigankar.wallpaperapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    //MainActivity

    //Splash
    //Home
    //category detail screen
    //image screen

    private lateinit var navController: NavController
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)

    }

    override fun setUpViews() {
        super.setUpViews()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setOnItemSelectedListener {
            if (it.itemId == R.id.action_home) {
                navController.navigate(R.id.homeFragment)
            }
            if (it.itemId == R.id.action_downloads) {
               // navController.navigate(R.id.action_homeFragment_to_downloadFragment)
            }



            return@setOnItemSelectedListener true
        }
    }


}