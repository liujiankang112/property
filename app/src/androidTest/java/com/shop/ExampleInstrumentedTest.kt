package com.shop

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Base64

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.lang.Exception

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.shop", appContext.packageName)
    }

    @Test
    @Throws(Exception::class)
    fun testRsa(){
        //初始化密钥
        //生成密钥对
        val keyMap = MyRsa.initKey()
        //公钥
        val publicKey = MyRsa.getPublicKey(keyMap)

        //私钥
        val privateKey = MyRsa.getPrivateKey(keyMap)
        println("公钥：/n" + Base64.encodeToString(publicKey, Base64.DEFAULT))
        println("私钥：/n" + Base64.encodeToString(privateKey, Base64.DEFAULT))

        println("================密钥对构造完毕,甲方将公钥公布给乙方，开始进行加密数据的传输=============")
        var str = "RSA密码交换算法"
        println("/n===========甲方向乙方发送加密数据==============")
        println("原文:$str")
        //甲方进行数据的加密
        val code1 = MyRsa.encryptByPrivateKey(str.toByteArray(), privateKey)
        println("加密后的数据：" + Base64.encodeToString(code1, Base64.DEFAULT))
        println("===========乙方使用甲方提供的公钥对数据进行解密==============")
        //乙方进行数据的解密
        val decode1 = MyRsa.decryptByPublicKey(code1, publicKey)
        println("乙方解密后的数据：" + String(decode1) + "/n/n")

        println("===========反向进行操作，乙方向甲方发送数据==============/n/n")

        str = "乙方向甲方发送数据RSA算法"

        println("原文:$str")

        //乙方使用公钥对数据进行加密
        val code2 = MyRsa.encryptByPublicKey(str.toByteArray(), publicKey)
        println("===========乙方使用公钥对数据进行加密==============")
        println("加密后的数据：" + Base64.encodeToString(code2, Base64.DEFAULT))

        println("=============乙方将数据传送给甲方======================")
        println("===========甲方使用私钥对数据进行解密==============")

        //甲方使用私钥对数据进行解密
        val decode2 = MyRsa.decryptByPrivateKey(code2, privateKey)

        println("甲方解密后的数据：" + String(decode2))

    }
}
