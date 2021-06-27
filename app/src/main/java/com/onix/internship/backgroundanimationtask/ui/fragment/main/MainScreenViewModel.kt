package com.onix.internship.backgroundanimationtask.ui.fragment.main

import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import androidx.lifecycle.ViewModel

class MainScreenViewModel() : ViewModel() {

    var model = MainModel()

    init {

        model.apply {
            val animator = ValueAnimator.ofFloat(0.0f, 1.0f).apply {
                repeatCount = ValueAnimator.INFINITE
                interpolator = LinearInterpolator()
                duration = 1000L
            }

            animator.addUpdateListener { animation ->
                val translationX = 1000F * animation.animatedValue as Float
                translationOne = translationX
                translationTwo = translationX - 1000F
            }
            animator.start()
        }
    }
}