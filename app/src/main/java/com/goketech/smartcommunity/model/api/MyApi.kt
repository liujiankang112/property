package com.goketech.smartcommunity.model.api

import com.goketech.smartcommunity.model.bean.IndexBean
import com.goketech.smartcommunity.model.bean.LoginBean
import io.reactivex.Flowable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {

    @GET("index")
    fun getIndexData(): Flowable<IndexBean>

    @POST("login")
    @FormUrlEncoded
    fun login(@Body param:RequestBody):Flowable<LoginBean>





}