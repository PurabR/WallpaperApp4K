package com.mrigankar.wallpaperapp.categories.Car

import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.databinding.FragmentCarBinding

class CarFragment: BaseFragment<FragmentCarBinding, CarViewModel>() {
    override fun getViewBinding(): FragmentCarBinding {
        return FragmentCarBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<CarViewModel>? {
        return CarViewModel::class.java
    }

    override fun setUpViews() {
        super.setUpViews()
    }
}