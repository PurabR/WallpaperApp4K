package com.mrigankar.wallpaperapp.home

import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<HomeViewModel>? {
        return HomeViewModel::class.java
    }

    override fun setUpViews() {
        super.setUpViews()
    }
}