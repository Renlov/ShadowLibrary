package com.hedgehog.shadowlibrary

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.annotation.IntRange

class BaseShadowBuilder {

    fun load(@DrawableRes image: Int): ShadowBuilder {
        return ShadowBuilder(image)
    }

    companion object {
        val ShadowLibrary = BaseShadowBuilder()
    }
}

class ShadowBuilder(@DrawableRes internal val image: Int?) {

    internal var shadowRadius: Int? = null

    @ColorRes
    internal var shadowColor: Int? = null
    internal var shadowScale: Float? = null
    internal var shadowPaddingTop: Int? = null
    internal var shadowPaddingBottom: Int? = null
    internal var shadowPaddingLeft: Int? = null
    internal var shadowPaddingRight: Int? = null
    internal var shadowTransitionTop: Int? = null
    internal var shadowTransitionBottom: Int? = null
    internal var shadowTransitionLeft: Int? = null
    internal var shadowTransitionRight: Int? = null

    /**
     * 0..25
     */
    fun withShadowRadius(@IntRange(from = 1, to = 25) radius: Int): ShadowBuilder {
        shadowRadius = radius
        return this
    }

    fun withShadowColor(@ColorRes color: Int): ShadowBuilder {
        shadowColor = color
        return this
    }

    fun withShadowScale(@FloatRange(from = 0.1, to = 2.0) scale: Float): ShadowBuilder {
        shadowScale = scale
        return this
    }

    fun withShadowPadding(
        top: Int = 0,
        bottom: Int = 0,
        start: Int = 0,
        end: Int = 0
    ): ShadowBuilder {
        shadowPaddingTop = top
        shadowPaddingBottom = bottom
        shadowPaddingLeft = start
        shadowPaddingRight = end
        return this
    }

    fun withShadowTransition(
        top: Int = 0,
        bottom: Int = 0,
        start: Int = 0,
        end: Int = 0
    ): ShadowBuilder {
        shadowTransitionTop = top
        shadowTransitionBottom = bottom
        shadowTransitionLeft = start
        shadowTransitionRight = end
        return this
    }

//    fun into(view: ShadowLib) {
//        view.setBuilder(this)
//    }
}