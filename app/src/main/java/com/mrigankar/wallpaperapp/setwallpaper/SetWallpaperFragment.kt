package com.mrigankar.wallpaperapp.setwallpaper

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.homedrop.common.base.BaseFragment
import com.homedrop.common.ktx.showShortToast
import com.homedrop.common.util.coroutine.launchIO
import com.homedrop.common.util.image.loadImage
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import com.mrigankar.wallpaperapp.ViewBinder.setWallpaper.SetWallpaperViewData
import com.mrigankar.wallpaperapp.adapter.SetWallpaperAdapter
import com.mrigankar.wallpaperapp.adapter.SetWallpaperAdapterListener
import com.mrigankar.wallpaperapp.databinding.FragmentSetWallpaperBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
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

        binding.llSetWallpaper.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launchIO {
                Glide.with(requireContext()).asBitmap().load(extras.link)
                    .into(object : CustomTarget<Bitmap>() {

                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            Handler(Looper.getMainLooper()).post{
                                val wallpaperManger =
                                    requireContext().getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager
                                wallpaperManger.setBitmap(resource)


                                showShortToast(requireContext(), "Wallpaper Successfully set")
                            }

                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })
            }

        }

        binding.llDownload.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launchIO {
                Glide.with(requireContext()).asBitmap().load(extras.link)
                    .into(object : CustomTarget<Bitmap>() {

                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            try {
                                val filename = "${System.currentTimeMillis()}.jpg"
                                val dm = requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                                val downloaduri = Uri.parse(extras.link)
                                val request = DownloadManager.Request(downloaduri)
                                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                                    .setAllowedOverRoaming(false)
                                    .setTitle(filename)
                                    .setMimeType("image/jpeg") // Your file type. You can use this code to download other file types also.
                                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                    .setDestinationInExternalPublicDir(
                                        Environment.DIRECTORY_PICTURES,
                                        "4KWallpaper Downloads" + File.separator + filename
                                    )
                                dm.enqueue(request)
                                showShortToast(requireContext(), "Image downloading started")
                            }catch (e: Exception){
                                showShortToast(requireContext(), "Image downloading failed")
                            }
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })
            }

        }

        loadImage(binding.imageWallpaper, extras.link)

    }


    override fun onSetWallpaperClicked(setWallpaper: SetWallpaperViewData) {
        showShortToast(requireContext(), "Image clicked")
    }




}
