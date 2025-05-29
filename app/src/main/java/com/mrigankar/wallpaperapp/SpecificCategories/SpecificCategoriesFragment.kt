package com.mrigankar.wallpaperapp.SpecificCategories

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import com.mrigankar.wallpaperapp.adapter.ImageAdapter
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener

import com.mrigankar.wallpaperapp.databinding.FragmentSpecificCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SpecificCategoriesFragment : BaseFragment<FragmentSpecificCategoriesBinding, SpecificCategoriesViewModel>(), ImageAdapterListener {

    private val imagesAdapter: ImageAdapter by lazy {
        ImageAdapter(ImageViewBinder(this))
    }

    lateinit var extras: CategoriesViewData

    override fun getViewBinding(): FragmentSpecificCategoriesBinding {
        return FragmentSpecificCategoriesBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<SpecificCategoriesViewModel>? {
        return SpecificCategoriesViewModel::class.java

    }

    override fun receiveExtras() {
        super.receiveExtras()
        extras = SpecificCategoriesFragmentArgs.fromBundle(requireArguments()).extras
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

        viewModel.getSpecificCategoriesData(extras)
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
            SpecificCategoriesFragmentDirections.actionCategoriesFragmentToSetWallpaperFragment(image)
        )
    }
}

