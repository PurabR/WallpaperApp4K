package com.mrigankar.wallpaperapp.Mood

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.Surface.ROTATION_0
import android.view.Surface.ROTATION_270
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.homedrop.common.base.BaseFragment
import com.mrigankar.wallpaperapp.SpecificCategories.SpecificCategoriesFragmentArgs
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewBinder
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import com.mrigankar.wallpaperapp.ViewBinder.categories.CategoriesViewData
import com.mrigankar.wallpaperapp.adapter.ImageAdapter
import com.mrigankar.wallpaperapp.adapter.ImageAdapterListener
import com.mrigankar.wallpaperapp.databinding.FragmentMoodBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@AndroidEntryPoint
class MoodFragment : BaseFragment<FragmentMoodBinding, MoodViewModel>(), ImageAdapterListener {

    override fun getViewBinding(): FragmentMoodBinding {
        return FragmentMoodBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<MoodViewModel> = MoodViewModel::class.java

    private val cameraExecutor = Executors.newSingleThreadExecutor()

    @SuppressLint("SetTextI18n")
    override fun setUpViews() {
        super.setUpViews()

        // Setup camera
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            bindCameraUseCase(cameraProvider)
        }, ContextCompat.getMainExecutor(requireContext()))


        viewModel.moodLiveData.observe(viewLifecycleOwner) { mood ->
            binding.moodTextView.text = "Detected mood: $mood"
        }


        viewModel.navigateToHappyFragment.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate == true) {

                val action = MoodFragmentDirections.actionMoodFragmentToHappyFragment()
                findNavController().navigate(action)
                viewModel.clearNavigationFlag()
            }
        }

        viewModel.navigateToSadFragment.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate == true) {
                val action = MoodFragmentDirections.actionMoodFragmentToSadFragment()
                findNavController().navigate(action)
                viewModel.clearNavigationFlag()
            }
        }

        binding.ivBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun bindCameraUseCase(cameraProvider: ProcessCameraProvider) {
        val preview = Preview.Builder().build().also {
            it.setSurfaceProvider(binding.previewView.surfaceProvider)
        }

        val imageAnalyzer = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
            .also {
                it.setAnalyzer(cameraExecutor) { imageProxy ->
                    viewModel.analyzeImage(imageProxy)
                }
            }

        val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalyzer)
        } catch (e: Exception) {
            Log.e("MoodFragment", "Camera binding failed", e)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraExecutor.shutdown()
    }

    override fun onImageClicked(image: ImageViewData) {

    }

    override fun isHomeScreen(): Boolean {
        return false
    }
}
