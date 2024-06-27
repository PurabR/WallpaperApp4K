package com.mrigankar.wallpaperapp.ViewBinder.setWallpaper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homedrop.common.ITEM_BOM
import com.homedrop.common.ITEM_SET_WALLPAPER
import com.homedrop.common.ITEM_SPECIFIC_CATEGORIES
import com.homedrop.common.base.ViewDataBinder
import com.homedrop.common.util.image.loadImage
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.specificCategories.SpecificCategoriesViewData
import com.mrigankar.wallpaperapp.adapter.ImageAdapter
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener
import com.mrigankar.wallpaperapp.adapter.SetWallpaperAdapterListener
import com.mrigankar.wallpaperapp.adapter.SpecificCategoriesAdapterListener
import com.mrigankar.wallpaperapp.databinding.ItemBestOfMonthBinding
import com.mrigankar.wallpaperapp.databinding.ItemImageBinding
import javax.inject.Inject

class SetWallpaperViewBinder @Inject constructor(
    private val listener: SetWallpaperAdapterListener
): ViewDataBinder<ItemImageBinding, SetWallpaperViewData>() {
    override val viewType: Int
        get() = ITEM_SET_WALLPAPER

    override fun createBinder(parent: ViewGroup): ItemImageBinding {
        return ItemImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    }


    override fun bindData(
        binding: ItemImageBinding,
        data: SetWallpaperViewData,
        position: Int
    ) {
        binding.apply {
            loadImage(binding.imageView, data.link)
            binding.root.setOnClickListener {
                listener.onSetWallpaperClicked(data)
            }
        }

    }
}