package com.shop

/**
 * constructor student类的构造函数
 */
class Student constructor(var name:String,var age:Int) {

    private val TAG = this.javaClass.simpleName

    fun print(){
        System.out.println("name:"+name+" is age = "+age)
    }
}