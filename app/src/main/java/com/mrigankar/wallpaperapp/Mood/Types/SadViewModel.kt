package com.mrigankar.wallpaperapp.Mood.Types

import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.homedrop.common.base.BaseViewModel
import com.homedrop.common.base.BaseViewType
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SadViewModel @Inject constructor(): BaseViewModel() {

    private val channel = Channel<List<BaseViewType>>()
    val collector = channel.receiveAsFlow()


    fun getSadCategoriesData() {

        viewModelScope.launch(Dispatchers.IO) {
            val db = FirebaseFirestore.getInstance()


            val listSadCategory =
                db.collection("sad").get().await()
            val lsc = listSadCategory.toObjects(ImageViewData::class.java)

            channel.send(lsc)


        }





    }
}