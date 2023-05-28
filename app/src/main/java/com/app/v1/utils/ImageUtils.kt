package com.app.v1.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import android.widget.ImageView
import java.net.URL

object ImageUtils {
    private val TAG: String = LogUtils.makeLogTag(this.javaClass)

    fun getUrlImage(url: String): Bitmap {
        val stream = URL(url).openStream()
        return BitmapFactory.decodeStream(stream)
    }

    fun getDownloadUrlImage(url: String, view: ImageView){
        LogUtils.e(TAG, "[getDownloadUrlImage] url : "+url)
        val imageURL = url
        var image: Bitmap? = null
        try {
            val stream = URL(imageURL).openStream()
            image = BitmapFactory.decodeStream(stream)
            image?.let {
                view.setImageBitmap(it)
            }
        }
        catch (e: Exception) {
            LogUtils.e(TAG, "[getDownloadUrlImage] Exception : "+e.message)
            e.printStackTrace()
        }
    }


}