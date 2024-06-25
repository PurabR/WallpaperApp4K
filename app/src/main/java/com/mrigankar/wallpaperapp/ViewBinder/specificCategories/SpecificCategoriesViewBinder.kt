package com.mrigankar.wallpaperapp.ViewBinder.specificCategories

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import com.homedrop.common.ITEM_SPECIFIC_CATEGORIES
import com.homedrop.common.base.ViewDataBinder
import com.homedrop.common.util.image.loadImage
import com.mrigankar.wallpaperapp.adapter.SpecificCategoriesAdapterListener
import com.mrigankar.wallpaperapp.categories.SpecificCategoriesFragment
import com.mrigankar.wallpaperapp.databinding.ItemCategoriesBinding
import com.mrigankar.wallpaperapp.databinding.ItemImageBinding
import javax.inject.Inject

class SpecificCategoriesViewBinder @Inject constructor(
    private val listener: SpecificCategoriesAdapterListener
) : ViewDataBinder<ItemImageBinding, SpecificCategoriesViewData>() {
    override val viewType: Int
        get() = ITEM_SPECIFIC_CATEGORIES

    override fun createBinder(parent: ViewGroup): ItemImageBinding {
        return ItemImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    }


    override fun bindData(
        binding: ItemImageBinding,
        data: SpecificCategoriesViewData,
        position: Int
    ) {
        binding.apply {
            loadImage(binding.imageView, data.link)
            binding.root.setOnClickListener {
                listener.onSpecificCategoriesClicked(data)
            }
        }

    }
}