package com.mrigankar.wallpaperapp.categories.technology

import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.databinding.FragmentTechnologyBinding


class TechnologyFragment : BaseFragment<FragmentTechnologyBinding, TechnologyViewModel>() {
    override fun getViewBinding(): FragmentTechnologyBinding {
        return FragmentTechnologyBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<TechnologyViewModel>? {
        return TechnologyViewModel::class.java
    }

    override fun setUpViews() {
        super.setUpViews()
    }

}