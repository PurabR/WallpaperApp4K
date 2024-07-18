package com.mrigankar.wallpaperapp.downloads

import android.os.Environment
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import com.mrigankar.wallpaperapp.adapter.ImageAdapter
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener
import com.mrigankar.wallpaperapp.databinding.FragmentDownloadBinding
import java.io.File

class DownloadFragment: BaseFragment<FragmentDownloadBinding, DownloadViewModel>(), ImageAdapterListener {

    private val imagesAdapter: ImageAdapter by lazy {
        ImageAdapter(ImageViewBinder(this))
    }

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

        val allFiles: Array<File>
        val imageList = arrayListOf<String>()

        val targetPath = Environment.getExternalStorageDirectory().absolutePath+"/Pictures/4KWallpaper Downloads"

        val targetFile= File(targetPath)
        allFiles = targetFile.listFiles()!!

        for (data in allFiles){
            imageList.add(data.absolutePath)
        }

        for(i in imageList){
            Log.e("@@@@@", "onCreateView:"+i)
        }

        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.adapter = imagesAdapter




    }

    override fun onImageClicked(image: ImageViewData) {
        TODO("Not yet implemented")
    }

    override fun isHomeScreen(): Boolean {
        TODO("Not yet implemented")
    }
}