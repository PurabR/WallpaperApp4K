package com.mrigankar.wallpaperapp.di

import androidx.fragment.app.Fragment
import com.mrigankar.wallpaperapp.adapter.HomeAdapterListener
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener
import com.mrigankar.wallpaperapp.adapter.SpecificCategoriesAdapterListener
import com.mrigankar.wallpaperapp.categories.SpecificCategoriesFragment
import com.mrigankar.wallpaperapp.home.HomeFragment
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
    fun provideSpecificAdapterListener(fragment: Fragment): SpecificCategoriesAdapterListener {
        return when(fragment) {
            is SpecificCategoriesFragment -> fragment
            else -> throw IllegalArgumentException("")
        }
    }

}