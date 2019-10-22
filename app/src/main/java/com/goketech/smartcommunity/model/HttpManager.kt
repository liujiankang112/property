package com.goketech.smartcommunity.model

import android.util.Log
import com.goketech.smartcommunity.constants.Constant
import com.goketech.smartcommunity.model.api.MyApi
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

object HttpManager {

    /*//单列双锁
    @Volatile
    private var httpManager: HttpManager? = null
    val instance: HttpManager?
        get() {
            if (httpManager == null) {
                synchronized(HttpManager::class.java) {
                    if (httpManager == null) httpManager = HttpManager()
                }
            }
            return httpManager
        }*/


    private var cache:Cache? = null

    private var myApi: MyApi? = null


    private val okHttpClient:OkHttpClient
        get() {
            val file = File(Constant.PATH_CACHE)
            cache = Cache(file,(1024*1024*100).toLong())
            return OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(UserAgentIntercepter())
                .addInterceptor(LoggingInterceptor())
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .cookieJar(cookieJar)
                .connectTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()
        }

    private fun getRetrofit(url:String):Retrofit{
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Synchronized
    private fun <T> getServerApi(baseUrl:String,tCla:Class<T>):T{
        return getRetrofit(baseUrl).create(tCla)
    }

    @Synchronized
    fun getMyApi(): MyApi {
        if(myApi == null){
            synchronized(HttpManager::class.java){
                myApi =
                    getServerApi(
                        Constant.Base_url,
                        MyApi::class.java
                    )
            }
        }
        return myApi!!
    }

    internal class UserAgentIntercepter:Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request().newBuilder()
                .addHeader("device-id",Constant.ANDROID_CODE)
                .addHeader("token",Constant.TOKEN)
                .build()

            return chain.proceed(request)
        }

    }

    /**
     * 日志拦截器
     */
    internal class LoggingInterceptor:Interceptor{
        //添加异常处理
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            var t1 = System.nanoTime()
            Log.i("interceptor",String.format("Sending request %s on %s%n%s",request.url(),chain.connection(),request.headers()))
            var response = chain.proceed(request)
            var t2 = System.nanoTime()
            Log.i(
                "Received:",
                String.format(
                    "Received response for %s in %.1fms%n%s",
                    response.request().url(),
                    (t2 - t1) / 1e6,
                    response.headers()
                )
            )
            return response
        }
    }


    /**
     * cookie设置
     */
    private val cookieJar = object:CookieJar{
        private val cookieMap = HashMap<String,List<Cookie>>()
        override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
            val host:String = url.host()
            val cookieList = cookieMap[host]
            if(cookieList != null){
                cookieMap.remove(host)
            }
            cookieMap[host] = cookies
        }

        override fun loadForRequest(url: HttpUrl): List<Cookie> {
            val cookieList = cookieMap[url.host()]
            return cookieList ?: ArrayList()
        }
    }

}