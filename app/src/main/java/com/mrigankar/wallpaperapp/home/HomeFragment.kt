package com.mrigankar.wallpaperapp.home


import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager

import com.homedrop.common.base.BaseFragment
import com.homedrop.common.ktx.showShortToast
import com.mrigankar.wallpaperapp.ViewBinder.ImageViewData
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData

import com.mrigankar.wallpaperapp.adapter.HomeAdapter
import com.mrigankar.wallpaperapp.adapter.CategoriesAdapter
import com.mrigankar.wallpaperapp.adapter.HomeAdapterListener
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener
import com.mrigankar.wallpaperapp.databinding.FragmentHomeNewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeNewBinding, HomeViewModel>(), ImageAdapterListener, HomeAdapterListener {


    @Inject
    lateinit var homeAdapter: HomeAdapter


    override fun getViewBinding(): FragmentHomeNewBinding {
        return FragmentHomeNewBinding.inflate(layoutInflater)

    }

    override fun getViewModelClass(): Class<HomeViewModel>? {
        return HomeViewModel::class.java
    }

    override fun setUpViews() {
        super.setUpViews()


        val layoutManager = GridLayoutManager(requireContext(), 6)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (position) {
                    0 -> return 6 // full width
                    else -> return 3 // span 2

                }
            }
        }
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = homeAdapter

        viewModel.getBomData()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.collector.collectLatest {
                homeAdapter.setItems(it)
            }
        }


    }

    override fun onImageClicked(image: ImageViewData) {
        showShortToast(requireContext(), "Image clicked")
    }

    override fun onCategoryClicked(category: CategoriesViewData) {
        showShortToast(requireContext(), "category clicked")

    }


}