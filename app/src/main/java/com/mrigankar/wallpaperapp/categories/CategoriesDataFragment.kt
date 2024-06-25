package com.mrigankar.wallpaperapp.categories

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.adapter.CatDataAdapter
import com.mrigankar.wallpaperapp.databinding.FragmentCategoriesDataBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class CategoriesDataFragment: BaseFragment<FragmentCategoriesDataBinding, CategoriesDataViewModel>(),  {

    @Inject
    lateinit var catDataAdapter: CatDataAdapter

    override fun getViewBinding(): FragmentCategoriesDataBinding {
        return FragmentCategoriesDataBinding.inflate(layoutInflater)

    }

    override fun getViewModelClass(): Class<CategoriesDataViewModel>? {
        return CategoriesDataViewModel::class.java

    }

    override fun setUpViews() {
        super.setUpViews()

        val layoutManager = GridLayoutManager(requireContext(), 2 )
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                when(position){
                    0,1 -> return 2
                    else -> return 1
                }
            }
        }

        binding.catRecyclerView.layoutManager = layoutManager
        binding.catRecyclerView.adapter = catDataAdapter

        viewModel.getCatData()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.collector.collectLatest {
                catDataAdapter.setItems(it)
            }
        }

    }


}