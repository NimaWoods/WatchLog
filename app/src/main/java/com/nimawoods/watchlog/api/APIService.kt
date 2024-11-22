package com.nimawoods.watchlog.api

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class APIService {

    companion object {
        private val client = OkHttpClient()

        @JvmStatic
        fun fetch(url: String, onSuccess: (String) -> Unit, onError: (Throwable) -> Unit) {
            val request = Request.Builder()
                .url(url)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onError(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    response.body?.string()?.let { body ->
                        if (response.isSuccessful) {
                            onSuccess(body)
                        } else {
                            onError(IOException("Unexpected code $response"))
                        }
                    } ?: onError(IOException("Empty response body"))
                }
            })
        }
    }

}