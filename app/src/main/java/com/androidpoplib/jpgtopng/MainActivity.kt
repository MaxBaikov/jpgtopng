package com.androidpoplib.jpgtopng

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class MainActivity : MvpAppCompatActivity(), MainView {

    private val PERMISSION_REQUEST_CODE = 10001

    private val PERMISSIONS: Array<String> = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private val presenter by moxyPresenter {
        MainPresenter(MainModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isPermissionsGranted(PERMISSIONS)) {
            Toast.makeText(this, "Разрешения есть, можно работать", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission(PERMISSIONS, PERMISSION_REQUEST_CODE);
        }

        button.setOnClickListener { presenter.btnClick() }
    }

    private fun isPermissionsGranted(permission: Array<String>): Boolean {
        var permissionCheck: Int = PackageManager.PERMISSION_GRANTED
        for (permission in PERMISSIONS) {
            permissionCheck += ActivityCompat.checkSelfPermission(this, permission)
        }
        return permissionCheck == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.size == 2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    this@MainActivity,
                    "Разрешение на чтение получено ",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    this@MainActivity,
                    "Разрешение на запись получено",
                    Toast.LENGTH_LONG
                )
                    .show()
            } else {
                Toast.makeText(this@MainActivity, "Разрешение/я не получены", Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun requestPermission(permission: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(this, permission, requestCode)
    }

    override fun setText(text: String) {
        textView.text = text
    }

    override fun setImage(bitmap: Bitmap) {
        imageView.setImageBitmap(bitmap)
    }

    override fun makeToast(text: String) {
        Toast.makeText(baseContext, text, Toast.LENGTH_SHORT).show()
    }


}