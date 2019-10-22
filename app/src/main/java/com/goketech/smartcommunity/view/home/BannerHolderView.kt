package com.goketech.smartcommunity.view.home

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bigkoo.convenientbanner.holder.Holder
import com.bumptech.glide.Glide

class BannerHolderView:Holder<String> {

    private lateinit var imageView:ImageView
    private lateinit var mContext:Context
    override fun createView(context: Context?): View {
        mContext = context!!
        imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        return imageView
    }

    override fun UpdateUI(context: Context?, position: Int, data: String?) {
        //imageView.setImageResource(data!!)
        Glide.with(mContext).load(data!!).into(imageView)
    }
}