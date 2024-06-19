package com.mrigankar.wallpaperapp.MainActivity

import com.homedrop.common.base.BaseActivity
import com.mrigankar.wallpaperapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setUpViews() {
        super.setUpViews()
    }


}