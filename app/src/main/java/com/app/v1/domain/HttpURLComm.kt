package com.app.v1.domain

import android.renderscript.Sampler
import android.util.Log
import android.webkit.CookieManager
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object HttpURLComm {
    // serverURL : JSON 요청을 받는 서버의 URL
    // postParams : POST 방식으로 전달될 입력 데이터
    // 반환 데이터 : 서버에서 전달된 JSON 데이터
    @JvmStatic
    @Throws(Exception::class)
    fun getJson(serverUrl: String?, postParams: String?): String {
        Thread.sleep(100)
        val url = URL(serverUrl)
        val httpClient = url.openConnection() as HttpURLConnection
        // 세션 쿠키 전달
        //val cookieString = CookieManager.getInstance().getCookie(Sampler.Value.IPADDRESS)
        val sb = StringBuilder()
        if (httpClient != null) { // 연결되었으면
            //add request header
            httpClient.requestMethod = "POST"
            httpClient.setRequestProperty("USER-AGENT", "Mozilla/5.0")
            httpClient.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5")
//0            if (cookieString != null) {
//                httpClient.setRequestProperty("Cookie", cookieString)
//                Log.e("PHP_getCookie", cookieString)
//            }
            httpClient.connectTimeout = 10000
            httpClient.readTimeout = 10000
            httpClient.useCaches = false
            httpClient.defaultUseCaches = false
            httpClient.doOutput = true // POST 로 데이터를 넘겨주겠다는 옵션
            httpClient.doInput = true

            // Send post request
            val wr = DataOutputStream(httpClient.outputStream)
            wr.writeBytes(postParams)
            wr.flush()
            wr.close()

            if (httpClient.responseCode == HttpURLConnection.HTTP_OK) {
                try {
                    val stream = BufferedInputStream(httpClient.inputStream)
                    val data: String = readStream(inputStream = stream)
                    return data
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    httpClient.disconnect()
                }
            } else {
                println("ERROR ${httpClient.responseCode}")
            }
        }
        return ""
    }

    fun readStream(inputStream: BufferedInputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { stringBuilder.append(it) }
        return stringBuilder.toString()
    }
}