package com.androidpoplib.jpgtopng

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.rxjava3.core.Single
import java.io.File

class MainModel {

    var readPath: String = File("/storage/emulated/0/download/bitmap.jpg").absolutePath
    var writePath: String = File("/storage/emulated/0/download/bitmap.png").absolutePath

    fun read(): Single<Bitmap>? = Single.fromCallable {
        return@fromCallable BitmapFactory.decodeFile(readPath)
    }

    fun write(fileContentAsArray: ByteArray) {
    File(writePath).writeBytes(fileContentAsArray)
}

}