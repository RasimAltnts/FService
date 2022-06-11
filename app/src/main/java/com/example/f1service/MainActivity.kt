package com.example.f1service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.f1service.databinding.ActivityMainBinding
import com.example.f1service.model.DF1DriverModels
import com.example.f1service.service.RestService
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private var mJSONObject: JSONObject? = null
    private var mRestService = RestService()
    private var gson = Gson()
    private lateinit var mBinding: ActivityMainBinding

    private var sessionCallback = object : RestService.IRequestCallback {
        override fun isSuccesfull(response: JsonObject?) {
            var driverModel = gson.fromJson(response,DF1DriverModels::class.java)
        }

        override fun isFailed(ErrorCode: Int) {
            println("ErrorCode:$ErrorCode")
        }

        override fun isError() {
            println("Request is Error")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRestService.sendRequest("drivers.json",sessionCallback)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
    }
}