package com.onix.internship.backgroundanimationtask.ui.view

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import com.onix.internship.backgroundanimationtask.R

@SuppressLint("UseCompatLoadingForDrawables")
class CustomSelfAnimationView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributes, defStyleAttr) {

    private val imageOne by lazy {
        context.getDrawable(R.drawable.star)?.let { it }
    }

    private val imageTwo by lazy {
        context.getDrawable(R.drawable.star)?.let { it }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val animator = ValueAnimator.ofFloat(0.0f, 1.0f)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.duration = 1000L
        animator.addUpdateListener { animation ->
            val translationX = width * animation.animatedValue as Float
//            imageOne. = translationX
//            imageTwo.translationX = translationX - width
        }


        val save = canvas!!.save()
        canvas.translate(x, y)

        animator.start()
        imageOne!!.draw(canvas)

    }
}