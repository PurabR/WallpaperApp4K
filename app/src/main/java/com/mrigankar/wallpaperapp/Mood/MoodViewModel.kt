package com.mrigankar.wallpaperapp.Mood

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.homedrop.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoodViewModel @Inject constructor(): BaseViewModel() {

    private val _moodLiveData = MutableLiveData<String>()
    val moodLiveData: LiveData<String> get() = _moodLiveData

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
    private var analysisComplete = false  // NEW FLAG

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
                            delay(3000)  // wait for 3 seconds
                            val finalMood = smileResults.groupingBy { it }
                                .eachCount()
                                .maxByOrNull { it.value }
                                ?.key ?: "Unknown"
                            _moodLiveData.postValue(finalMood)
                            analysisComplete = true  // ANALYSIS DONE — LOCK FURTHER CHANGES
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
}
