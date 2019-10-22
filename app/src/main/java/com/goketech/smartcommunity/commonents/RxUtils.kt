package com.goketech.smartcommunity.commonents

import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxUtils {

    /**
     * rxjava 线程切换统一处理
     * @param <T>
     * @return
    </T> */
    fun <T> rxScheduler(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }


}
