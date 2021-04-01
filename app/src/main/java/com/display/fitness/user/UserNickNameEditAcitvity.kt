package com.display.fitness.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.display.fitness.R
import com.display.fitness.base.BaseActivity

class UserNickNameEditAcitvity : BaseActivity(), View.OnClickListener {

    private var key : String ?= null
    private var content : String ?= null
    override fun initDatas() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_nick_name_edit_acitvity)

        initView()
    }

    fun initView() {

        val mToolbar_tv_title = findViewById<TextView>(R.id.toolbar_title)
        val mToolbar_tv_content = findViewById<TextView>(R.id.toolbar_tv_right)
        val editNick = findViewById<EditText>(R.id.user_nick_edit)

        mToolbar_tv_content.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
//            R.id.toolbar_tv_right -> sendInfo()
        }
    }


//    fun sendInfo() {
//        Task.callInBackground {
//            HttpClientCenter.editUserNickName(key,content, HttpCallback<>{
//
//            })
//        }
//    }

}