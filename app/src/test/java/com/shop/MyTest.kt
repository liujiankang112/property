package com.shop

class MyTest {
    companion object {

        @Volatile
        private var instance: MyTest? = null

        var BASE_URL = "www.baidu.com"

        fun getInstance(): MyTest? {
            if (instance == null) {
                synchronized(MyTest::class.java) {
                    if (instance == null) instance = MyTest()
                }
            }
            return instance
        }
    }

}
