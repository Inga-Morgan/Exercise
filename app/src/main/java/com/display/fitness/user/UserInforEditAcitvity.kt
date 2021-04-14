package com.display.fitness.user

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.display.fitness.R
import com.display.fitness.base.BaseActivity
import com.display.fitness.toolbar.CustomToolBar

class UserInforEditAcitvity : BaseActivity(){

    companion object{
        val REQUEST_CODE: Int = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_toolbar)
        switchContent()
        initview()
    }

    fun switchContent() {
        //必需继承FragmentActivity,嵌套fragment只需要这行代码
        supportFragmentManager.beginTransaction().replace(R.id.content_layout, UserInforEditFragment()).commitAllowingStateLoss()
    }

    fun initview() {
        val mToolbar = findViewById<CustomToolBar>(R.id.toolbar)
        mToolbar.setMyCenterTitle(R.string.info_details,true)
        mToolbar.setNavigationOnClickListener{ this@UserInforEditAcitvity.finish() }
    }
}