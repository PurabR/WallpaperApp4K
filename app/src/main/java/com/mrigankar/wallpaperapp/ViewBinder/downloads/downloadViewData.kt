package com.mrigankar.wallpaperapp.ViewBinder.downloads

import android.os.Parcelable
import com.homedrop.common.ITEM_DOWNLOAD
import com.homedrop.common.ITEM_IMAGE
import com.homedrop.common.base.BaseViewType
import com.mrigankar.wallpaperapp.adapter.DownloadAdapterListener
import kotlinx.parcelize.Parcelize

@Parcelize
data class downloadViewData(
    val image: String,
    override val viewType: Int = ITEM_DOWNLOAD

) : BaseViewType, Parcelable