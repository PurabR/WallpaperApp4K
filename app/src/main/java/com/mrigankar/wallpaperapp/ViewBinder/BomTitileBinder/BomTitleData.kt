package com.mrigankar.wallpaperapp.ViewBinder.BomTitileBinder


import com.homedrop.common.ITEM_TITLE_BOM
import com.homedrop.common.base.BaseViewType

data class BomTitleData (
    val name : String = " ",
    override val viewType: Int = ITEM_TITLE_BOM
): BaseViewType