package com.hedgehog.shadow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hedgehog.shadowlibrary.BaseShadowBuilder.Companion.ShadowLibrary
import com.hedgehog.shadowlibrary.ShadowLib
import com.hedgehog.shadowlibrary.getAndrey

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ShadowLibrary.load(R.drawable.ic_default)
            .withShadowColor(R.color.black)
            .withShadowRadius(15)
            .withShadowScale(1.1f)
            .withShadowTransition(top = 5, start = 5)
            .into(findViewById(R.id.shadowLiba))
    }
}