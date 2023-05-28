package com.app.v1.utils

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.app.v1.data.MainRelatedDto
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

@Suppress("DEPRECATION")
class URLConnectionAsyncTask(val url: String, val data: MutableLiveData<MainRelatedDto>) :
    AsyncTask<String, Void, String?>() {
    private val TAG: String = LogUtils.makeLogTag(this.javaClass)

    override fun doInBackground(vararg urls: String): String? {

        var result = ""
        try {
            val url = URL(url)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.setRequestProperty(
                "Accept",
                "application/json"
            ) // The format of response we want to get from the server
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.doInput = true
            httpURLConnection.doOutput = false

            // Check if the connection is successful
            val responseCode = httpURLConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = httpURLConnection.inputStream.bufferedReader()
                    .use { it.readText() }  // defaults to UTF-8
                response?.let { data ->
                    result = data
                }
            }
        } catch (e: Exception) {
            LogUtils.e(TAG, "[URLConnectionAsyncTask] Exception : " + e.message)
            e.printStackTrace()
        }
        return result
    }

    override fun onPostExecute(result: String?) {
        GlobalScope.launch(Dispatchers.IO) {
            result?.let {
                Gson().fromJson(result, MainRelatedDto::class.java)?.apply {
                    data.postValue(this)
                }
            }
        }
    }

}