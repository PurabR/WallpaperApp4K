package com.mrigankar.wallpaperapp.categories

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.homedrop.common.base.BaseViewModel
import com.homedrop.common.base.BaseViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class CategoriesDataViewModel @Inject constructor(

): BaseViewModel(){
    private val channel = Channel<List<BaseViewType>>()
    val collector = channel.receiveAsFlow()


    fun getCatData(){
        viewModelScope.launch(Dispatchers.IO) {
            val db = FirebaseFirestore.getInstance()
            val listCatData = db.collection("categories")
                .document("ZBkqDXJVdsijOzmzF6HX")
                .collection("wallpapers").get().await()
            val ctl = listCatData.toObjects(CategoriesDataViewData::class.java)
            channel.send(ctl)

        }
    }



}