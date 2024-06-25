package com.mrigankar.wallpaperapp.categories

import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.homedrop.common.base.BaseViewModel
import com.homedrop.common.base.BaseViewType
import com.mrigankar.wallpaperapp.ViewBinder.TitileBinder.TitleData
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import com.mrigankar.wallpaperapp.ViewBinder.specificCategories.SpecificCategoriesViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SpecificCategoriesViewModel @Inject constructor(): BaseViewModel() {

    private val channel = Channel<List<BaseViewType>>()
    val collector = channel.receiveAsFlow()


    fun getSpecificCategoriesData(extras: CategoriesViewData) {

        viewModelScope.launch(Dispatchers.IO) {
            val db = FirebaseFirestore.getInstance()




            val listSpecificCategory =
                db.collection("categories").document(extras.id)
                    .collection("wallpapers").get().await()
            val lsc = listSpecificCategory.toObjects(SpecificCategoriesViewData::class.java)

            val listCategoryTitle = db.collection("categories").document(extras.id)
                .collection("wallpapers").get().await()
            val lct = listCategoryTitle.toObjects(TitleData::class.java)

            channel.send(lct+lsc)


        }





    }
}
