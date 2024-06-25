package com.mrigankar.wallpaperapp.adapter

import androidx.viewbinding.ViewBinding
import com.homedrop.common.base.BaseRecyclerAdapter
import com.homedrop.common.base.BaseViewType
import com.homedrop.common.base.ViewDataBinder
import com.mrigankar.wallpaperapp.ViewBinder.TitileBinder.TitleViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.bestofmonth.bomViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import com.mrigankar.wallpaperapp.ViewBinder.specificCategories.SpecificCategoriesViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.specificCategories.SpecificCategoriesViewData
import javax.inject.Inject

class SpecificCategoriesAdapter @Inject constructor(
    private val specificCategoriesViewBinder: SpecificCategoriesViewBinder,
    private val titleViewBinder: TitleViewBinder
) : BaseRecyclerAdapter<BaseViewType>() {
    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): List<ViewDataBinder<ViewBinding, BaseViewType>> {
        val viewDataBinders = ArrayList<ViewDataBinder<*, *>>(2)
        viewDataBinders.add(specificCategoriesViewBinder)
        viewDataBinders.add(titleViewBinder)


        return viewDataBinders as List<ViewDataBinder<ViewBinding, BaseViewType>>

    }
}

interface SpecificCategoriesAdapterListener {

    fun onSpecificCategoriesClicked(specificCategory: SpecificCategoriesViewData)

}