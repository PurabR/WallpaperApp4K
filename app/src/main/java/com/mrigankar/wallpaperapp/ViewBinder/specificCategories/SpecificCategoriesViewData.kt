package com.mrigankar.wallpaperapp.ViewBinder.specificCategories

import android.os.Parcelable
import com.homedrop.common.ITEM_SPECIFIC_CATEGORIES
import com.homedrop.common.base.BaseViewType
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpecificCategoriesViewData (
    val id : String = " ",
    val link : String = " ",
    val name : String = " ",
    override val viewType: Int = ITEM_SPECIFIC_CATEGORIES
): BaseViewType, Parcelable