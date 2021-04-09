package com.display.fitness.user

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import com.display.fitness.R
import com.display.fitness.base.BaseActivity
import com.display.fitness.toolbar.CustomToolBar

class UserNickNameEditAcitvity : BaseActivity() {

    private val RESULT_OK: Int = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_nick_name_edit_acitvity)
        initView()
    }

    fun initView() {
        val mNickyEdit = findViewById<EditText>(R.id.user_nick_edit)
        val mToolBar = findViewById<CustomToolBar>(R.id.toolbar)
        mToolBar.setNavigationOnClickListener { this@UserNickNameEditAcitvity.finish() }
        mToolBar.setMyCenterTitle(R.string.nick_name_edit, true)
        mToolBar.setMySettingText(R.string.save)
        mToolBar.setMySettingTextColor(resources.getColor(R.color.gray_color))
        mToolBar.setNavigationOnClickListener { this@UserNickNameEditAcitvity.finish() }

        mToolBar.setSettingTextOnClickListener {
            if (TextUtils.isEmpty(mNickyEdit.text.toString())) {
                showNormalDialog()
            } else {
            }
        }
    }


    open fun showNormalDialog() {
        val normalDialog = AlertDialog.Builder(this)
        normalDialog.setTitle("提示")
        normalDialog.setMessage("没有输入昵称，请重新输入")
        normalDialog.setPositiveButton("确定") { dialog, which ->
            dialog.dismiss()
        }
        // 显示
        normalDialog.show()
    }
}