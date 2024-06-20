package com.mrigankar.wallpaperapp.categories

import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.databinding.FragmentMinimalBinding

class MinimalFragment : BaseFragment<FragmentMinimalBinding, MinimalViewModel>() {
    override fun getViewBinding(): FragmentMinimalBinding {
        return FragmentMinimalBinding.inflate(layoutInflater)
    }
}