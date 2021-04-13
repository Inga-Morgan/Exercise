package com.display.fitness.forum

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.display.fitness.R
import com.display.fitness.bean.TipsBean
import com.display.fitness.dialog.AlertDialog
import com.display.fitness.dialog.LoadingDialog
import com.display.fitness.http.HttpCallback
import com.display.fitness.http.HttpClientCenter
import com.display.fitness.toolbar.CustomToolBar


class ForumTipsUpActivity : AppCompatActivity(), AlertDialog.OnDialogButtonClickListener {

    private var addCircle: TextView? = null
    private var index: Int = 0
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_forum_tips_uplod)
        initView()
    }

    fun initView() {
        val mToolbar = findViewById<CustomToolBar>(R.id.toolbar)
        addCircle = findViewById(R.id.forum_add_tips)

        mToolbar.setMyCenterTitle(R.string.tips_up, true)
        mToolbar.setMySettingText(R.string.publish)
        mToolbar.setMySettingTextColor(resources.getColor(R.color.gray_color))

        mToolbar.setNavigationOnClickListener { finish() }
        val mContent = findViewById<EditText>(R.id.forun_add_tips_content)
        addCircle?.setOnClickListener {
            startActivityForResult(Intent(this, ForumTipsIconsActivity::class.java), REQUEST_CODE)
        }
        mToolbar.setSettingTextOnClickListener {
            post(mContent.text.toString())
        }
    }

    private fun post(mContent: String) {
        if (TextUtils.isEmpty(mContent) || index == 0) {
            AlertDialog(this@ForumTipsUpActivity, resources.getString(R.string.notice), resources.getString(R.string.content_empty), true, 0, this).show()
        } else {
            val dialog = LoadingDialog(this@ForumTipsUpActivity)
            dialog.show()

            HttpClientCenter.uploadTips(mContent, index++.toString(), object : HttpCallback<TipsBean.TipsUpInfo>() {
                override fun onSuccess(t: TipsBean.TipsUpInfo) {
                    Toast.makeText(this@ForumTipsUpActivity, resources.getString(R.string.publish_success), Toast.LENGTH_SHORT).show()
                    finish()
                }

                override fun onFail(e: Exception?) {
                    dialog.dismiss()
                    Toast.makeText(this@ForumTipsUpActivity, resources.getString(R.string.publish_failure), Toast.LENGTH_SHORT).show()
                    gotoFork()
                }


            })
        }
    }

    private fun gotoFork() {
        AlertDialog(this@ForumTipsUpActivity, resources.getString(R.string.notice), resources.getString(R.string.not_fork), resources.getString(R.string.cancle), resources.getString(R.string.goto_fork), 1, this).show()
    }

    override fun onDialogButtonClick(requestCode: Int, isPositive: Boolean) {
        when (requestCode) {
            0 -> if (isPositive) {
                return
            } else {
                finish()
            }

            1 -> if (!isPositive) {
                val intent = Intent(this@ForumTipsUpActivity, ForumItemActivity::class.java)
                intent.putExtra("ForumItemActivity", index)
                startActivity(intent)
            } else {
                finish()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                val circleInfo = data?.getSerializableExtra("ForumTipsIconsActivity") as CircleInfo
                addCircle?.text = circleInfo.name
                index = circleInfo.id
            }
        }
    }
}