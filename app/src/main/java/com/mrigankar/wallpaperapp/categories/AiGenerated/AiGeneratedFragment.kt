package com.mrigankar.wallpaperapp.categories.AiGenerated

import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.databinding.FragmentAiGeneratedBinding

class AiGeneratedFragment : BaseFragment<FragmentAiGeneratedBinding, AiGeneratedViewModel>() {
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