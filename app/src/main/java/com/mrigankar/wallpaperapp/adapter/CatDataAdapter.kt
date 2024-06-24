package com.mrigankar.wallpaperapp.adapter

import androidx.viewbinding.ViewBinding
import com.homedrop.common.base.BaseRecyclerAdapter
import com.homedrop.common.base.BaseViewType
import com.homedrop.common.base.ViewDataBinder
import com.mrigankar.wallpaperapp.ViewBinder.TitileBinder.TitleViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.bestofmonth.bomViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import com.mrigankar.wallpaperapp.categories.CategoriesDataViewBinder
import javax.inject.Inject

class CatDataAdapter @Inject constructor(
    private val catDataBinder: CategoriesDataViewBinder
): BaseRecyclerAdapter<BaseViewType>() {
    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): List<ViewDataBinder<ViewBinding, BaseViewType>> {
        val viewDataBinders = ArrayList<ViewDataBinder<*, *>>(2)
        viewDataBinders.add(catDataBinder)


        return viewDataBinders as List<ViewDataBinder<ViewBinding, BaseViewType>>

    }
}

interface CategoryDataAdapterListener  {

    fun onCategoryClicked(category: CategoriesViewData)

}