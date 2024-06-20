package com.mrigankar.wallpaperapp.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.R
import com.mrigankar.wallpaperapp.databinding.FragmentGodBinding


class GodFragment : BaseFragment<FragmentGodBinding,GodViewModel>() {
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