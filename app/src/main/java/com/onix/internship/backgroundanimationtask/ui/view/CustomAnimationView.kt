package com.onix.internship.backgroundanimationtask.ui.view

import android.animation.TimeAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.onix.internship.backgroundanimationtask.R
import com.onix.internship.backgroundanimationtask.ui.data.view.Star
import java.util.*
import kotlin.math.max
import kotlin.math.roundToInt

@SuppressLint("UseCompatLoadingForDrawables")
class CustomAnimationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val baseSpeedDpPeerSecond = 300
        private const val count = 32
        private const val speed = 1337

        private const val scaleMinPart = 0.45f
        private const val scaleRandomPart = 0.55f
        private const val alphaScalePart = 1f
        private const val alphaRandomPart = 0f
    }

    private val starsArray = Array(count) { Star() }
    private val random = Random(speed.toLong())
    private val timeAnimator: TimeAnimator = TimeAnimator()
    private val currentPlayTime: Float = 0f

    private lateinit var drawable: Drawable
    private var baseSpeed: Float
    private var baseSize: Float


    init {
        context.getDrawable(R.drawable.star)?.let { drawable = it }

        baseSpeed = baseSpeedDpPeerSecond * resources.displayMetrics.density
        baseSize = max(drawable.intrinsicWidth, drawable.intrinsicHeight) / 2f
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val viewHeight = height
        for ((x, y, scale, alpha) in starsArray) {
            val starSize = scale * baseSize
            if (y + starSize < 0 || y - starSize > viewHeight) {
                continue
            }

            val save = canvas.save()
            canvas.translate(x, y)

            val progress = (y + starSize) / viewHeight
            canvas.rotate(360 * progress)

            val size = starSize.roundToInt()
            drawable.setBounds(-size, -size, size, size)
            drawable.alpha = (255 * alpha).roundToInt()

            drawable.draw(canvas)
            canvas.restoreToCount(save)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        timeAnimator.setTimeListener { _: TimeAnimator?, _: Long, deltaTime: Long ->
            if (!isLaidOut) {
                return@setTimeListener
            }
            updateState(deltaTime.toFloat())
            invalidate()
        }
        timeAnimator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        timeAnimator.setTimeListener(null)
        timeAnimator.removeAllListeners()
        timeAnimator.cancel()
    }


    fun resume() {
        if (timeAnimator.isPaused) {
            timeAnimator.start()
            timeAnimator.currentPlayTime = currentPlayTime.toLong()
        }
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        for (i in starsArray.indices) {
            val star = Star()
            initializeStar(star, width, height)
            starsArray[i] = star
        }
    }


    private fun updateState(deltaMs: Float) {
        val deltaSeconds = deltaMs / 1000f
        val viewWidth = width
        val viewHeight = height

        for (star in starsArray) {
            star.y -= star.speed * deltaSeconds

            val size = star.scale * baseSize
            if (star.y + size < 0) {
                initializeStar(star, viewWidth, viewHeight)
            }
        }
    }

    private fun initializeStar(star: Star, viewWidth: Int, viewHeight: Int) {
        star.scale =
            scaleMinPart + scaleRandomPart * random.nextFloat()

        star.x = viewWidth * random.nextFloat()
        star.y = viewHeight.toFloat()
        star.y += star.scale * baseSize
        star.y += viewHeight * random.nextFloat() / 4f

        star.alpha =
            alphaScalePart * star.scale + alphaRandomPart * random.nextFloat()
        star.speed = baseSpeed * star.alpha * star.scale
    }

}