package com.goketech.smartcommunity.base

import com.goketech.smartcommunity.interfaces.IBaseView
import com.goketech.smartcommunity.interfaces.IPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

open class BasePresenter<V: IBaseView>:
    IPresenter<V> {

    protected var mView:V? = null
    private var weakReference:WeakReference<V>? = null
    protected var compositeDisposable:CompositeDisposable? = null

    override fun attachView(view: V) {
        weakReference = WeakReference(view)
        mView = weakReference!!.get()
    }

    protected fun addSubscribe(disposable:Disposable){
        if(compositeDisposable == null) compositeDisposable = CompositeDisposable()
        compositeDisposable!!.add(disposable)
    }

    protected fun unSubscribe(){
        if(compositeDisposable != null){
            compositeDisposable!!.clear()
        }
    }

    override fun detachView() {
        this.mView = null
    }
}