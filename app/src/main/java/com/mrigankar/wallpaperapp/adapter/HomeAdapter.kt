package com.mrigankar.wallpaperapp.adapter

import androidx.viewbinding.ViewBinding
import com.homedrop.common.base.BaseRecyclerAdapter
import com.homedrop.common.base.BaseViewType
import com.homedrop.common.base.ViewDataBinder
import com.mrigankar.wallpaperapp.ViewBinder.BomTitileBinder.BomTitleViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.bestofmonth.bomViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import javax.inject.Inject

class HomeAdapter @Inject constructor(
    private val bomViewBinder: bomViewBinder,
    private val categoryViewBinder: CategoriesViewBinder,
    private val bomTitleViewBinder: BomTitleViewBinder
): BaseRecyclerAdapter<BaseViewType>() {
    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): List<ViewDataBinder<ViewBinding, BaseViewType>> {
        val viewDataBinders = ArrayList<ViewDataBinder<*, *>>(2)
        viewDataBinders.add(bomViewBinder)
        viewDataBinders.add(categoryViewBinder)
        viewDataBinders.add(bomTitleViewBinder)


        return viewDataBinders as List<ViewDataBinder<ViewBinding, BaseViewType>>

    }
}

interface HomeAdapterListener  {

    fun onCategoryClicked(category: CategoriesViewData)

}