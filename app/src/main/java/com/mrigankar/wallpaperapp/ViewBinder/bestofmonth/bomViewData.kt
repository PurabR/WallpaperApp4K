package com.mrigankar.wallpaperapp.ViewBinder.bestofmonth

import com.homedrop.common.ITEM_BOM
import com.homedrop.common.base.BaseViewType

data class bomViewData (
    val id : String ="",
    val link : String="",
    override val viewType: Int = ITEM_BOM
): BaseViewType

