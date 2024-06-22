package com.mrigankar.wallpaperapp.categories.bike

import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.databinding.FragmentBikeBinding

class BikeFragment : BaseFragment<FragmentBikeBinding, BikeViewModel>() {
    override fun getViewBinding(): FragmentBikeBinding {
        return FragmentBikeBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<BikeViewModel>? {
        return BikeViewModel::class.java
    }
    override fun setUpViews() {
        super.setUpViews()
    }

}