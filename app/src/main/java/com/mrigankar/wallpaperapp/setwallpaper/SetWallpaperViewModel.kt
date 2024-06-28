package com.mrigankar.wallpaperapp.setwallpaper

import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.homedrop.common.base.BaseViewModel
import com.homedrop.common.base.BaseViewType
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import com.mrigankar.wallpaperapp.ViewBinder.setWallpaper.SetWallpaperViewData
import com.mrigankar.wallpaperapp.ViewBinder.specificCategories.SpecificCategoriesViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SetWallpaperViewModel @Inject constructor(

) : BaseViewModel() {

    private val channel= Channel<List<BaseViewType>>()
    val collector = channel.receiveAsFlow()


    fun getSetWallpaper(setExtra: SpecificCategoriesViewData){
        viewModelScope.launch(Dispatchers.IO){
            val db = FirebaseFirestore.getInstance()

            val listSetWallpaper = db.collection("categories").document(setExtra.id)
                .collection("wallpapers").get().await()

            val lsw = listSetWallpaper.toObjects(ImageViewData::class.java)

            channel.send(lsw)
        }
    }



//;.;.
}