package com.mrigankar.wallpaperapp.ViewBinder.specificCategories

import com.homedrop.common.ITEM_SPECIFIC_CATEGORIES
import com.homedrop.common.base.BaseViewType

data class SpecificCategoriesViewData (
    val id : String = " ",
    val link : String = " ",
    val name : String = " ",
    override val viewType: Int = ITEM_SPECIFIC_CATEGORIES
): BaseViewType