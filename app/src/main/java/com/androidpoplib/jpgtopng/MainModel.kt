package com.androidpoplib.jpgtopng

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.rxjava3.core.Single
import java.io.File

class MainModel {

    var path: String = File("/storage/emulated/0/download/bitmap.jpg").absolutePath


    fun single(): Single<Bitmap>? = Single.fromCallable {
        return@fromCallable BitmapFactory.decodeFile(path)
    }


    //    var fname="/SD-карта/DCIM/Camera/20190805_135345.jpg"
//    var bitmap1= BitmapFactory.decodeFile(fname)
//    imageView2.setImageBitmap(bitmap1)
}