package com.mrigankar.wallpaperapp.categories.minimal

import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.databinding.FragmentMinimalBinding

class MinimalFragment : BaseFragment<FragmentMinimalBinding, MinimalViewModel>() {
    override fun getViewBinding(): FragmentMinimalBinding {
        return FragmentMinimalBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<MinimalViewModel>? {
        return MinimalViewModel::class.java
    }

    override fun setUpViews() {
        super.setUpViews()
    }
}