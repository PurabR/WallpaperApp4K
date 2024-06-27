package com.mrigankar.wallpaperapp.adapter

import androidx.viewbinding.ViewBinding
import com.homedrop.common.base.BaseRecyclerAdapter
import com.homedrop.common.base.BaseViewType
import com.homedrop.common.base.ViewDataBinder
import com.mrigankar.wallpaperapp.ViewBinder.setWallpaper.SetWallpaperViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.setWallpaper.SetWallpaperViewData
import com.mrigankar.wallpaperapp.ViewBinder.specificCategories.SpecificCategoriesViewData
import javax.inject.Inject

class SetWallpaperAdapter @Inject constructor(
    private val setWallpaperViewBinder: SetWallpaperViewBinder

) : BaseRecyclerAdapter<BaseViewType>() {
    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): List<ViewDataBinder<ViewBinding, BaseViewType>> {
        val viewDataBinders = ArrayList<ViewDataBinder<*, *>>(1)
        viewDataBinders.add(setWallpaperViewBinder)
        return viewDataBinders as List<ViewDataBinder<ViewBinding, BaseViewType>>


    }

}

interface SetWallpaperAdapterListener {

    fun onSetWallpaperClicked(setWallpaper: SetWallpaperViewData)

}