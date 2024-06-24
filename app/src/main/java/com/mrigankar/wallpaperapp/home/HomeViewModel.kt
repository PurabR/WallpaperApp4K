package com.mrigankar.wallpaperapp.home


import androidx.lifecycle.viewModelScope

import com.google.firebase.firestore.FirebaseFirestore

import com.homedrop.common.base.BaseViewModel
import com.homedrop.common.base.BaseViewType
import com.mrigankar.wallpaperapp.ViewBinder.ImageViewData
import com.mrigankar.wallpaperapp.ViewBinder.bestofmonth.BomViewDataItems
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : BaseViewModel() {

    private val channel = Channel<List<BaseViewType>>()
    val collector = channel.receiveAsFlow()


    fun getBomData() {
        viewModelScope.launch(Dispatchers.IO) {
            val db = FirebaseFirestore.getInstance()

            val listBom = db.collection("bestofmonth").get().await()
            val lb = listBom.toObjects(ImageViewData::class.java)

            val listCategory = db.collection("categories").get().await()
            val lc = listCategory.toObjects(CategoriesViewData::class.java)

            channel.send(listOf(BomViewDataItems(lb)) + lc)
        }
    }

}



