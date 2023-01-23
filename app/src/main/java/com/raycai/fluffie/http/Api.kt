package com.raycai.fluffie.http

import com.dam.bestexpensetracker.data.constant.Const
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Api {

    fun ApiClient(): ApiInterface {

        // change your base URL
        val retrofit = Retrofit.Builder()
            .baseUrl(Const.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Creating object for our interface
        var api: ApiInterface = retrofit.create(ApiInterface::class.java)
        return api
    }
}