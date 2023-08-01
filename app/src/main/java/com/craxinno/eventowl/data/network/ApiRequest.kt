package com.craxinno.eventowl.data.network

import com.craxinno.eventowl.data.network.responses.AgendaListResponse
import com.craxinno.eventowl.data.network.responses.AgendaResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiRequest {
    @FormUrlEncoded
    @POST("demo_agneda_list")
    suspend fun agendaList(
        @Field("eid") eid : Int,
        @Field("pid") pid : Int
    ) : Response<AgendaListResponse>

    @GET("demo_agenda_detail")
    suspend fun getAgenda(
    @Query("sid") sid : Int,
    @Query("eid") eid : Int,
    @Query("pid") pid : Int,
    @Query("aid") aid : Int
    ) : Response<AgendaResponse>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : ApiRequest {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://eventowl.net:3680/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRequest::class.java)
        }
    }
}