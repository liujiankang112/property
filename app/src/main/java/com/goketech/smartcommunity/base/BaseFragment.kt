package com.goketech.smartcommunity.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.goketech.smartcommunity.interfaces.IBaseView
import com.goketech.smartcommunity.interfaces.IPresenter

abstract class BaseFragment<V: IBaseView,P: IPresenter<V>>:Fragment() {

    protected abstract val layout:Int

    protected var presenter:P? = null

    protected abstract fun initView(view:View)
    protected abstract fun initData()
    protected abstract fun initPresenter():P
    protected var mContext:Context? = null
    protected var mActivity:Activity? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {var view= inflater.inflate(layout,null)
        mContext = context
        mActivity = activity
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = initPresenter()
        if(presenter != null){
            presenter!!.attachView(this as V)
        }
        initView(view)
        initData()
    }

    override fun onResume() {
        super.onResume()
        if(presenter != null){
            presenter!!.attachView(this as V)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(presenter != null){
            presenter!!.detachView()
        }
    }


}