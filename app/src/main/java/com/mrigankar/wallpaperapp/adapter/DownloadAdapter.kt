package com.mrigankar.wallpaperapp.adapter

import androidx.viewbinding.ViewBinding
import com.homedrop.common.base.BaseRecyclerAdapter
import com.homedrop.common.base.BaseViewType
import com.homedrop.common.base.ViewDataBinder
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import com.mrigankar.wallpaperapp.ViewBinder.downloads.downloadViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.downloads.downloadViewData
import javax.inject.Inject

class DownloadAdapter(
    private val downloadViewBinder: downloadViewBinder
) : BaseRecyclerAdapter<BaseViewType>() {
    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): List<ViewDataBinder<ViewBinding, BaseViewType>> {
        val viewDataBinders = ArrayList<ViewDataBinder<*, *>>(1)
        viewDataBinders.add(downloadViewBinder)
        return viewDataBinders as List<ViewDataBinder<ViewBinding, BaseViewType>>
    }
}

interface DownloadAdapterListener {

    fun onImageClicked(image: downloadViewData)


}