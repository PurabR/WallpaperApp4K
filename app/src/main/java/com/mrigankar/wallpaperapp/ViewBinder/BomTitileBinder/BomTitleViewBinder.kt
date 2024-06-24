package com.mrigankar.wallpaperapp.ViewBinder.BomTitileBinder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homedrop.common.ITEM_TITLE_BOM
import com.homedrop.common.base.ViewDataBinder
import com.mrigankar.wallpaperapp.databinding.ItemCategoriesBinding
import com.mrigankar.wallpaperapp.databinding.ItemTitleBinding
import javax.inject.Inject

class BomTitleViewBinder @Inject constructor(

): ViewDataBinder<ItemTitleBinding, BomTitleData>() {
    override val viewType: Int
        get() = ITEM_TITLE_BOM

    override fun createBinder(parent: ViewGroup): ItemTitleBinding {
        return ItemTitleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    }


    override fun bindData(binding: ItemTitleBinding, data: BomTitleData, position: Int) {
        binding.apply {
            tvTitle.text = data.name
        }
    }
}