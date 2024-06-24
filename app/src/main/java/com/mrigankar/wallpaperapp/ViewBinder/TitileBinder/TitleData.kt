package com.mrigankar.wallpaperapp.ViewBinder.TitileBinder


import com.homedrop.common.ITEM_TITLE_BOM
import com.homedrop.common.base.BaseViewType

data class TitleData (
    val title : String = " ",
    override val viewType: Int = ITEM_TITLE_BOM
): BaseViewType