package com.goketech.smartcommunity.view.home.adapter

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.shop.R
import com.goketech.smartcommunity.base.BaseAdapter
import com.goketech.smartcommunity.constants.Constant
import com.goketech.smartcommunity.model.bean.HotGoods

class HotGoodAdapter(mDatas:MutableList<HotGoods>):
    BaseAdapter<HotGoods>(mDatas) {
    override val layoutId: Int
        get() = R.layout.layout_hotgood_item

    override fun bindData(holder: BaseViewHolder, position: Int, t: HotGoods) {
        val img:ImageView = holder.getView(R.id.img) as ImageView
        Glide.with(mContext).load(t.list_pic_url).into(img)
        val txt_name:TextView = holder.getView(R.id.txt_name) as TextView
        txt_name.setText(t.name)
        val txt_des:TextView = holder.getView(R.id.txt_des) as TextView
        txt_des.setText(t.goods_brief)
        val txt_price = holder.getView(R.id.txt_price) as TextView
        val price = Constant.price_new_mode.replace("$",t.retail_price)
        txt_price.setText(price)
    }
}