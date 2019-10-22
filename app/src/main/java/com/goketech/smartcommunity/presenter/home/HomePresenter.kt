package com.goketech.smartcommunity.presenter.home

import com.goketech.smartcommunity.base.BasePresenter
import com.goketech.smartcommunity.commonents.CommonSubscriber
import com.goketech.smartcommunity.commonents.RxUtils
import com.goketech.smartcommunity.interfaces.home.HomeConstact
import com.goketech.smartcommunity.model.HttpManager
import com.goketech.smartcommunity.model.bean.IndexBean

class HomePresenter: BasePresenter<HomeConstact.View>(),
    HomeConstact.Presenter {
    override fun getIndex() {
        addSubscribe(
            HttpManager.getMyApi().getIndexData()
            .compose(RxUtils.rxScheduler<IndexBean>())
            .subscribeWith(object: CommonSubscriber<IndexBean>(mView!!){
                override fun onNext(t: IndexBean?) {
                    mView!!.getIndexReturn(t!!)
                }

            }))

    }

}