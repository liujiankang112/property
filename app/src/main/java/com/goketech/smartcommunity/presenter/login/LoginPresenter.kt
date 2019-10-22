package com.goketech.smartcommunity.presenter.login

import com.goketech.smartcommunity.base.BasePresenter
import com.goketech.smartcommunity.commonents.CommonSubscriber
import com.goketech.smartcommunity.commonents.RxUtils
import com.goketech.smartcommunity.constants.Constant
import com.goketech.smartcommunity.interfaces.login.LoginConstact
import com.goketech.smartcommunity.model.HttpManager
import com.goketech.smartcommunity.model.bean.IndexBean
import com.goketech.smartcommunity.model.bean.LoginBean
import okhttp3.RequestBody

class LoginPresenter: BasePresenter<LoginConstact.View>(),LoginConstact.Presenter {

    override fun login(param: RequestBody) {

        addSubscribe(
            HttpManager.getMyApi().login(param)
                .compose(RxUtils.rxScheduler<LoginBean>())
                .subscribeWith(object: CommonSubscriber<LoginBean>(mView!!){
                    override fun onNext(t: LoginBean?) {
                        mView!!.loginReturn(t!!)
                    }

                }))
    }
}