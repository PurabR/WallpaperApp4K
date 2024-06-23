package com.mrigankar.wallpaperapp.home


import androidx.lifecycle.viewModelScope

import com.google.firebase.firestore.FirebaseFirestore

import com.homedrop.common.base.BaseViewModel
import com.homedrop.common.base.BaseViewType
import com.mrigankar.wallpaperapp.ViewBinder.bestofmonth.bomViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.bestofmonth.bomViewData
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

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            val db = FirebaseFirestore.getInstance()
            val listCategory = db.collection("bestofmonth").get().await()
            val lm = listCategory.toObjects(bomViewData::class.java)
            val listBom = db.collection("categories").get().await()
            val bm = listBom.toObjects(CategoriesViewData::class.java)
            channel.send(lm+bm)
        }
    }

}



