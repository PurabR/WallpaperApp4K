package com.mrigankar.wallpaperapp.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.R
import com.mrigankar.wallpaperapp.databinding.FragmentSpaceBinding

class SpaceFragment : BaseFragment<FragmentSpaceBinding,SpaceViewModel>() {
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