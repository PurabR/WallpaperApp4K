package com.mrigankar.wallpaperapp.ViewBinder.bestofmonth

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homedrop.common.ITEM_BOM
import com.homedrop.common.ITEM_CATEGORIES
import com.homedrop.common.base.ViewDataBinder
import com.homedrop.common.util.image.loadImage
import com.mrigankar.wallpaperapp.databinding.ItemBestOfMonthBinding
import javax.inject.Inject

class bomViewBinder @Inject constructor(


): ViewDataBinder<ItemBestOfMonthBinding, bomViewData>() {
    override val viewType: Int
        get() = ITEM_BOM

    override fun createBinder(parent: ViewGroup): ItemBestOfMonthBinding {
        return ItemBestOfMonthBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    }


    override fun bindData(binding: ItemBestOfMonthBinding, data: bomViewData, position: Int) {
        binding.apply {
            loadImage(ivImage, data.link, cornerRadius = 10)
        }
    }
}