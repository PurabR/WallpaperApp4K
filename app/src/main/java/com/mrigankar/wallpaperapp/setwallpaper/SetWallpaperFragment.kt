package com.mrigankar.wallpaperapp.setwallpaper

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.homedrop.common.base.BaseFragment
import com.homedrop.common.ktx.showShortToast
import com.homedrop.common.util.coroutine.launchIO
import com.homedrop.common.util.image.loadImage
import com.mrigankar.wallpaperapp.SpecificCategories.SpecificCategoriesFragmentArgs
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import com.mrigankar.wallpaperapp.ViewBinder.setWallpaper.SetWallpaperViewData
import com.mrigankar.wallpaperapp.ViewBinder.specificCategories.SpecificCategoriesViewData
import com.mrigankar.wallpaperapp.adapter.ImageAdapter
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener
import com.mrigankar.wallpaperapp.adapter.SetWallpaperAdapter
import com.mrigankar.wallpaperapp.adapter.SetWallpaperAdapterListener
import com.mrigankar.wallpaperapp.adapter.SpecificCategoriesAdapterListener
import com.mrigankar.wallpaperapp.databinding.FragmentSetWallpaperBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SetWallpaperFragment : BaseFragment<FragmentSetWallpaperBinding, SetWallpaperViewModel>(),
    SetWallpaperAdapterListener {

    @Inject
    lateinit var setWallpaperAdapter: SetWallpaperAdapter
    lateinit var extras: ImageViewData


    override fun getViewBinding(): FragmentSetWallpaperBinding {
        return FragmentSetWallpaperBinding.inflate(layoutInflater)

    }

    override fun getViewModelClass(): Class<SetWallpaperViewModel>? {
        return SetWallpaperViewModel::class.java

    }

    override fun receiveExtras() {
        super.receiveExtras()
        extras = SetWallpaperFragmentArgs.fromBundle(requireArguments()).extras
    }

    override fun setUpViews() {
        super.setUpViews()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }



        binding.setWallpaper.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launchIO {
                Glide.with(requireContext()).asBitmap().load(extras.link)
                    .into(object : CustomTarget<Bitmap>() {

                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            val wallpaperManger =
                                requireContext().getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager
                            wallpaperManger.setBitmap(resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })
            }


        }




        loadImage(binding.imageWallpaper, extras.link)


//        val layoutManager = GridLayoutManager(requireContext(), 6)
//        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//            override fun getSpanSize(position: Int): Int {
//                when (position) {
//                    0 -> return 6
//                    else -> return 3
//
//                }
//            }
//        }
//        binding.recyclerView.layoutManager = layoutManager
//        binding.recyclerView.adapter = setWallpaperAdapter
//
//        viewModel.getSetWallpaper(extras)
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.collector.collectLatest {
//                setWallpaperAdapter.setItems(it)
//            }
//        }
//

    }

    override fun onSetWallpaperClicked(setWallpaper: SetWallpaperViewData) {
        showShortToast(requireContext(), "Image clicked")
    }


}

//