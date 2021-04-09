package com.display.fitness.user

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.display.fitness.R
import com.display.fitness.base.BaseActivity

class UserInforEditAcitvity : BaseActivity(), View.OnClickListener {

    private val REQUEST_CODE: Int = 1
    private val birthLayout : RelativeLayout ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initView()
        setTitle(R.string.UID)
    }

    override fun setTitle(title: CharSequence?) {

    }

    private fun initView() {
        val header = findViewById<ImageView>(R.id.edit_init)
        val birthLayout = findViewById<RelativeLayout>(R.id.user_details_birth_layout)
        header.setOnClickListener(this)
        birthLayout.setOnClickListener(this)
        supportActionBar?.setTitle(R.string.UID)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.edit_init -> startActivityForResult(Intent(this@UserInforEditAcitvity, UserNickNameEditAcitvity::class.java), REQUEST_CODE)
//            R.id.user_info_userLogo , R.id.edit_header ->

            R.id.user_details_birth_layout -> {

            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result: String? = data?.getExtras()?.getString("result")
    }
}