package com.mrigankar.wallpaperapp.ViewBinder.setWallpaper


import android.os.Parcelable
import com.homedrop.common.ITEM_SET_WALLPAPER
import com.homedrop.common.base.BaseViewType
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SetWallpaperViewData (
    val id : String ="",
    val link : String = "",
    override val viewType: Int = ITEM_SET_WALLPAPER
): BaseViewType, Parcelable