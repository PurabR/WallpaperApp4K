package com.mrigankar.wallpaperapp.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.R
import com.mrigankar.wallpaperapp.databinding.FragmentBikeBinding

class BikeFragment : BaseFragment<FragmentBikeBinding,BikeViewModel>() {
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