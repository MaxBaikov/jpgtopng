package com.androidpoplib.jpgtopng

import android.graphics.Bitmap
import moxy.MvpPresenter

class MainPresenter(val model: MainModel) : MvpPresenter<MainView>() {

    var permissionStatus: Boolean = false



    fun btnClick() {

        model.single()?.subscribe({ s ->
            viewState.setImage(s)
            convertJpgToPng(s)
        }, {
            viewState.makeToast("something went wrong")
        })


    }

    private fun convertJpgToPng(s: Bitmap?) {
//        //TODO конвертировать
//        //TODO идти в model и записать на диск
        viewState.setText(".jpg converted to .png and saved") //TODO можно ли этосделать через обращению к res/strings

    }
}