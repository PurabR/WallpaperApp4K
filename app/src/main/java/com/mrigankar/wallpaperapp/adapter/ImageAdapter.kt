package com.mrigankar.wallpaperapp.adapter

import androidx.viewbinding.ViewBinding
import com.homedrop.common.base.BaseRecyclerAdapter
import com.homedrop.common.base.BaseViewType
import com.homedrop.common.base.ViewDataBinder
import com.mrigankar.wallpaperapp.ViewBinder.ImageViewData
import com.mrigankar.wallpaperapp.ViewBinder.bestofmonth.ImageViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewBinder
import javax.inject.Inject

class ImageAdapter (
    private val imageViewBinder: ImageViewBinder
): BaseRecyclerAdapter<BaseViewType>() {
    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): List<ViewDataBinder<ViewBinding, BaseViewType>> {
        val viewDataBinders = ArrayList<ViewDataBinder<*, *>>(1)
        viewDataBinders.add(imageViewBinder)
        return viewDataBinders as List<ViewDataBinder<ViewBinding, BaseViewType>>
    }
}

interface ImageAdapterListener {

    fun onImageClicked(image: ImageViewData)

}