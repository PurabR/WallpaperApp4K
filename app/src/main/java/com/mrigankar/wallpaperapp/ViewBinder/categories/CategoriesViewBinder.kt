package com.mrigankar.wallpaperapp.ViewBinder.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homedrop.common.ITEM_CATEGORIES
import com.homedrop.common.base.ViewDataBinder
import com.homedrop.common.util.image.loadImage
import com.mrigankar.wallpaperapp.databinding.ItemCategoriesBinding
import javax.inject.Inject

class CategoriesViewBinder @Inject constructor(


):ViewDataBinder<ItemCategoriesBinding, CategoriesViewData>() {
    override val viewType: Int
        get() = ITEM_CATEGORIES

    override fun createBinder(parent: ViewGroup): ItemCategoriesBinding {
        return ItemCategoriesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    }


    override fun bindData(binding: ItemCategoriesBinding, data: CategoriesViewData, position: Int) {
        binding.apply {
            loadImage(ivImage, data.link, cornerRadius = 10)
            tvTitle.text = data.name
        }
    }
}
///