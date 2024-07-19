package com.mrigankar.wallpaperapp.downloads

import android.os.Environment
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import com.mrigankar.wallpaperapp.ViewBinder.downloads.downloadViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.downloads.downloadViewData
import com.mrigankar.wallpaperapp.adapter.DownloadAdapter
import com.mrigankar.wallpaperapp.adapter.DownloadAdapterListener
import com.mrigankar.wallpaperapp.adapter.ImageAdapter
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener
import com.mrigankar.wallpaperapp.databinding.FragmentDownloadBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File

class DownloadFragment : BaseFragment<FragmentDownloadBinding, DownloadViewModel>(),
    DownloadAdapterListener {

    private val downloadAdapter: DownloadAdapter by lazy {
        DownloadAdapter(downloadViewBinder(this))
    }

    override fun getViewBinding(): FragmentDownloadBinding {
        return FragmentDownloadBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<DownloadViewModel>? {
        return DownloadViewModel::class.java
    }

    override fun setUpViews() {
        super.setUpViews()

        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.adapter = downloadAdapter


        binding.ivBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.getDownloads()
        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.collector.collectLatest {
                downloadAdapter.setItems(it)
            }
        }
    }


    override fun onImageClicked(image: downloadViewData) {

    }


}