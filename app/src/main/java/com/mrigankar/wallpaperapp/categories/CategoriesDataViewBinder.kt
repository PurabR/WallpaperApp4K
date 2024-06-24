package com.mrigankar.wallpaperapp.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homedrop.common.ITEM_CAT_DATA
import com.homedrop.common.base.ViewDataBinder
import com.homedrop.common.util.image.loadImage
import com.mrigankar.wallpaperapp.adapter.CatDataAdapter
import com.mrigankar.wallpaperapp.databinding.ItemCategoriesBinding
import com.mrigankar.wallpaperapp.databinding.ItemCategoryImageBinding
import javax.inject.Inject

class CategoriesDataViewBinder @Inject constructor(
    private val listener: CatDataAdapter
): ViewDataBinder<ItemCategoryImageBinding, CategoriesDataViewData>() {
    private val catDataAdapter = CatDataAdapter(CategoriesDataViewBinder(listener))
        get() = ITEM_CAT_DATA

    override fun createBinder(parent: ViewGroup): ItemCategoryImageBinding {
        return ItemCategoryImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    }

    override fun bindData(
        binding: ItemCategoryImageBinding,
        data: CategoriesDataViewData,
        position: Int
    ) {
        binding.apply {
            recyclerView.adapter = catDataAdapter
        }
    }

}
