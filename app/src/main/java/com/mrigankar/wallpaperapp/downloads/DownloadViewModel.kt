package com.mrigankar.wallpaperapp.downloads

import android.os.Environment
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.homedrop.common.base.BaseViewModel
import com.homedrop.common.base.BaseViewType
import com.mrigankar.wallpaperapp.ViewBinder.bestofmonth.BomViewDataItems
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import com.mrigankar.wallpaperapp.ViewBinder.downloads.downloadViewData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.File
import java.nio.file.Files
import javax.inject.Inject

class DownloadViewModel @Inject constructor(): BaseViewModel() {

    private val channel = Channel<List<BaseViewType>>()
    val collector = channel.receiveAsFlow()

    fun getDownloads() {

        viewModelScope.launch(Dispatchers.IO) {

            val targetPath =
                Environment.getExternalStorageDirectory().absolutePath + "/Pictures/4KWallpaper Downloads"

            val targetFile = File(targetPath)

            channel.send(targetFile.listFiles().orEmpty().map {
                downloadViewData(it.absolutePath)
            })

        }


    }
}