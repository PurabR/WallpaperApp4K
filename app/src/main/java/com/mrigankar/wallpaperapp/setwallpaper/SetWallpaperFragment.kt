package com.mrigankar.wallpaperapp.setwallpaper

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.homedrop.common.base.BaseFragment
import com.homedrop.common.ktx.showShortToast
import com.mrigankar.wallpaperapp.SpecificCategories.SpecificCategoriesFragmentArgs
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import com.mrigankar.wallpaperapp.ViewBinder.setWallpaper.SetWallpaperViewData
import com.mrigankar.wallpaperapp.ViewBinder.specificCategories.SpecificCategoriesViewData
import com.mrigankar.wallpaperapp.adapter.ImageAdapter
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener
import com.mrigankar.wallpaperapp.adapter.SetWallpaperAdapter
import com.mrigankar.wallpaperapp.adapter.SetWallpaperAdapterListener
import com.mrigankar.wallpaperapp.adapter.SpecificCategoriesAdapterListener
import com.mrigankar.wallpaperapp.databinding.FragmentSetWallpaperBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SetWallpaperFragment : BaseFragment<FragmentSetWallpaperBinding, SetWallpaperViewModel>(),
    SetWallpaperAdapterListener {

    @Inject
    lateinit var setWallpaperAdapter: SetWallpaperAdapter
    lateinit var setExtra: SpecificCategoriesViewData

    override fun getViewBinding(): FragmentSetWallpaperBinding {
        return FragmentSetWallpaperBinding.inflate(layoutInflater)

    }

    override fun getViewModelClass(): Class<SetWallpaperViewModel>? {
        return SetWallpaperViewModel::class.java

    }

    override fun receiveExtras() {
        super.receiveExtras()
        setExtra = SetWallpaperFragmentArgs.fromBundle(requireArguments()).setExtra
    }

    override fun setUpViews() {
        super.setUpViews()

        val layoutManager = GridLayoutManager(requireContext(), 6)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (position) {
                    0 -> return 6
                    else -> return 3

                }
            }
        }
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = setWallpaperAdapter

        viewModel.getSetWallpaper(setExtra)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.collector.collectLatest {
                setWallpaperAdapter.setItems(it)
            }
        }


    }

    override fun onSetWallpaperClicked(setWallpaper: SetWallpaperViewData) {
        showShortToast(requireContext(), "Image clicked")
    }

//
}