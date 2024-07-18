package com.mrigankar.wallpaperapp.di

import androidx.fragment.app.Fragment
import com.mrigankar.wallpaperapp.adapter.HomeAdapterListener
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener
import com.mrigankar.wallpaperapp.adapter.SpecificCategoriesAdapterListener
import com.mrigankar.wallpaperapp.SpecificCategories.SpecificCategoriesFragment
import com.mrigankar.wallpaperapp.adapter.SetWallpaperAdapterListener
import com.mrigankar.wallpaperapp.downloads.DownloadFragment
import com.mrigankar.wallpaperapp.home.HomeFragment
import com.mrigankar.wallpaperapp.setwallpaper.SetWallpaperFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object AdapterModule {

    @Provides
    fun provideImageAdapterListener(fragment: Fragment): ImageAdapterListener {
        return when(fragment) {
            is HomeFragment -> fragment
            is SpecificCategoriesFragment -> fragment
            is DownloadFragment -> fragment
            else -> throw IllegalArgumentException("")
        }
    }

    @Provides
    fun provideHomeAdapterListener(fragment: Fragment): HomeAdapterListener {
        return when(fragment) {
            is HomeFragment -> fragment
            else -> throw IllegalArgumentException("")
        }
    }

    @Provides
    fun provideSetWallpaperListener(fragment: Fragment): SetWallpaperAdapterListener {
        return when(fragment) {
            is SetWallpaperFragment -> fragment
            else -> throw IllegalArgumentException("")
        }
    }

}