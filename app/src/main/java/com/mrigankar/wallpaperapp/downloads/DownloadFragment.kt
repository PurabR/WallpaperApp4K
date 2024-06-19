package com.mrigankar.wallpaperapp.downloads

import androidx.fragment.app.Fragment
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.databinding.FragmentDownloadBinding

class DownloadFragment: BaseFragment<FragmentDownloadBinding, DownloadFragmentViewModel>() {
    override fun getViewBinding(): FragmentDownloadBinding {
        return FragmentDownloadBinding.inflate(layoutInflater)
    }
}