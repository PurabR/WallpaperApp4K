package com.mrigankar.wallpaperapp.Mood

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.homedrop.common.base.BaseViewModel
import com.homedrop.common.base.BaseViewType
import com.mrigankar.wallpaperapp.ViewBinder.ImageBinder.ImageViewData
import com.mrigankar.wallpaperapp.home.HomeFragmentDirections

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class MoodViewModel @Inject constructor() : BaseViewModel() {

    private val _moodLiveData = MutableLiveData<String>()
    val moodLiveData: LiveData<String> get() = _moodLiveData

    private val _navigateToHappyFragment = MutableLiveData<Boolean>()
    val navigateToHappyFragment: LiveData<Boolean> get() = _navigateToHappyFragment

    private val _navigateToSadFragment = MutableLiveData<Boolean>()
    val navigateToSadFragment: LiveData<Boolean> get() = _navigateToSadFragment

    private val faceDetector: FaceDetector by lazy {
        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_NONE)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .build()
        FaceDetection.getClient(options)
    }

    private val smileResults = mutableListOf<String>()
    private var isAnalyzing = false
    private var analysisComplete = false

    private val channel = Channel<List<BaseViewType>>(Channel.UNLIMITED)
    val collector: Flow<List<BaseViewType>> = channel.receiveAsFlow()

    @OptIn(ExperimentalGetImage::class)
    fun analyzeImage(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null && !analysisComplete) {
            val inputImage = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            faceDetector.process(inputImage)
                .addOnSuccessListener { faces ->
                    for (face in faces) {
                        val smileProb = face.smilingProbability ?: -1.0f
                        val mood = if (smileProb > 0.3) "Happy" else "Sad"
                        smileResults.add(mood)
                        break
                    }

                    if (!isAnalyzing) {
                        isAnalyzing = true
                        viewModelScope.launch(Dispatchers.Main) {
                            delay(3000)
                            val finalMood = smileResults.groupingBy { it }
                                .eachCount()
                                .maxByOrNull { it.value }
                                ?.key ?: "Unknown"

                            _moodLiveData.postValue(finalMood)
                            analysisComplete = true

                            if (finalMood == "Happy") {
                                _navigateToHappyFragment.postValue(true)
                            } else {
                                _navigateToSadFragment.postValue(true)
                            }
                        }
                    }
                }
                .addOnFailureListener {
                    Log.e("MoodViewModel", "Face detection failed", it)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        } else {
            imageProxy.close()
        }
    }

    fun clearNavigationFlag() {
        _navigateToHappyFragment.value = false
    }
}
