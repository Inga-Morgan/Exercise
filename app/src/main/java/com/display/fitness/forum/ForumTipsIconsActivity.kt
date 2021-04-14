package com.display.fitness.forum

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.display.fitness.R
import com.display.fitness.adapter.ListDialogAdapter
import com.display.fitness.bean.GroupIconsBean
import com.display.fitness.http.HttpCallback
import com.display.fitness.http.HttpClientCenter
import com.display.fitness.toolbar.CustomToolBar
import java.io.Serializable

/**
 * @author : ye's
 * @date :   2021/3/25
 * @desc :
 */
class ForumTipsIconsActivity : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forum_tips_icons)
        initView()
    }

    private fun initView() {
        val mToolbar = findViewById<CustomToolBar>(R.id.toolbar)
        mToolbar.setMyCenterTitle(R.string.circle_selected, true)
        mToolbar.setLeftIconOnClickListener { finish() }
        mRecyclerView = findViewById(R.id.dialog_list_rv)
        request()
    }

    private fun request() {
        HttpClientCenter.getCircleIcons(object : HttpCallback<GroupIconsBean>() {
            override fun onSuccess(t: GroupIconsBean?) {
                t?.data?.let { initRecyclerView(it) }
            }

            override fun onFail(e: java.lang.Exception?) {
            }

        })
    }

    fun initRecyclerView(list: MutableList<GroupIconsBean.CircleIcons>) {
        val dataAdapter = ListDialogAdapter(list, this@ForumTipsIconsActivity)

        mRecyclerView?.layoutManager = LinearLayoutManager(this@ForumTipsIconsActivity)
        mRecyclerView?.adapter = dataAdapter
        //在此处理点击事件即可，viewName可以区分是item还是内部控件
        dataAdapter.setOnItemClickListener { _, viewName, position ->
            when (viewName) {
                ListDialogAdapter.ViewName.ITEM -> {
                    val intent = Intent(this@ForumTipsIconsActivity, CircleInfoDetailsActivity::class.java)
                    intent.putExtra("CircleInfoDetailsActivity", list[position])
                    startActivity(intent)
                }

                ListDialogAdapter.ViewName.PRACTISE -> {
                    val intent = Intent()
                    //把需要返回的数据存放在intent
                    intent.putExtra("ForumTipsIconsActivity", CircleInfo(position + 1, list[position].getName()))
                    //设置返回数据
                    setResult(RESULT_OK, intent)
                    finish()
                }

            }
        }
    }
}

open class CircleInfo(val id: Int, val name: String) : Serializable