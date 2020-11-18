package com.androidpoplib.jpgtopng

import android.graphics.Bitmap
import moxy.MvpPresenter
import java.io.ByteArrayOutputStream

class MainPresenter(val model: MainModel) : MvpPresenter<MainView>() {

    fun btnClick() {
        model.read()?.subscribe({ s ->
            viewState.setImage(s)
            convertJpgToPng(s)
        }, {
            viewState.makeToast("something went wrong")
        })
    }

    private fun convertJpgToPng(bitmap: Bitmap?) {
        if (bitmap != null) {
            model.write(bitmap.compress(Bitmap.CompressFormat.PNG))
            viewState.setText(".jpg converted to .png and saved")
        }
    }

    private fun Bitmap.compress(
        format: Bitmap.CompressFormat,
        quality: Int = 100
    ): ByteArray {
        val stream = ByteArrayOutputStream()
        this.compress(
            format,
            quality,
            stream
        )
        return stream.toByteArray()
    }
}