package com.goketech.smartcommunity.interfaces

interface IPresenter<V: IBaseView> {

    fun attachView(view: V)

    fun detachView()

}