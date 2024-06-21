package com.mrigankar.wallpaperapp.home

import com.google.firebase.firestore.FirebaseFirestore
import com.homedrop.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor():BaseViewModel() {

    lateinit var db:FirebaseFirestore



}