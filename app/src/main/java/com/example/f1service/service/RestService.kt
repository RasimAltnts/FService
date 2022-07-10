package com.example.f1service.service

import androidx.annotation.NonNull
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url


class RestService {

    interface ApiService {
        @Headers("Content-Type: application/json")
        @GET
        fun sendRequestOnApiService(@Url url:String):Call<JsonObject>
    }

    interface IRequestCallback {
        fun isSuccesfull(response:JsonObject?)

        fun isFailed(ErrorCode:Int){
            println("ErrorCode:$ErrorCode")
        }
        fun isError(){
            println("Request is Error")
        }
    }

    private val BASE_URL = "http://ergast.com/api/f1/"

    private val retrofit:Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ApiService::class.java)

    /**
     * This Function is Send Request URL...
     */
    fun sendRequest(@NonNull url:String,@NonNull callback: IRequestCallback) {
        val call = service.sendRequestOnApiService(url)
        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    response.body().let {
                        callback.isSuccesfull(it)
                    }
                }
                else {
                    println(response.message())
                    callback.isFailed(response.code())
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                println(t.localizedMessage)
                callback.isError()
            }
        })
    }
}