package com.mrigankar.wallpaperapp.SpecificCategories

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import com.mrigankar.wallpaperapp.ViewBinder.specificCategories.SpecificCategoriesViewData
import com.mrigankar.wallpaperapp.adapter.SpecificCategoriesAdapter
import com.mrigankar.wallpaperapp.adapter.SpecificCategoriesAdapterListener

import com.mrigankar.wallpaperapp.databinding.FragmentSpecificCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SpecificCategoriesFragment : BaseFragment<FragmentSpecificCategoriesBinding, SpecificCategoriesViewModel>(), SpecificCategoriesAdapterListener {

    @Inject
    lateinit var specificCategoriesAdapter: SpecificCategoriesAdapter

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


        val layoutManager = GridLayoutManager(requireContext(), 6)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (position) {
                    0 -> return 6
                    else -> return 3

                }
            }
        }
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = specificCategoriesAdapter

        viewModel.getSpecificCategoriesData(extras)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.collector.collectLatest {
                specificCategoriesAdapter.setItems(it)
            }
        }
    }


    override fun onSpecificCategoriesClicked(specificCategory: SpecificCategoriesViewData) {
        findNavController().navigate(
            SpecificCategoriesFragmentDirections.actionCategoriesFragmentToSetWallpaperFragment(specificCategory)
        )
    }
}

