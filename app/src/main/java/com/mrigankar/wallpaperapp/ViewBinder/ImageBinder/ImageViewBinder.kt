package com.mrigankar.wallpaperapp.ViewBinder.ImageBinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import com.homedrop.common.ITEM_IMAGE
import com.homedrop.common.base.ViewDataBinder
import com.homedrop.common.util.DeviceUtil
import com.homedrop.common.util.image.loadImage
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener
import com.mrigankar.wallpaperapp.databinding.ItemImageBinding

class ImageViewBinder(
    private val listener: ImageAdapterListener
) : ViewDataBinder<ItemImageBinding, ImageViewData>() {

    override val viewType: Int
        get() = ITEM_IMAGE

    override fun createBinder(parent: ViewGroup): ItemImageBinding {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        binding.imageView.updateLayoutParams {
            width = DeviceUtil.getDeviceWidth() / 3
        }
        return binding

    }

    override fun bindData(binding: ItemImageBinding, data: ImageViewData, position: Int) {
        loadImage(binding.imageView, data.link)
        binding.root.setOnClickListener {
            listener.onImageClicked(data)
        }
    }
}