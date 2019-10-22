package com.shop

class Classes:IClass {
    override fun joinClass(student: BaseStudent) {
        student.printInfo()
        System.out.println("student加入班级")
    }
}