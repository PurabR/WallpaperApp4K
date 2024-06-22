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

        db = FirebaseFirestore.getInstance()
        db.collection("bestofmonth").addSnapshotListener { value, error ->
            val listBestofTheMonth= arrayListOf<bomViewData>()
            val data = value?.toObjects(bomViewData::class.java)
            listBestofTheMonth.addAll(data!!)

            for (i in listBestofTheMonth){
                Log.e("TEST", "OnCreateView"+i)
            }

        }
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