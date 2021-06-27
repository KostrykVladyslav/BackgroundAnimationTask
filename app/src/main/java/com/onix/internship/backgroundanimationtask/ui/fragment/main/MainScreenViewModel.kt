package com.onix.internship.backgroundanimationtask.ui.fragment.main

import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainScreenViewModel() : ViewModel() {


    private var _translationOne = MutableLiveData<Float>()
    val translationOne : LiveData<Float> = _translationOne

    private var _translationTwo = MutableLiveData<Float>()
    val translationTwo : LiveData<Float> = _translationTwo

    fun startAnimation(width : Float) {
            val animator = ValueAnimator.ofFloat(0.0f, 1.0f).apply {
                repeatCount = ValueAnimator.INFINITE
                interpolator = LinearInterpolator()
                duration = 1000L
            }

            animator.addUpdateListener { animation ->
                val translationX = width * animation.animatedValue as Float
                _translationOne.postValue(translationX)
                _translationTwo.postValue(translationX - width)
            }
            animator.start()
    }
}