package com.mrigankar.wallpaperapp.ViewBinder.ImageBinder

import com.homedrop.common.ITEM_IMAGE
import com.homedrop.common.base.BaseViewType

data class ImageViewData(
    val link: String = "",
    override val viewType: Int = ITEM_IMAGE

    ) : BaseViewType