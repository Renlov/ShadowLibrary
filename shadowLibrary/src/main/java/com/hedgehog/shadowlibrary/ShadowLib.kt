package com.hedgehog.shadowlibrary

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import kotlin.properties.Delegates

class ShadowLib @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {


    private var shadowBuilder: ShadowBuilder = ShadowBuilder(null)

    private var image: Int
    private var shadowScale: Float by Delegates.notNull()
    private var shadowRadius: Int by Delegates.notNull()
    private var shadowColor: Int by Delegates.notNull()
    private var shadowPaddingTop: Int by Delegates.notNull()
    private var shadowPaddingBottom: Int by Delegates.notNull()
    private var shadowPaddingLeft: Int by Delegates.notNull()
    private var shadowPaddingRight: Int by Delegates.notNull()
    private var shadowTransitionTop: Int by Delegates.notNull()
    private var shadowTransitionBottom: Int by Delegates.notNull()
    private var shadowTransitionLeft: Int by Delegates.notNull()
    private var shadowTransitionRight: Int by Delegates.notNull()


    init {
        val typeArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ShadowLib)
        shadowScale = typeArray.getFloat(R.styleable.ShadowLib_shadowScale, 0f)
        image = typeArray.getResourceId(
            R.styleable.ShadowLib_shadowImage, R.drawable.ic_default
        )
        shadowRadius = typeArray.getInt(R.styleable.ShadowLib_shadowRadius, 1)
        shadowColor = typeArray.getResourceId(R.styleable.ShadowLib_shadowColor, R.color.shadow)
        shadowPaddingTop = typeArray.getInt(R.styleable.ShadowLib_shadowPaddingTop, 0)
        shadowPaddingBottom = typeArray.getInt(R.styleable.ShadowLib_shadowPaddingBottom, 0)
        shadowPaddingLeft = typeArray.getInt(R.styleable.ShadowLib_shadowPaddingLeft, 0)
        shadowPaddingRight = typeArray.getInt(R.styleable.ShadowLib_shadowPaddingRight, 0)
        shadowTransitionTop = typeArray.getInt(R.styleable.ShadowLib_shadowTransitionTop, 0)
        shadowTransitionBottom = typeArray.getInt(R.styleable.ShadowLib_shadowTransitionBottom, 0)
        shadowTransitionLeft = typeArray.getInt(R.styleable.ShadowLib_shadowTransitionLeft, 0)
        shadowTransitionRight = typeArray.getInt(R.styleable.ShadowLib_shadowTransitionRight, 0)
        typeArray.recycle()
    }

    fun setBuilder(shadowBuilder: ShadowBuilder) {
        this@ShadowLib.shadowBuilder = shadowBuilder
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val image = drawImage(width, height)
        val shadow = drawShadow(width, height)

        canvas.drawBitmap(
            shadow,
            shadowTransitionVertical(shadow.height),
            shadowTransitionHorizontal(shadow.width),
            paintShadow()
        )
        canvas.drawBitmap(
            image,
            (width / 2f) - image.width / 2,
            (height / 2f) - image.height / 2,
            null
        )
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //Минимальный размер компонента (ширина)
        val minWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        //Минимальный размер компонента (высота)
        val minHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        val desiredCellSizeInPixel = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, DEFAULT_SIZE,
            resources.displayMetrics
        ).toInt()

        val desiredWidth =
            minWidth.coerceAtLeast(desiredCellSizeInPixel + paddingLeft + paddingRight)
        val desiredHeight =
            minHeight.coerceAtLeast(desiredCellSizeInPixel + paddingBottom + paddingBottom)

        setMeasuredDimension(
            resolveSize(desiredWidth, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec)
        )
    }

    private fun drawImage(width: Int, height: Int): Bitmap {
        val drawable: Drawable =
            ResourcesCompat.getDrawable(resources, shadowBuilder.image ?: image, null)
                ?: error("error")
        return drawable.toBitmap(width, height)
    }

    private fun drawShadow(width: Int, height: Int): Bitmap {
        val drawable =
            ResourcesCompat.getDrawable(resources, shadowBuilder.image ?: image, null)
                ?: error("error")
        val widthBitmap = (width * (shadowBuilder.shadowScale ?: shadowScale)).toInt()
        val heightBitmap = (height * (shadowBuilder.shadowScale ?: shadowScale)).toInt()
        return setShadowParam(drawable.toBitmap(widthBitmap, heightBitmap))
    }

    private fun setShadowParam(bitmap: Bitmap): Bitmap {
        var drawBitmap = bitmap

        if (shadowBuilder.shadowPaddingTop != 0) {
            drawBitmap = addPaddingTopForBitmap(
                drawBitmap,
                shadowBuilder.shadowPaddingTop ?: shadowPaddingTop
            )
        }
        if (shadowBuilder.shadowPaddingBottom != 0) {
            drawBitmap = addPaddingBottomForBitmap(
                drawBitmap,
                shadowBuilder.shadowPaddingBottom ?: shadowPaddingBottom
            )
        }
        if (shadowBuilder.shadowPaddingLeft != 0) {
            drawBitmap = addPaddingLeftForBitmap(
                drawBitmap,
                shadowBuilder.shadowPaddingLeft ?: shadowPaddingLeft
            )
        }
        if (shadowBuilder.shadowPaddingRight != 0) {
            drawBitmap = addPaddingRightForBitmap(
                drawBitmap,
                shadowBuilder.shadowPaddingRight ?: shadowPaddingRight
            )
        }
        return drawBitmap//Toolkit.blur(drawBitmap, shadowBuilder.shadowRadius ?: shadowRadius)
    }

    private fun shadowTransitionVertical(drawBitmap: Int): Float {
        val heightShape = (height / 2f) - drawBitmap / 2
        return heightShape + (shadowBuilder.shadowTransitionTop
            ?: shadowTransitionTop) - (shadowBuilder.shadowTransitionBottom
            ?: shadowTransitionBottom)
    }

    private fun shadowTransitionHorizontal(drawBitmap: Int): Float {
        val widthShape = (width / 2f) - drawBitmap / 2
        return widthShape + (shadowBuilder.shadowTransitionLeft
            ?: shadowTransitionLeft) - (shadowBuilder.shadowTransitionRight
            ?: shadowTransitionRight)
    }

    private fun paintShadow(): Paint {
        val paint = Paint()
        val filter: ColorFilter = PorterDuffColorFilter(
            ContextCompat.getColor(context, shadowBuilder.shadowColor ?: shadowColor),
            PorterDuff.Mode.SRC_IN
        )
        paint.colorFilter = filter
        return paint
    }

    private fun addPaddingTopForBitmap(bitmap: Bitmap, paddingTop: Int): Bitmap {
        val outputBitmap =
            Bitmap.createBitmap(bitmap.width, bitmap.height + paddingTop, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(outputBitmap)
        canvas.drawBitmap(bitmap, 0f, paddingTop.toFloat(), null)
        return outputBitmap
    }

    private fun addPaddingBottomForBitmap(bitmap: Bitmap, paddingBottom: Int): Bitmap {
        val outputBitmap = Bitmap.createBitmap(
            bitmap.width,
            bitmap.height + paddingBottom,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(outputBitmap)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        return outputBitmap
    }

    private fun addPaddingRightForBitmap(bitmap: Bitmap, paddingRight: Int): Bitmap {
        val outputBitmap =
            Bitmap.createBitmap(bitmap.width + paddingRight, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(outputBitmap)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        return outputBitmap
    }

    private fun addPaddingLeftForBitmap(bitmap: Bitmap, paddingLeft: Int): Bitmap {
        val outputBitmap =
            Bitmap.createBitmap(bitmap.width + paddingLeft, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(outputBitmap)
        canvas.drawBitmap(bitmap, paddingLeft.toFloat(), 0f, null)
        return outputBitmap
    }

    companion object {
        const val DEFAULT_SIZE = 50f
    }
}