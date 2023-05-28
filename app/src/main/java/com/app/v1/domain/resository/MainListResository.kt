package com.app.v1.domain.resository

import androidx.lifecycle.MutableLiveData
import com.app.v1.data.BaseResponse
import com.app.v1.data.MainRelatedDto
import com.app.v1.utils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.withContext
import com.google.gson.Gson
import kotlinx.coroutines.launch


object MainListResository {

    fun requestRelatedData (url: String): MutableLiveData<BaseResponse<MainRelatedDto>> {
        val livedata : MutableLiveData<BaseResponse<MainRelatedDto>> = MutableLiveData()

        var result = ""
        GlobalScope.launch(Dispatchers.IO) {
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

                withContext(Dispatchers.Main) {
                    response?.let { data ->
                        result = data
                        LogUtils.e("Test", "[requestURLConnection] result : " + result)
                        Gson().fromJson(result, MainRelatedDto::class.java)?.apply {
                            livedata.postValue(BaseResponse(this))
                        }
                    }
                }
            }
        }
        return livedata
    }
}