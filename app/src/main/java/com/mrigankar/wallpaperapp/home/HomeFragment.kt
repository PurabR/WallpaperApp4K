package com.mrigankar.wallpaperapp.home


import androidx.lifecycle.lifecycleScope

import com.homedrop.common.base.BaseFragment

import com.mrigankar.wallpaperapp.adapter.BomAdapter
import com.mrigankar.wallpaperapp.adapter.CategoriesAdapter
import com.mrigankar.wallpaperapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {


    @Inject
    lateinit var bomAdapter: BomAdapter

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter


    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)

    }

    override fun getViewModelClass(): Class<HomeViewModel>? {
        return HomeViewModel::class.java
    }

    override fun setUpViews() {
        super.setUpViews()

        binding.rvCategories.adapter = categoriesAdapter

        viewModel.getCategoriesData()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.collector2.collectLatest {
                categoriesAdapter.setItems(it)
            }
        }

        binding.rvBom.adapter = bomAdapter

        viewModel.getBomData()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.collector.collectLatest {
                bomAdapter.setItems(it)
            }
        }


    }


}