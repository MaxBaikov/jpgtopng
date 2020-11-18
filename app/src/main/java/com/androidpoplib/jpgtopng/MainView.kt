package com.androidpoplib.jpgtopng

import android.content.Context
import android.graphics.Bitmap
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {

    fun setText(text: String)
    fun setImage(bitmap: Bitmap)
    fun makeToast(text: String)

}