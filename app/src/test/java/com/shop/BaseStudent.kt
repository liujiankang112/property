package com.shop

/**
 * open 开放，可以被重写
 */
open class BaseStudent {

    protected var class_name = ""

    open fun printInfo(){
        System.out.println("info -- "+class_name)
    }

}