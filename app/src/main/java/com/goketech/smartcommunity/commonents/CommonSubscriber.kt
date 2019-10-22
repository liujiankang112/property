package com.goketech.smartcommunity.commonents

import android.text.TextUtils
import com.goketech.smartcommunity.interfaces.IBaseView
import io.reactivex.subscribers.ResourceSubscriber

abstract class CommonSubscriber<T> : ResourceSubscriber<T> {

    private var mView: IBaseView? = null
    private var errorMsg: String? = null
    private var isShowErrorState = false

    protected constructor(view: IBaseView) {
        mView = view
    }

    protected constructor(view: IBaseView, emsg: String) {
        mView = view
        errorMsg = emsg
    }

    protected constructor(view: IBaseView, isError: Boolean) {
        mView = view
        isShowErrorState = isError
    }

    protected constructor(view: IBaseView, emsg: String, isError: Boolean) {
        mView = view
        errorMsg = emsg
        isShowErrorState = isError
    }

    override fun onError(t: Throwable) {
        //LoadingUtil.getInstance().hideLoading();
        if (errorMsg != null && TextUtils.isEmpty(errorMsg)) {
            if (mView != null) mView!!.onError(errorMsg!!)
        }
    }

    override fun onComplete() {
        //LoadingUtil.getInstance().hideLoading();
    }
}
