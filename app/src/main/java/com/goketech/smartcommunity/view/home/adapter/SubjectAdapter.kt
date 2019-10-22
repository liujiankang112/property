package com.goketech.smartcommunity.view.home.adapter

import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.shop.R
import com.goketech.smartcommunity.base.BaseAdapter
import com.goketech.smartcommunity.constants.Constant
import com.goketech.smartcommunity.model.bean.Topic

class SubjectAdapter(mDatas:MutableList<Topic>):
    BaseAdapter<Topic>(mDatas) {
    override val layoutId: Int
        get() = R.layout.layout_subject_item

    override fun bindData(holder: BaseViewHolder, position: Int, t: Topic) {
        val img:ImageView = holder.getView(R.id.img) as ImageView
        Glide.with(mContext).load(t.item_pic_url).into(img)
        val txt_title:TextView = holder.getView(R.id.txt_title) as TextView
        val title = Constant.subject_title.replace("$",t.title).replace("price",t.price_info)
        txt_title.setText(Html.fromHtml(title))
        val txt_subject:TextView = holder.getView(R.id.txt_des) as TextView
        txt_subject.setText(t.subtitle)
    }
}