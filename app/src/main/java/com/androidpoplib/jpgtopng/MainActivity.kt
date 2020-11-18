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

    private val REQUEST_READ_EXTERNAL_STORGE = 10001
    private val REQUEST_WRITE_EXTERNAL_STORGE = 10002

    private val READ_EXTERNAL_STORAGE_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE
    private val WRITE_EXTERNAL_STORAGE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE

    private val presenter by moxyPresenter {
        MainPresenter(MainModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isPermissionGranted(READ_EXTERNAL_STORAGE_PERMISSION)) {
            Toast.makeText(this, "Разрешения есть, можно работать", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission(READ_EXTERNAL_STORAGE_PERMISSION, REQUEST_READ_EXTERNAL_STORGE);
        }

        button.setOnClickListener {presenter.btnClick() }
    }

    private fun isPermissionGranted(permission: String): Boolean {
        val permissionCheck = ActivityCompat.checkSelfPermission(this, permission)
        return permissionCheck == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_READ_EXTERNAL_STORGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "Разрешения получены", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@MainActivity, "Разрешения не получены", Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions!!, grantResults)
        }
    }

    private fun requestPermission(permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
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