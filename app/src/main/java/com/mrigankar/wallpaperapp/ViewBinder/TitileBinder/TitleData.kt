package com.mrigankar.wallpaperapp.ViewBinder.TitileBinder


import com.homedrop.common.ITEM_TITLE
import com.homedrop.common.base.BaseViewType

data class TitleData (
    val title : String = " ",
    val name : String ="",
    override val viewType: Int = ITEM_TITLE
): BaseViewType