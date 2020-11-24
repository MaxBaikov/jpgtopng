package com.androidpoplib.jpgtopng

import moxy.MvpPresenter

class MainPresenter(val model: MainModel) : MvpPresenter<MainView>() {

    fun btnClick() {
        model.read()?.subscribe({ s ->
            viewState.setImage(s)
            model.convertJpgToPng(s)
            viewState.setText(".jpg converted to .png and saved")
        }, {
            viewState.makeToast("something went wrong")
        })
    }


}