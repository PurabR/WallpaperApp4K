package com.mrigankar.wallpaperapp.downloads

import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.databinding.FragmentDownloadBinding

class DownloadFragment: BaseFragment<FragmentDownloadBinding, DownloadViewModel>() {
    override fun getViewBinding(): FragmentDownloadBinding {
        return FragmentDownloadBinding.inflate(layoutInflater)
    }
}