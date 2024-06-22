package com.mrigankar.wallpaperapp.ViewBinder.categories

import com.homedrop.common.ITEM_CATEGORIES
import com.homedrop.common.base.BaseViewType

data class CategoriesViewData (
    val id : String,
    val link : String,
    override val viewType: Int = ITEM_CATEGORIES
): BaseViewType