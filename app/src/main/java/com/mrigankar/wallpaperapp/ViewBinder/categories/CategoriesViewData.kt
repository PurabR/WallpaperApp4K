package com.mrigankar.wallpaperapp.ViewBinder.categories

import android.os.Parcelable
import com.homedrop.common.ITEM_CATEGORIES
import com.homedrop.common.base.BaseViewType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoriesViewData (
    val id : String = " ",
    val link : String = " ",
    val name : String = " ",
    override val viewType: Int = ITEM_CATEGORIES
): BaseViewType, Parcelable
