package com.mrigankar.wallpaperapp.downloads

import androidx.navigation.fragment.findNavController
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.databinding.FragmentDownloadBinding

class DownloadFragment: BaseFragment<FragmentDownloadBinding, DownloadViewModel>() {
    override fun getViewBinding(): FragmentDownloadBinding {
        return FragmentDownloadBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<DownloadViewModel>? {
        return DownloadViewModel::class.java
    }
    override fun setUpViews() {
        super.setUpViews()

        binding.ivBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}