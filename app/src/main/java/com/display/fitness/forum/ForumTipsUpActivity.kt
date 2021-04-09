package com.display.fitness.forum

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.display.fitness.R
import com.display.fitness.adapter.ListDialogAdapter
import com.display.fitness.bean.GroupIconsBean
import com.display.fitness.bean.TipsBean
import com.display.fitness.dialog.AlertDialog
import com.display.fitness.dialog.ListDialog
import com.display.fitness.dialog.LoadingDialog
import com.display.fitness.http.HttpCallback
import com.display.fitness.http.HttpClientCenter
import com.display.fitness.toolbar.CustomToolBar
import java.io.Serializable


class ForumTipsUpActivity : AppCompatActivity(), AlertDialog.OnDialogButtonClickListener, ListDialogAdapter.RecyclerViewItemClickListener {

    private var addCircle: TextView? = null
    private var customDialog: ListDialog? = null
    private var list: MutableList<GroupIconsBean.CircleIcons>? = null
    private var mHandle = Handler(Looper.getMainLooper())
    private var name: String? = null
    private var index: String = 0.toString()

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
            Toast.makeText(this@ForumTipsUpActivity, "clicked", Toast.LENGTH_SHORT).show()
            circleSelected()
        }
        mToolbar.setSettingTextOnClickListener {
            post(mContent.text.toString())
        }
    }

    private fun post(mContent: String) {
        Log.e("TAG_index", addCircle?.text.toString() + index)

//        for (i in 0 ..list?.size) {
//            if (addCircle?.text.toString() == list!![i].getName()){
//                index == list[i].getId()
//                break
//            }
//        }
        if (TextUtils.isEmpty(mContent) || TextUtils.isEmpty(name)) {
            AlertDialog(this@ForumTipsUpActivity, resources.getString(R.string.notice), resources.getString(R.string.content_empty), true, 0, this).show()
        } else {
            val dialog = LoadingDialog(this@ForumTipsUpActivity)
            dialog.show()

            HttpClientCenter.uploadTips( mContent, index,object : HttpCallback<TipsBean.TipsUpInfo>() {
                override fun onSuccess(t: TipsBean.TipsUpInfo) {
                    Toast.makeText(this@ForumTipsUpActivity, resources.getString(R.string.publish_success), Toast.LENGTH_SHORT).show()
                    finish()
                }

                override fun onFail(e: Exception?) {
                    dialog.dismiss()
                    Toast.makeText(this@ForumTipsUpActivity, resources.getString(R.string.publish_failure), Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    override fun onDialogButtonClick(requestCode: Int, isPositive: Boolean) {
        if (isPositive) {
            return
        } else {
            finish()
        }
    }

    private fun circleSelected() {

        HttpClientCenter.getCircleIcons(object : HttpCallback<GroupIconsBean>() {
            override fun onSuccess(t: GroupIconsBean?) {
                list = t?.data
                list?.let {
                    addCircleIcons(it)
                }
            }

            override fun onFail(e: java.lang.Exception?) {
                Log.e("TAG", "Error")
            }
        })
    }

    fun addCircleIcons(list: MutableList<GroupIconsBean.CircleIcons>) {

        mHandle.post(Runnable {

            val dataAdapter = ListDialogAdapter(list, this, this@ForumTipsUpActivity)
            customDialog = ListDialog(this@ForumTipsUpActivity, dataAdapter)
            customDialog?.show()
            customDialog?.setCanceledOnTouchOutside(true)

//            dataAdapter.setOnItemClickListener { holder, position ->
//                holder.selected.setOnClickListener {
//                    addCircle?.text = list[position].getName()
//                    name = list[position].getName()
//                    customDialog?.dismiss()
//                    Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show()
//                    Log.e("TAG_init",position.toString())
//                }
//            }

            dataAdapter.setOnItemClickListener(object : ListDialogAdapter.OnRecyclerViewItemClickListener {
                override fun onClick(view: View?, viewName: ListDialogAdapter.ViewName?, position: Int) {
                    //在此处理点击事件即可，viewName可以区分是item还是内部控件
                    when (viewName) {
                        ListDialogAdapter.ViewName.PRACTISE -> {
                            addCircle?.text = list[position].getName()
                            name = list[position].getName()
                            customDialog?.dismiss()
                            Toast.makeText(this@ForumTipsUpActivity, "clicked", Toast.LENGTH_SHORT).show()
                            Log.e("TAG_init", position.toString())
                        }
                    }
                }
            })

        })

        Log.e("TAG___index", name + "_______" + index)
    }

    override fun clickOnItem(data: String?) {
        addCircle?.text = data;

        if (customDialog != null) {
            customDialog?.dismiss();
        }
    }
}

open class TipCircleIcons(val list: MutableList<GroupIconsBean.CircleIcons>) : Serializable