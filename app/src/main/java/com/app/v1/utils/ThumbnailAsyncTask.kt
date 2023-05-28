package com.app.v1.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.net.URL


/**
 * // test 용
 * AsyncTask 이용한 url image download
 * desperate api 로 추후 대체예정 및 고도화 예정
 * */
@Suppress("DEPRECATION")
class ThumbnailAsyncTask (val imageView: ImageView, val url: String,) : AsyncTask<String, Void, Bitmap?>() {
    private val TAG: String = LogUtils.makeLogTag(this.javaClass)

    override fun doInBackground(vararg urls: String): Bitmap? {
        val imageURL = url
        var image: Bitmap? = null
        try {
            val stream = URL(imageURL).openStream()
            image = BitmapFactory.decodeStream(stream)
            image?.let {
                imageView.setImageBitmap(it)
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
        return image
    }

    override fun onPostExecute(result: Bitmap?) {
        imageView.setImageBitmap(result)
    }

}