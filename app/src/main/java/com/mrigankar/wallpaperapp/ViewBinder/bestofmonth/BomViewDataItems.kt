package com.mrigankar.wallpaperapp.ViewBinder.bestofmonth

import com.homedrop.common.ITEM_BOM
import com.homedrop.common.base.BaseViewType
import com.mrigankar.wallpaperapp.ViewBinder.ImageViewData

data class BomViewDataItems (
    val items: List<ImageViewData>,
    override val viewType: Int = ITEM_BOM
): BaseViewType