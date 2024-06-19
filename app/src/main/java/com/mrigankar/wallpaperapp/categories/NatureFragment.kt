package com.mrigankar.wallpaperapp.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.databinding.FragmentNatureBinding

class NatureFragment: BaseFragment<FragmentNatureBinding, NatureViewModel>() {
    override fun getViewBinding(): FragmentNatureBinding {
        return FragmentNatureBinding.inflate(layoutInflater)
    }

}