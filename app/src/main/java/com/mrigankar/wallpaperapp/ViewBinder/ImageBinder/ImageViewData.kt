package com.mrigankar.wallpaperapp.ViewBinder.ImageBinder

import android.os.Parcelable
import com.homedrop.common.ITEM_IMAGE
import com.homedrop.common.base.BaseViewType
import kotlinx.parcelize.Parcelize
@Parcelize
data class ImageViewData(
    val id: String ="",
    val link: String = "",
    override val viewType: Int = ITEM_IMAGE

    ) : BaseViewType, Parcelable