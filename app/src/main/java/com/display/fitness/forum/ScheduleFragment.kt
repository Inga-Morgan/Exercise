package com.display.fitness.forum

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.display.fitness.R
import com.display.fitness.adapter.TipsAdapter
import com.display.fitness.bean.TipsBean
import com.display.fitness.bean.TipsInfo
import com.display.fitness.http.HttpCallback
import com.display.fitness.http.HttpClientCenter
import com.display.fitness.toolbar.CustomToolBar

class ScheduleFragment : Fragment() {

    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    private var mRecyclerView: RecyclerView? = null
    private var mCurrentPage: Int = 1
    private val mPerPage: Int = 3
    private val REQUEST_CODE = 1
    private var infoList: List<TipsInfo> = ArrayList()
    private var mAdapter: TipsAdapter? = TipsAdapter(activity, infoList)
    private var workHandler = Handler(Looper.getMainLooper())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_story, container, false)
        val txName = view.findViewById<TextView>(R.id.tx_name)
        val circleSelected = view.findViewById<TextView>(R.id.circle_selected_tv)
        val mToolbar = view?.findViewById<CustomToolBar>(R.id.toolbar)
        mRecyclerView = view?.findViewById(R.id.forum_tips_list)
        mSwipeRefreshLayout = view?.findViewById(R.id.swipeLayout)
        mRecyclerView?.isNestedScrollingEnabled = false

        initHeader(txName, circleSelected)
        request()
        initView()
        initData()
        initToolbar(mToolbar)
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

    private fun initHeader(txName: TextView, selectIcon: TextView) {
        selectIcon.setOnClickListener {
            Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show()
            startActivity(Intent(activity, ForumTipsIconsActivity::class.java))
        }
    }

    private fun initView() {
        mSwipeRefreshLayout?.setColorSchemeResources(R.color.app_red, R.color.app_blue1, R.color.theme_yellow, R.color.app_green)
        mSwipeRefreshLayout?.setOnRefreshListener {
            request()
        }
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
            intent.putExtra("TipsInfo", infoList[position])
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
                    workHandler.sendMessage(message)
                    mAdapter?.notifyDataSetChanged()
                }
            }

            override fun onFail(e: Exception?) {
                mSwipeRefreshLayout?.isRefreshing = false
            }
        })
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CODE) {
//            val circleInfo = data?.getSerializableExtra("ForumTipsIconsActivity") as CircleInfo
//            addCircle?.text = circleInfo.name
//            index = circleInfo.id
//        }
//    }
}