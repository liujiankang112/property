package com.goketech.smartcommunity.utils

import java.net.URLEncoder
import java.util.*

class MyUtils {

    companion object{
        val secret_key = "m\$c2Bk3kb^J$7MDE"

        fun getSign(map:Map<String,String>):String?{
            var result = ""
            try {
                val infoIds = ArrayList<Map.Entry<String, String>>(map.entries)
                // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
                Collections.sort<Map.Entry<String, String>>(infoIds) { o1, o2 -> o1.key.toString().compareTo(o2.key) }

                // 构造签名键值对的格式
                var sb = StringBuilder()
                for (item in infoIds) {
                    if (item.key != null || item.key !== "") {
                        val key = item.key
                        val `val` = URLEncoder.encode(item.value)
                        if (!(`val` === "" || `val` == null)) {
                            sb.append(key + "=" + `val` + "&")
                        }
                    }
                }
                sb = sb.deleteCharAt(sb.length - 1)
                //			sb.append(PropertyManager.getProperty("SIGNKEY"));
                result = sb.toString()

                //进行MD5加密
                result = MD5Utils.MD5Encode(result + secret_key, "utf8")!!.toUpperCase()
            } catch (e: Exception) {
                return null
            }

            return result
        }
    }

}