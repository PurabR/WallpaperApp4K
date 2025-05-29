package com.mrigankar.wallpaperapp.Mood

import android.annotation.SuppressLint
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.R
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewBinder
import com.mrigankar.wallpaperapp.adapter.ImageAdapter
import com.mrigankar.wallpaperapp.databinding.FragmentMoodBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoodFragment : BaseFragment<FragmentMoodBinding, MoodViewModel>() {

    override fun getViewBinding(): FragmentMoodBinding {
        return FragmentMoodBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<MoodViewModel> = MoodViewModel::class.java

    @SuppressLint("SetTextI18n")
    override fun setUpViews() {
        super.setUpViews()

        // Setup Camera after view is created
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            bindCameraUseCase(cameraProvider)
        }, ContextCompat.getMainExecutor(requireContext()))

        // Observe mood changes from ViewModel
        viewModel.moodLiveData.observe(viewLifecycleOwner) { mood ->
            binding.moodTextView.text = "Detected mood: $mood"
        }

        binding.ivBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun bindCameraUseCase(cameraProvider: ProcessCameraProvider) {
        val preview = Preview.Builder().build().also {

        }

        val imageAnalyzer = ImageAnalysis.Builder()
            .build()
            .also {
                it.setAnalyzer(ContextCompat.getMainExecutor(requireContext())) { imageProxy ->
                    viewModel.analyzeImage(imageProxy)
                }
            }

        val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalyzer)
    }


}
