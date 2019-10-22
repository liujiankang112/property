package com.goketech.smartcommunity.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

open abstract class BaseAdapter<T>: Adapter<BaseAdapter<T>.BaseViewHolder> {

    protected lateinit var onItemClickListener: OnItemClickListener;
    protected var mDatas:MutableList<T>? = null
    protected lateinit var mContext:Context
    protected abstract val layoutId:Int

    constructor(mDatas: MutableList<T>){
        this.mDatas = mDatas
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaseViewHolder {
        mContext = p0.context
        val view = LayoutInflater.from(mContext).inflate(layoutId,p0,false)
        val baseViewHolder = BaseViewHolder(view)
        view.setOnClickListener {
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(it,baseViewHolder.layoutPosition)
            }
        }
        return baseViewHolder
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder, i:Int){
        val t = mDatas!![i]
        bindData(viewHolder,i,t)
    }

    open protected abstract fun bindData(holder: BaseViewHolder, position: Int, t:T)

    override fun getItemCount(): Int {
        return if(mDatas == null) 0 else mDatas!!.size
    }


    public fun addOnItemClickListener(listener: OnItemClickListener){
        onItemClickListener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(v: View, position:Int)
    }

    inner class BaseViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        private val myViewSparseArray:SparseArray<View>

        init {
            myViewSparseArray = SparseArray()
        }

        fun getView(id:Int):View?{
            var view:View? = myViewSparseArray.get(id)
            if(view == null){
                view = itemView.findViewById(id)
                myViewSparseArray.put(id,view)
            }
            return  view
        }


    }

}