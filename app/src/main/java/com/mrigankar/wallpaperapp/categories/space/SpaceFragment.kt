package com.mrigankar.wallpaperapp.categories.space

import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.databinding.FragmentSpaceBinding

class SpaceFragment : BaseFragment<FragmentSpaceBinding, SpaceViewModel>() {
    override fun getViewBinding(): FragmentSpaceBinding {
        return FragmentSpaceBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<SpaceViewModel>? {
        return SpaceViewModel::class.java
    }
    override fun setUpViews() {
        super.setUpViews()
    }

}