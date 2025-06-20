package com.mrigankar.wallpaperapp.ViewBinder.bestofmonth

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homedrop.common.ITEM_BOM
import com.homedrop.common.base.ViewDataBinder
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewBinder
import com.mrigankar.wallpaperapp.adapter.ImageAdapter
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener
import com.mrigankar.wallpaperapp.databinding.ItemBestOfMonthBinding
import javax.inject.Inject

class bomViewBinder @Inject constructor(
    private val imageAdapterListener: ImageAdapterListener
): ViewDataBinder<ItemBestOfMonthBinding, BomViewDataItems>() {

    private val imageAdapter = ImageAdapter(ImageViewBinder(imageAdapterListener))

    override val viewType: Int
        get() = ITEM_BOM

    override fun createBinder(parent: ViewGroup): ItemBestOfMonthBinding {
        return ItemBestOfMonthBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    }


    override fun bindData(binding: ItemBestOfMonthBinding, data: BomViewDataItems, position: Int) {
        binding.apply {
            recyclerView.adapter = imageAdapter
        }
        imageAdapter.setItems(data.items)
    }
}