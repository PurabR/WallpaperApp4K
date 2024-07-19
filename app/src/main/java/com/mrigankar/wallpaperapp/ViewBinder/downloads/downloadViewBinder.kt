package com.mrigankar.wallpaperapp.ViewBinder.downloads

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import com.homedrop.common.ITEM_DOWNLOAD
import com.homedrop.common.ITEM_IMAGE
import com.homedrop.common.base.ViewDataBinder
import com.homedrop.common.util.DeviceUtil
import com.homedrop.common.util.image.loadImage
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import com.mrigankar.wallpaperapp.adapter.DownloadAdapter
import com.mrigankar.wallpaperapp.adapter.DownloadAdapterListener
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener
import com.mrigankar.wallpaperapp.databinding.FragmentDownloadBinding
import com.mrigankar.wallpaperapp.databinding.ItemImageBinding
import javax.inject.Inject

class downloadViewBinder (
    private val listener: DownloadAdapterListener
) : ViewDataBinder<ItemImageBinding, downloadViewData>() {

    override val viewType: Int
        get() = ITEM_DOWNLOAD

    override fun createBinder(parent: ViewGroup): ItemImageBinding {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return binding

    }

    override fun bindData(binding: ItemImageBinding, data: downloadViewData, position: Int) {
        loadImage(binding.imageView, data.image)

        binding.root.setOnClickListener {
            listener.onImageClicked(data)
        }
    }
}