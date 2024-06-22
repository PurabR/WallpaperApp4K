package com.homedrop.common.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * A generic ViewHolder that works with a [ViewDataBinding].
 *
 * @param <T> The type of the ViewDataBinding.
</T> */
class DataBoundViewHolder<T : ViewBinding>(
    val binding: T
) : RecyclerView.ViewHolder(binding.root)