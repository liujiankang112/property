package com.goketech.smartcommunity.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.goketech.smartcommunity.interfaces.IBaseView
import com.goketech.smartcommunity.interfaces.IPresenter

abstract class BaseActivity<V: IBaseView,P: IPresenter<V>>:AppCompatActivity(),
    IBaseView {

    protected abstract val layout:Int

    protected var presenter:P? = null

    protected abstract fun initView()
    protected abstract fun initData()
    protected abstract fun initPresenter():P

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(layout)
        initView()
        presenter = initPresenter()
        if(presenter != null){
            presenter!!.attachView(this as V)
        }
        initData()

    }

    override fun onError(err: String) {

    }

    override fun onDestroy() {
        super.onDestroy()
        if(presenter != null){
            presenter!!.detachView()
        }
    }
}