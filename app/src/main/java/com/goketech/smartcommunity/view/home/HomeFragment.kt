package com.goketech.smartcommunity.view.home

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.bigkoo.convenientbanner.ConvenientBanner
import com.goketech.smartcommunity.base.BaseAdapter
import com.goketech.smartcommunity.base.BaseFragment
import com.goketech.smartcommunity.commonents.MyLinearDecoration
import com.goketech.smartcommunity.interfaces.home.HomeConstact
import com.goketech.smartcommunity.R
import com.goketech.smartcommunity.model.bean.*
import com.goketech.smartcommunity.presenter.home.HomePresenter
import com.goketech.smartcommunity.view.home.adapter.BrandAdapter
import com.goketech.smartcommunity.view.home.adapter.HotGoodAdapter
import com.goketech.smartcommunity.view.home.adapter.NewGoodAdapter
import com.goketech.smartcommunity.view.home.adapter.SubjectAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment:
    BaseFragment<HomeConstact.View, HomeConstact.Presenter>(),
    HomeConstact.View, BaseAdapter.OnItemClickListener {

    private var banner:ConvenientBanner<String>? = null
    private lateinit var brandList:MutableList<IndexBean.Brand>
    private lateinit var brandAdapter: BrandAdapter

    private lateinit var newGoodList:MutableList<IndexBean.NewGoods>
    private lateinit var newGoodAdapter: NewGoodAdapter

    private lateinit var hotGoodList:MutableList<IndexBean.HotGoods>
    private lateinit var hotGoodAdapter: HotGoodAdapter

    private lateinit var subjectList:MutableList<IndexBean.Topic>
    private lateinit var subjectAdapter: SubjectAdapter



    override fun getIndexReturn(result: IndexBean) {
        Log.i("HomeFragment",result.errmsg)
        var bannarData = ArrayList<String>()

        for (item in result.data.banner){
            bannarData.add(item.image_url)
        }

        banner!!.setPages({ BannerHolderView() },bannarData)
            .setPageIndicator(intArrayOf(R.drawable.ic_banner_unselect,R.drawable.ic_banner_select))
            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
            .startTurning(3000)

        initTab(result.data.channel)

        initBrand(result.data.brandList)

        initNewGood(result.data.newGoodsList)

        initHotGood(result.data.hotGoodsList)

        initSubject(result.data.topicList)
    }

    override fun onError(err: String) {
    }

    override val layout: Int
        get() = R.layout.fragment_home

    override fun initView(view:View) {
        banner = view.findViewById(R.id.banner)
        brandList = ArrayList()
        brandAdapter = BrandAdapter(brandList)
        brandAdapter.addOnItemClickListener(this)
        recy_brand.layoutManager = GridLayoutManager(context,2)
        recy_brand.adapter = brandAdapter

        newGoodList = ArrayList()
        newGoodAdapter = NewGoodAdapter(newGoodList)
        newGoodAdapter.addOnItemClickListener(this)
        recy_newgood.layoutManager = GridLayoutManager(context,2)
        recy_newgood.adapter = newGoodAdapter

        hotGoodList = ArrayList()
        hotGoodAdapter = HotGoodAdapter(hotGoodList)
        hotGoodAdapter.addOnItemClickListener(this)
        recy_hotgood.addItemDecoration(MyLinearDecoration())
        recy_hotgood.layoutManager = LinearLayoutManager(context)
        recy_hotgood.adapter = hotGoodAdapter

        subjectList = ArrayList()
        subjectAdapter = SubjectAdapter(subjectList)
        subjectAdapter.addOnItemClickListener(this)
        recy_subject.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
        recy_subject.adapter = subjectAdapter

    }

    override fun initData() {
        presenter!!.getIndex()
    }

    override fun initPresenter(): HomeConstact.Presenter {
        return HomePresenter()
    }

    /**
     * 初始化tab
     */
    fun initTab(channel:List<IndexBean.Channel>){
        for(item in channel){
            val tab = tabList.newTab();
            tab.text = item.name
            tab.tag = item
            tabList.addTab(tab)
        }
    }

    /**
     * 初始化Brand
     */
    fun initBrand(brand:List<IndexBean.Brand>){
        brandList.addAll(brand)
        brandAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(v: View, position: Int) {

    }

    /**
     * 初始化新品发布
     */
    fun initNewGood(goods:List<IndexBean.NewGoods>){
        newGoodList.addAll(goods)
        newGoodAdapter.notifyDataSetChanged()
    }

    /**
     * 人气推荐
     */
    fun initHotGood(goods:List<IndexBean.HotGoods>){
        hotGoodList.addAll(goods)
        hotGoodAdapter.notifyDataSetChanged()
    }

    /**
     * 专题精选
     */
    fun initSubject(topic:List<IndexBean.Topic>){
        subjectList.addAll(topic)
        subjectAdapter.notifyDataSetChanged()
    }
}