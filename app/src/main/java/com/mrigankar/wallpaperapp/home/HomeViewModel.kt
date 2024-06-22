package com.mrigankar.wallpaperapp.home

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import com.homedrop.common.base.BaseViewModel
import com.mrigankar.wallpaperapp.ViewBinder.bestofmonth.bomViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor():BaseViewModel() {
    lateinit var db : FirebaseFirestore

    fun getProducts(){
        db = FirebaseFirestore.getInstance()

        db.collection("bestofmonth").addSnapshotListener { value, error ->
            val listBestofTheMonth = arrayListOf<bomViewData>()
            val data = value?.toObjects(bomViewData::class.java)
            listBestofTheMonth.addAll(data!!)

            for (i in listBestofTheMonth){
                Log.e("@@@@@@", "onCreateView")
            }
        }
    }

}