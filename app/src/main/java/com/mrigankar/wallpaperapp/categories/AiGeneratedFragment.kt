package com.mrigankar.wallpaperapp.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.R
import com.mrigankar.wallpaperapp.databinding.FragmentAiGeneratedBinding
import com.mrigankar.wallpaperapp.home.HomeViewModel

class AiGeneratedFragment : BaseFragment<FragmentAiGeneratedBinding,AiGeneratedViewModel>() {
    override fun getViewBinding(): FragmentAiGeneratedBinding {
        return FragmentAiGeneratedBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<AiGeneratedViewModel>? {
        return AiGeneratedViewModel::class.java
    }
    override fun setUpViews() {
        super.setUpViews()
    }
}