package com.mrigankar.wallpaperapp.home

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.ViewBinder.bestofmonth.bomViewData
import com.mrigankar.wallpaperapp.adapter.HomeAdapter
import com.mrigankar.wallpaperapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    lateinit var db: FirebaseFirestore

   @Inject
   lateinit var homeAdapter: HomeAdapter


    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)

    }

    override fun getViewModelClass(): Class<HomeViewModel>? {
        return HomeViewModel::class.java
    }

    override fun setUpViews() {
        super.setUpViews()
        initViews()
        homeAdapter.setItems(
            listOf(
bomViewData("cdzVNRDy6QrDuX7ltv7i", "https://e0.pxfuel.com/wallpapers/901/254/desktop-wallpaper-nature-cell-phone-mobile-nature-android.jpg"

)

            )
        )

    }

    fun initViews(){
        val lm = LinearLayoutManager(requireContext())
        binding.recyclerView.apply {
            layoutManager = lm
            setHasFixedSize(true)
            adapter = homeAdapter
        }

    }

}