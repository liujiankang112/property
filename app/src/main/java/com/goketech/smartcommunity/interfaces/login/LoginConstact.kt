package com.goketech.smartcommunity.interfaces.login

import com.goketech.smartcommunity.interfaces.IBaseView
import com.goketech.smartcommunity.interfaces.IPresenter
import com.goketech.smartcommunity.model.bean.LoginBean
import okhttp3.RequestBody

interface LoginConstact {

    interface View:IBaseView{
        fun loginReturn(result:LoginBean)
    }

    interface Presenter:IPresenter<View>{
        fun login(param:RequestBody);
    }
}