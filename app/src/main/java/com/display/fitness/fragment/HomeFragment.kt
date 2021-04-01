package com.display.fitness.fragment

import android.os.Bundle
import android.view.View
import com.display.fitness.R
import com.display.fitness.base.BaseFragment


/**
 * @author: yees
 * @date:   2021/2/10
 * @desc:
 */

open class HomeFragment : BaseFragment() {

    open class PageInfo {
        var page = 0
        fun nextPage() {
            page++
        }

        fun reset() {
            page = 0
        }

        val isFirstPage: Boolean
            get() = page == 0
    }

    private val pageInfo: PageInfo = PageInfo()

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun initView(savedInstanceState: Bundle?, view: View?) {
        if (view != null) {
//            mRecyclerView = view.findViewById(R.id.rv_list)
//            val mBannerView = view.findViewById<BannerViewPager<Int>>(R.id.banner_home)
//
//            mBannerView.setIndicatorSliderGap(BannerUtils.dp2px(6.0F))
//                    .setScrollDuration(800)
//                    .setLifecycleRegistry(lifecycle)
//                    .setIndicatorGravity(IndicatorGravity.CENTER)
//                    .setAdapter(ViewBindingSampleAdapter(getNormalWidth(R.dimen.dp_8))).create()
//            setupDashIndicator(mBannerView)
//            mRecyclerView?.isNestedScrollingEnabled = false
//            mRecyclerView?.setLayoutManager(GridLayoutManager(activity, 3))
//            initAdapter()


        }
    }

//    fun initAdapter() {
//        val navList: List<HomeMenuItem> = DataFactory.loadData()
//        val header2: TransformersLayout<HomeMenuItem> = TransformersLayout(context)
//        //使用options配置会覆盖xml的属性
//        //使用options配置会覆盖xml的属性
//        val options: TransformersOptions = TransformersOptions.Builder()
//                .lines(1)
//                .spanCount(3)
//                .pagingMode(true)
//                .scrollBarWidth(DisplayHelper.dp2px(context, 48))
//                .scrollBarHeight(DisplayHelper.dp2px(context, 4))
//                .scrollBarRadius(DisplayHelper.dp2px(context, 4) / 2f)
//                .scrollBarTopMargin(DisplayHelper.dp2px(context, 6))
//                .scrollBarTrackColor(Color.parseColor("#e5e5e5")) //                .scrollBarThumbColor(Color.parseColor("#658421"))
//                .build()
//        header2.apply(options)
//                .addOnTransformersItemClickListener { position -> Toast.makeText(context, "点击：" + header2.getDataList()[position].getText(), Toast.LENGTH_SHORT).show() }
//                .load(navList, object : TransformersHolderCreator<HomeMenuItem?> {
//                    override fun createHolder(itemView: View?): Holder<HomeMenuItem?>? {
//                        return itemView?.let { NavAdapterViewHolder(it) }
//                    }
//
//                    override fun getLayoutId(): Int {
//                        return R.layout.item_home
//                    }
//                })


//        mRecyclerView?.layoutManager = LinearLayoutManager(context)
//        val beans: MutableList<String> = java.util.ArrayList()
//        val adapter = HomeMenuAdapter(beans)
//        val padding: Int = DisplayHelper.dp2px(context, 8)
//        val normal = TextView(context)
//        normal.text = "常用功能"
//        normal.setTextColor(Color.BLACK)
//        normal.setPadding(padding * 2, padding, 0, padding)
//        adapter.addHeaderView(normal)
//        adapter.addHeaderView(header2)
//        mRecyclerView?.adapter = adapter
//    }

    //    //设置Banner样式
//    private fun setupDashIndicator(mBannerView: BannerViewPager<Int>) {
//        mBannerView.setIndicatorStyle(IndicatorStyle.DASH)
//                .setIndicatorHeight(getNormalWidth(R.dimen.dp_3))
//                .setIndicatorSlideMode(mSlideMode)
//                .setIndicatorSliderGap(getNormalWidth(R.dimen.dp_3))
//                .setIndicatorSliderWidth(getNormalWidth(R.dimen.dp_10), getNormalWidth(R.dimen.dp_10))
//                .setIndicatorSliderColor(getColor(R.color.gray_color), getColor(R.color.white))
//                .refreshData(getPicList(4));
//    }
//
    private fun getNormalWidth(id: Int): Int {
        return resources.getDimensionPixelOffset(id)
    }
}