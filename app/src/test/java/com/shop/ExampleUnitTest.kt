package com.shop

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

//kotlin 学习地址 https://www.runoob.com/kotlin/kotlin-class-object.html
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testWhen(){

        var a:Int
        //定义变量的时候不能不初始
        //创建不可修改一个混合数据的list集合
        val list:List<Any> = listOf(1,2,3,"a","b")
        val listData = listOf(1,2,3,"a","b")
        //int类型的list
        val intList:List<Int> = listOf(1,2,3,4)


        lateinit var list1:MutableList<Int>
        //创建student
        var students:MutableList<Student>? = null
        var studentsA = ArrayList<Student>()

        if(students == null) students = ArrayList<Student>()
        //填充student数据
        for(i in 0..10){
            System.out.println("for index："+i)
            var student = Student("s"+i,i+1)
            students!!.add(student)
            studentsA.add(student)
        }

        val data = setOf<Int>(1,2,3,4,5,10,11,12)
        val num = 11

        if(list == null){

        }
        //结合
        list1 = ArrayList<Int>()
        if(students != null){
            students = mutableListOf()
        }


        //判断数据是否在数组中
        when{
            13 in data -> System.out.println("当前输入值在data数组中")
            else -> {
                System.out.println("当前输入值不在data数组中")
            }
        }

        //for
        for(i in data.indices){
            System.out.println("i = "+i)
        }
        //for index下标  value值
        for((index,value) in data.withIndex()){
            System.out.println("index:"+index+" value:"+value)
        }

        //条件判断 类似java switch
        when(num){
            in 1..5 -> System.out.println("当前输入值在1到5之间")
            6,7 -> System.out.println("当前的输入值是6 or 7")
            !in 6..10 -> System.out.println("当前输入值不在6..10ss")
            is Int -> System.out.println("当前输入值是String 类型")
            else -> {
                System.out.print("当前输入的值不在1到5之间")
            }
        }

        var student = Student("jack",20)
        student.print()

    }

    @Test
    fun testBaseClass(){
        var student = VipStudent()
        student.printInfo()

        var classes = Classes()
        classes.joinClass(student)

        System.out.println(MyTest.BASE_URL)

    }
}
