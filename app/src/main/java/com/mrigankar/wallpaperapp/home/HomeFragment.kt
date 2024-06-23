package com.mrigankar.wallpaperapp.home

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.ViewBinder.bestofmonth.bomViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.bestofmonth.bomViewData
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import com.mrigankar.wallpaperapp.adapter.HomeAdapter
import com.mrigankar.wallpaperapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

        binding.recyclerView.adapter = homeAdapter

        viewModel.getData()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.collector.collectLatest {
                homeAdapter.setItems(it)
            }
        }


    }



}