package com.mrigankar.wallpaperapp.home


import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.homedrop.common.base.BaseFragment
import com.homedrop.common.ktx.showShortToast
import com.homedrop.common.util.image.loadImage
import com.mrigankar.wallpaperapp.R
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData

import com.mrigankar.wallpaperapp.adapter.HomeAdapter

import com.mrigankar.wallpaperapp.adapter.HomeAdapterListener
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener
import com.mrigankar.wallpaperapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), ImageAdapterListener, HomeAdapterListener {


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


        val layoutManager = GridLayoutManager(requireContext(), 6)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (position) {
                    0, 1, 2 -> return 6
                    else -> return 3

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
      findNavController().navigate(
          HomeFragmentDirections.actionHomeFragmentToSetWallpaperFragment(image)
      )
    }

    override fun onCategoryClicked(category: CategoriesViewData) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToCategoriesFragment(category)
        )

    }


}