package com.craxinno.eventowl.data.network

import android.util.Log
import com.craxinno.eventowl.utils.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeNetworkRequest {
    suspend fun<T : Any> apiRequest(call : suspend () -> Response<T>) : T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(
                        JSONObject(it).getString("replyMsg")
                    )
                } catch (_: JSONException) {}
                message.append("\n")
            }
            message.append("Error Code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }
}