package com.mrigankar.wallpaperapp.adapter

import androidx.viewbinding.ViewBinding
import com.homedrop.common.base.BaseRecyclerAdapter
import com.homedrop.common.base.BaseViewType
import com.homedrop.common.base.ViewDataBinder
import com.mrigankar.wallpaperapp.ViewBinder.bestofmonth.bomViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.bestofmonth.bomViewData
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewBinder
import javax.inject.Inject

class HomeAdapter @Inject constructor(
    private val categoriesViewBinder: CategoriesViewBinder,
    private val bomViewBinder: bomViewBinder
): BaseRecyclerAdapter<BaseViewType>() {
    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): List<ViewDataBinder<ViewBinding,BaseViewType>> {
        val viewDataBinders = ArrayList<ViewDataBinder<*, *>>(2)
        viewDataBinders.add(categoriesViewBinder)
        viewDataBinders.add(bomViewBinder)

        return viewDataBinders as List<ViewDataBinder<ViewBinding,BaseViewType>>
    }
}