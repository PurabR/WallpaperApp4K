package com.homedrop.common.base

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.homedrop.common.ViewType

abstract class ViewDataBinder<V : ViewDataBinding, T : BaseViewType> {

    @get:ViewType
    abstract val viewType: Int

    abstract fun createBinder(parent: ViewGroup): V

    abstract fun bindData(binding: V, data: T, position: Int)

    open fun bindDataWithPayload(binding: V, data: T?, position: Int) {}
}