package com.display.fitness.forum

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ExpandableListView.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.display.fitness.R
import com.display.fitness.adapter.CommentExpandAdapter
import com.display.fitness.bean.CommentBean
import com.display.fitness.bean.CommentDetailBean
import com.display.fitness.bean.ReplyDetailBean
import com.display.fitness.bean.TipsInfo
import com.display.fitness.comment.CommentExpandableListView
import com.display.fitness.http.HttpCallback
import com.display.fitness.http.HttpClientCenter
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import de.hdodenhof.circleimageview.CircleImageView
import java.io.Serializable

class ForumCommentActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "ForumCommentActivity"
    private var toolbar: Toolbar? = null
    private var bt_comment: TextView? = null
    private var expandableListView: CommentExpandableListView? = null
    private var adapter: CommentExpandAdapter? = null
    private var commentBean: CommentBean? = null
    private var commentsList: MutableList<CommentDetailBean>? = null
    private var dialog: BottomSheetDialog? = null
    private var tipsInfo: TipsInfo? = null
    private var mTipsCommentInfo: TipsCommentInfo? = null
    private var mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0 -> {
                    val bundle = msg.data
                    mTipsCommentInfo = bundle?.getSerializable("TipsCommentInfo") as TipsCommentInfo
                    initExpandableListView(mTipsCommentInfo?.list)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forum_comment)
        tipsInfo = intent.getSerializableExtra("TipsInfo") as TipsInfo?
        Log.e("TAG", "$tipsInfo-------------")
        initView()
        getTipsCommentData(tipsInfo?.getId())
    }

    private fun initView() {
        toolbar = findViewById(R.id.toolbar)
        expandableListView = findViewById(R.id.detail_page_lv_comment)
        bt_comment = findViewById(R.id.detail_page_do_comment)
        val userName = findViewById<TextView>(R.id.detail_page_userName)
        val userImg = findViewById<CircleImageView>(R.id.detail_page_userLogo)
        val content = findViewById<TextView>(R.id.detail_page_story)
        Glide.with(this).load(tipsInfo?.getUserImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.header)
                .centerCrop()
                .into(userImg)
        userName.text = tipsInfo?.getUserName()
        content.text = tipsInfo?.getContent()
        bt_comment?.setOnClickListener(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
//        collapsingToolbar.title = "详情"
    }

    /**
     * 初始化评论和回复列表
     */
    private fun initExpandableListView(commentList: List<CommentDetailBean>?) {
        expandableListView?.setGroupIndicator(null)
        //默认展开所有回复
        adapter = CommentExpandAdapter(this, commentList)
        expandableListView?.setAdapter(adapter)
        for (i in commentList?.indices!!) {
            expandableListView?.expandGroup(i)
        }
        expandableListView?.setOnGroupClickListener(OnGroupClickListener { expandableListView, _, groupPosition, _ ->
            val isExpanded = expandableListView.isGroupExpanded(groupPosition)
            Log.e(TAG, "onGroupClick: 当前的评论id>>>" + commentList[groupPosition].id)
//            showReplyDialog(groupPosition)
            true
        })
//        expandableListView?.setOnChildClickListener(OnChildClickListener { _, _, _, _, _ ->
//            Toast.makeText(this@ForumCommentActivity, "点击了回复", Toast.LENGTH_SHORT).show()
//            false
//        })
        expandableListView?.setOnGroupExpandListener(OnGroupExpandListener {
        })
    }

    private fun getTipsCommentData(tipsId: String?) {
        Log.e("TAG", "init")
        HttpClientCenter.getTipsCommentList(tipsId, object : HttpCallback<CommentBean?>() {
            override fun onSuccess(t: CommentBean?) {
                val message = Message.obtain()
                message.what = 0
                val b = Bundle()
                b.putSerializable("TipsCommentInfo", t?.data?.list?.let { TipsCommentInfo(it) })
                message.data = b
                mHandler.sendMessage(message)
            }

            override fun onFail(e: Exception?) {

            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {

            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.detail_page_do_comment) {
            showCommentDialog()
        }
    }

    /**
     * by moos on 2018/04/20
     * func:弹出评论框
     */
    private fun showCommentDialog() {
        dialog = BottomSheetDialog(this)
        val commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout, null)
        val commentText = commentView.findViewById<EditText>(R.id.dialog_comment_et)
        val bt_comment = commentView.findViewById<Button>(R.id.dialog_comment_bt)
        dialog!!.setContentView(commentView)
        /**
         * 解决bsd显示不全的情况
         */
        val parent = commentView.parent as View
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(parent)
        commentView.measure(0, 0)
        behavior.peekHeight = commentView.measuredHeight
        bt_comment.setOnClickListener {
            val commentContent = commentText.text.toString().trim { it <= ' ' }
            if (!TextUtils.isEmpty(commentContent)) {

                postCommentContent(commentContent)
            } else {
                Toast.makeText(this@ForumCommentActivity, "评论内容不能为空", Toast.LENGTH_SHORT).show()
            }
        }
        commentText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (!TextUtils.isEmpty(charSequence) && charSequence.length > 2) {
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"))
                } else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"))
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        dialog?.show()
    }

    private fun postCommentContent(content : String ) {
        HttpClientCenter.postCommentContent(tipsInfo?.getId(),content,object :HttpCallback<CommentBean.ReplyCommentBean?>() {
            override fun onSuccess(t: CommentBean.ReplyCommentBean?) {
                dialog!!.dismiss()
                val detailBean = CommentDetailBean(t?.data?.getUserName(), content, "刚刚")
                adapter?.addTheCommentData(detailBean)

                Toast.makeText(this@ForumCommentActivity, "评论成功", Toast.LENGTH_SHORT).show()
            }

            override fun onFail(e: java.lang.Exception?) {
                TODO("Not yet implemented")
            }

        })
    }

    /**
     * by moos on 2018/04/20
     * func:弹出回复框
     */
    private fun showReplyDialog(position: Int) {
        dialog = BottomSheetDialog(this)
        val commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout, null)
        val commentText = commentView.findViewById<EditText>(R.id.dialog_comment_et)
        val bt_comment = commentView.findViewById<Button>(R.id.dialog_comment_bt)
        commentText.hint = "回复 " + commentsList!![position].getUserName().toString() + " 的评论:"
        dialog?.setContentView(commentView)
        bt_comment.setOnClickListener {

            val replyContent = commentText.text.toString().trim { it <= ' ' }
            if (!TextUtils.isEmpty(replyContent)) {
                dialog?.dismiss()
                val detailBean = ReplyDetailBean("小红", replyContent)
                adapter?.addTheReplyData(detailBean, position)
                expandableListView?.expandGroup(position)
                Toast.makeText(this@ForumCommentActivity, "回复成功", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@ForumCommentActivity, "回复内容不能为空", Toast.LENGTH_SHORT).show()
            }
        }
        commentText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (!TextUtils.isEmpty(charSequence) && charSequence.length > 2) {
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"))
                } else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"))
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        dialog?.show()
    }

}

open class TipsCommentInfo(val list: MutableList<CommentDetailBean>) : Serializable