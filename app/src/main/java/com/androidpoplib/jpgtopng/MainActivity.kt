package com.androidpoplib.jpgtopng

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val presenter by moxyPresenter {
        MainPresenter(MainModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{presenter.btnClick() }
    }

    override fun setText(text: String) {
        button.text = text
    }

    override fun setImage(bitmap: Bitmap) {
        imageView.setImageBitmap(bitmap)
    }

    override fun makeToast(text: String) {
        Toast.makeText(baseContext, text, Toast.LENGTH_SHORT).show()
    }

}