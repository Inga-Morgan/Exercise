package com.display.fitness.forum

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.display.fitness.R
import com.display.fitness.adapter.HomeMenuAdapter
import com.display.fitness.adapter.TipsAdapter
import com.display.fitness.bean.*
import com.display.fitness.http.HttpCallback
import com.display.fitness.http.HttpClientCenter
import com.display.fitness.toolbar.CustomToolBar
import com.display.fitness.utils.DisplayHelper
import com.example.baserecyclerhelper.options.TransformersOptions
import com.example.baserecyclerhelper.view.TransformersLayout
import com.example.baserecyclerhelper.viewholder.Holder
import com.example.baserecyclerhelper.viewholder.TransformersHolderCreator
import com.example.deamon.holder.NavAdapterViewHolder
import java.io.Serializable
import java.util.*

class ScheduleFragment : Fragment() {

    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    private var mRecyclerView: RecyclerView? = null
    private var mTipsCircleRecyclerView: RecyclerView? = null
    private var mCurrentPage: Int = 1
    private val mPerPage: Int = 3
    private var infoList: List<TipsInfo> = ArrayList()
    private var navList: List<HomeMenuItem> = DataFactory.loadData()
    private var mAdapter: TipsAdapter? = TipsAdapter(activity, infoList)
    private var workHandler: Handler? = null
    private var adapter: HomeMenuAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_story, container, false)
        mTipsCircleRecyclerView = view?.findViewById(R.id.menu_item_circle)
        val mToolbar = view?.findViewById<CustomToolBar>(R.id.toolbar)
        mRecyclerView = view?.findViewById(R.id.forum_tips_list)
        mSwipeRefreshLayout = view?.findViewById(R.id.swipeLayout)
        mRecyclerView?.isNestedScrollingEnabled = false
        mRecyclerView?.layoutManager = GridLayoutManager(activity, 3)
        initToolbar(mToolbar)
        request()
        initView()
        initData()
        return view
    }

    private fun initToolbar(mToolbar: CustomToolBar?) {
        mToolbar?.setMyCenterTitle(R.string.forum_title, true)
        mToolbar?.setMySettingIcon(R.mipmap.ic_up)
        mToolbar?.setLeftIconVisibility(View.GONE)
        mToolbar?.navigationIcon = null
        mToolbar?.setSettingIconOnClickListener {
            startActivity(Intent(activity, ForumTipsUpActivity::class.java))
        }
    }

    private fun initView() {
        mSwipeRefreshLayout?.setColorSchemeResources(R.color.app_red, R.color.app_blue1, R.color.theme_yellow, R.color.app_green)
        mSwipeRefreshLayout?.setOnRefreshListener {
            request()
        }
    }

    /**
     * 初始化加载更多
     */
    private fun initCircleAdapter() {
        val header2: TransformersLayout<HomeMenuItem> = TransformersLayout(activity)
        //使用options配置会覆盖xml的属性
        val options: TransformersOptions = TransformersOptions.Builder()
                .lines(1)
                .spanCount(5)
                .pagingMode(true)
                .scrollBarWidth(DisplayHelper.dp2px(activity, 48))
                .scrollBarHeight(DisplayHelper.dp2px(activity, 4))
                .scrollBarRadius(DisplayHelper.dp2px(activity, 4) / 2f)
                .scrollBarTopMargin(DisplayHelper.dp2px(activity, 6))
                .scrollBarTrackColor(Color.parseColor("#e5e5e5"))
                //.scrollBarThumbColor(Color.parseColor("#658421"))
                .build()

        header2.apply(options)
                .addOnTransformersItemClickListener { position ->
                    Toast.makeText(activity, "点击：" + header2.getDataList()[position].getText(), Toast.LENGTH_SHORT).show()
                }
                .load(navList, object : TransformersHolderCreator<HomeMenuItem?> {
                    override fun createHolder(itemView: View?): Holder<HomeMenuItem?>? {
                        return itemView?.let { NavAdapterViewHolder(it) }
                    }

                    override fun getLayoutId(): Int {
                        return R.layout.item_home
                    }
                })

        mTipsCircleRecyclerView?.layoutManager = LinearLayoutManager(activity)
        adapter = HomeMenuAdapter(null)
        adapter?.addHeaderView(header2)
        mTipsCircleRecyclerView?.adapter = adapter


    }


    private fun initData() {
        workHandler = object : Handler(Looper.getMainLooper()) {
            // 消息处理的操作
            override fun handleMessage(msg: Message) {
                //设置了两种消息处理操作,通过msg来进行识别
                when (msg.what) {
                    0 -> {
                        initRecyclerView()
                    }
                    1 -> {
                        initCircleAdapter()
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        //评论列表的rv
        mRecyclerView?.layoutManager = LinearLayoutManager(activity)
        mRecyclerView?.adapter = mAdapter
        mAdapter?.setOnLoadMoreListener {
            mCurrentPage++
            request()
        }

        if (infoList.size == mCurrentPage * mPerPage) {
            mAdapter?.setCanLoadMore(true)
        } else {
            mAdapter?.setCanLoadMore(false)
        }

        mAdapter?.setOnItemClickListener { _, position, _ ->
            val intent = Intent(activity, ForumCommentActivity::class.java)
            intent.putExtra("TipsInfo",infoList[position])
            startActivity(intent)
        }
    }

    private fun request() {
        HttpClientCenter.getTipsList(object : HttpCallback<TipsBean>() {
            override fun onSuccess(t: TipsBean?) {
                infoList = t?.data as List<TipsInfo>
                mSwipeRefreshLayout?.isRefreshing = false
                if (infoList.isNotEmpty()) {
                    mAdapter?.setData(infoList)
                    val message = Message()
                    message.what = 0
                    workHandler?.sendMessage(message)
                    mAdapter?.notifyDataSetChanged()
                }
            }

            override fun onFail(e: Exception?) {
                mSwipeRefreshLayout?.isRefreshing = false
            }

        })

        HttpClientCenter.getCircleIcons(object : HttpCallback<GroupIconsBean>() {
            override fun onSuccess(t: GroupIconsBean?) {
                mSwipeRefreshLayout?.isRefreshing = false
                if (infoList.isNotEmpty()) {
                    navList = DataFactory.loadOriginData(t?.data)
                    val message = Message()
                    message.what = 1
                    workHandler?.sendMessage(message)
                }
            }

            override fun onFail(e: java.lang.Exception?) {
                mSwipeRefreshLayout?.isRefreshing = false
            }

        })
    }
}

open class CommentDetailsInfo(val info: TipsInfo) : Serializable
