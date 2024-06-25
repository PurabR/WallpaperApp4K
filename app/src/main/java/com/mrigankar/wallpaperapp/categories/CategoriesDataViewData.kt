package com.mrigankar.wallpaperapp.categories

import com.homedrop.common.ITEM_CAT_DATA
import com.homedrop.common.base.BaseViewType
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData

data class CategoriesDataViewData(
    val items: List<ImageViewData>,
    override val viewType: Int = ITEM_CAT_DATA
): BaseViewType
