package com.mrigankar.wallpaperapp.Mood.Types

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.SpecificCategories.SpecificCategoriesFragmentArgs
import com.mrigankar.wallpaperapp.SpecificCategories.SpecificCategoriesFragmentDirections
import com.mrigankar.wallpaperapp.SpecificCategories.SpecificCategoriesFragmentDirections.Companion.actionCategoriesFragmentToSetWallpaperFragment
import com.mrigankar.wallpaperapp.SpecificCategories.SpecificCategoriesViewModel
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import com.mrigankar.wallpaperapp.adapter.ImageAdapter
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener
import com.mrigankar.wallpaperapp.databinding.FragmentHappySadBinding

import com.mrigankar.wallpaperapp.databinding.FragmentSpecificCategoriesBinding
import com.mrigankar.wallpaperapp.setwallpaper.SetWallpaperFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SadFragment : BaseFragment<FragmentHappySadBinding, SadViewModel>(), ImageAdapterListener {

    private val imagesAdapter: ImageAdapter by lazy {
        ImageAdapter(ImageViewBinder(this))
    }


    override fun getViewBinding(): FragmentHappySadBinding{
        return FragmentHappySadBinding.inflate(layoutInflater)
    }


    override fun getViewModelClass(): Class<SadViewModel>? {
        return SadViewModel::class.java

    }


    override fun setUpViews() {
        super.setUpViews()


        val layoutManager = GridLayoutManager(requireContext(), 2)
//        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//            override fun getSpanSize(position: Int): Int {
//                when (position) {
//                    1 -> return 6
//                    else -> return 3
//
//                }
//            }
//        }
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = imagesAdapter
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.getSadCategoriesData()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.collector.collectLatest {
                imagesAdapter.setItems(it)
            }
        }
    }

    override fun isHomeScreen(): Boolean {
        return false
    }

    override fun onImageClicked(image: ImageViewData) {
        findNavController().navigate(
            SadFragmentDirections.actionSadFragmentToSetWallpaperFragment(image)
        )
    }
}
