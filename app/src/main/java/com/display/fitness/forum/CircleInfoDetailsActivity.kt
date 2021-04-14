package com.display.fitness.forum

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.display.fitness.R
import com.display.fitness.adapter.TipsAdapter
import com.display.fitness.base.BaseActivity
import com.display.fitness.bean.*
import com.display.fitness.http.HttpCallback
import com.display.fitness.http.HttpClientCenter
import com.display.fitness.http.HttpClientCenter.checkForkInfo
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout

class CircleInfoDetailsActivity : BaseActivity(), View.OnClickListener {
    private var mCurrentPage: Int = 1
    private var circleInfo: GroupIconsBean.CircleIcons? = null
    private var toolbar: Toolbar? = null
    private var circleForkBtn: Button? = null
    private var isForked: Boolean = false
    private var mRecyclerView: RecyclerView? = null
    private var infoList: List<TipsInfo> = ArrayList()
    private var mAdapter: TipsAdapter? = TipsAdapter(this, infoList)
    private var workHandler = Handler(Looper.getMainLooper())
    private var mSwipeRefreshLayout: SwipeRefreshLayout ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_add)
        circleInfo = intent.getSerializableExtra("CircleInfoDetailsActivity") as GroupIconsBean.CircleIcons
        initTips()
        initView()
        initData()
    }

    private fun initView() {
        toolbar = findViewById(R.id.toolbar)
        toolbar?.setNavigationOnClickListener { finish() }
        val appBarLayout = findViewById<AppBarLayout>(R.id.circle_info_appbar_layout)
        val circleIcon = findViewById<ImageView>(R.id.circle_info_fork_icon)
        val circleTitle = findViewById<TextView>(R.id.circle_info_fork_title)
        val circleIntro = findViewById<TextView>(R.id.circle_info_fork_intro)

        mRecyclerView = findViewById(R.id.circle_details_tips)
        mSwipeRefreshLayout = findViewById(R.id.swipeLayout)
        circleForkBtn = findViewById(R.id.circle_info_fork_btn)
        Glide.with(this).load(circleInfo?.getIcon())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.header)
                .centerCrop()
                .into(circleIcon)
        circleTitle.text = circleInfo?.getName()
        circleIntro.text = circleInfo?.getNotice()
        setSupportActionBar(toolbar)
        circleForkBtn?.setOnClickListener {
            circleForkBtn?.setBackgroundColor(resources.getColor(R.color.gray))
            circleForkBtn?.text = "已关注"
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)

        appBarLayout.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.title = circleInfo?.getName()
                    isShow = true
                } else if (isShow) {
                    //careful there should a space between double quote otherwise it wont work
                    collapsingToolbar.title = " "
                    isShow = false
                }
            }
        })

        if (isForked) {
            circleForkBtn?.text = "已关注"
        }
        circleForkBtn?.setOnClickListener(this)
        mSwipeRefreshLayout?.setColorSchemeResources(R.color.app_red, R.color.app_blue1, R.color.theme_yellow, R.color.app_green)
        mSwipeRefreshLayout?.setOnRefreshListener {
            initTips()
        }
    }

    private fun initData() {
        checkIsForked()
        workHandler = object : Handler(Looper.getMainLooper()) {
            // 消息处理的操作
            override fun handleMessage(msg: Message) {
                //设置了两种消息处理操作,通过msg来进行识别
                when (msg.what) {
                    0 -> {
                        initRecyclerView()
                    }
                }
            }
        }
    }

    private fun checkIsForked() {
        checkForkInfo(object : HttpCallback<CircleForked?>() {
            override fun onSuccess(t: CircleForked?) {
                for (index in t?.data!!) {
                    if (index.id == circleInfo?.getId()) {
                        isForked = true
                        circleForkBtn?.post {
                            circleForkBtn?.text = "已关注"
                        }
                        break
                    }
                }
            }

            override fun onFail(e: Exception?) {

            }

        })
    }

    private fun request() {
        HttpClientCenter.addForkCircle(circleInfo?.id, object : HttpCallback<CommentBean.ReplyCommentBean?>() {
            override fun onSuccess(t: CommentBean.ReplyCommentBean?) {
                Log.e("TAG", t.toString())
                circleForkBtn?.text = "已关注"
                ToastUtils.showShort("已关注")
            }

            override fun onFail(e: Exception?) {
                ToastUtils.showShort("请勿重复关注")
            }

        })
    }

    private fun initTips() {
        circleInfo?.getId()?.let {
            HttpClientCenter.getTipsList(it, object : HttpCallback<TipsBean>() {
                override fun onSuccess(t: TipsBean?) {
                    mSwipeRefreshLayout?.isRefreshing = false
                    infoList = t?.data as List<TipsInfo>
                    LogUtils.e(infoList.size)
                    if (infoList.isNotEmpty()) {
                        mAdapter?.setData(infoList)
                        val message = Message()
                        message.what = 0
                        workHandler.sendMessage(message)
                        mAdapter?.notifyDataSetChanged()
                    }
                }

                override fun onFail(e: java.lang.Exception?) {
                    mSwipeRefreshLayout?.isRefreshing = false
                    ToastUtils.showShort("加载失败")
                }

            })
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.circle_info_fork_btn -> {
                request()
            }
        }
    }

    private fun initRecyclerView() {
        mAdapter?.setOnLoadMoreListener {
            mCurrentPage++
            request()
        }

        //评论列表的rv
        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        mRecyclerView?.adapter = mAdapter

        mAdapter?.setOnItemClickListener { _, position, _ ->
            val intent = Intent(this, ForumCommentActivity::class.java)
            intent.putExtra("TipsInfo", infoList[position])
            startActivity(intent)
        }

    }

    companion object {
        const val TIPS_DETAILS_FRAGMENT_TAG = "tips_details_fragment"
    }
}