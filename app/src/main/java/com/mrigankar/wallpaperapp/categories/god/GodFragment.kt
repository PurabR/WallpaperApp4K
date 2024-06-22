package com.mrigankar.wallpaperapp.categories.god

import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.databinding.FragmentGodBinding


class GodFragment : BaseFragment<FragmentGodBinding, GodViewModel>() {
    override fun getViewBinding(): FragmentGodBinding {
        return FragmentGodBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<GodViewModel>? {
        return GodViewModel::class.java
    }
    override fun setUpViews() {
        super.setUpViews()
    }

}