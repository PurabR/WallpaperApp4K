package com.mrigankar.wallpaperapp.setwallpaper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.homedrop.common.base.BaseFragment
import com.homedrop.common.ktx.showShortToast
import com.mrigankar.wallpaperapp.R
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import com.mrigankar.wallpaperapp.adapter.HomeAdapter
import com.mrigankar.wallpaperapp.adapter.HomeAdapterListener
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener
import com.mrigankar.wallpaperapp.databinding.FragmentHomeBinding
import com.mrigankar.wallpaperapp.databinding.FragmentSetWallpaperBinding
import com.mrigankar.wallpaperapp.home.HomeFragmentDirections
import com.mrigankar.wallpaperapp.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SetWallpaperFragment : BaseFragment<FragmentSetWallpaperBinding, SetWallpaperViewModel>(){

    override fun getViewBinding(): FragmentSetWallpaperBinding {
        return FragmentSetWallpaperBinding.inflate(layoutInflater)

    }

    override fun getViewModelClass(): Class<SetWallpaperViewModel>? {
        return SetWallpaperViewModel::class.java
    }

    override fun setUpViews() {
        super.setUpViews()



    }


}