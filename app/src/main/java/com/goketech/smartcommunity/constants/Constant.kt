package com.goketech.smartcommunity.constants

import com.goketech.smartcommunity.apps.MyApp
import java.io.File

object Constant {

    val Base_url = "http://sc.minxj.com/api/"


    //网络缓存的地址
    val PATH_DATA = MyApp.myApp.cacheDir.absolutePath + File.separator + "data"

    val PATH_CACHE = "$PATH_DATA/property"

    //android码
    val ANDROID_CODE = "a01973b3-38a2-4260-8caa-00c4b53e6d86"

    //android密匙
    val ANDROID_SECRET = "m\$c2Bk3kb^J\$7MDE"

    var TOKEN = "token"  //用户token

}