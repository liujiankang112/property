package com.goketech.smartcommunity.interfaces.home

import com.goketech.smartcommunity.interfaces.IBaseView
import com.goketech.smartcommunity.interfaces.IPresenter
import com.goketech.smartcommunity.model.bean.IndexBean

interface HomeConstact {

    interface View: IBaseView {
        fun getIndexReturn(result: IndexBean)
    }

    interface Presenter:
        IPresenter<View> {
        fun getIndex();
    }
}