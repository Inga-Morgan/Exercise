package com.display.fitness.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.display.fitness.R
import com.display.fitness.app.MyApplication
import com.display.fitness.http.HttpCallback
import com.display.fitness.http.HttpClientCenter
import com.display.fitness.model.CommonJson
import com.display.fitness.ui.NormalActivity
import com.display.fitness.utils.SaveUserInfoUtils

class LoginActivity : FragmentActivity(), View.OnClickListener {

    private val loginParam: CommonJson.LoginParam? = null
    private var telePhone: EditText? = null
    private var password: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    fun initView() {

        telePhone = findViewById(R.id.input_stuNum)
        password = findViewById(R.id.input_password)
        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener(this)

        if (MyApplication.getAppContext() == null) {
            Log.e("TAG___CONTEXT", "null")
        } else {
            Log.e("TAG___CONTEXT", MyApplication.getAppContext().toString())
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login -> login()
        }
    }

    fun login() {
        if (telePhone?.text.toString() != null && password?.text.toString() != null) {
            HttpClientCenter.login(telePhone?.text.toString(), password?.text.toString(), object : HttpCallback<CommonJson?>() {
                override fun onSuccess(t: CommonJson?) {
                    if (t == null) {
                        onFail(null)
                        return
                    }
                    SaveUserInfoUtils.setUserInfo(t.userInfo)
                    startActivity(Intent(this@LoginActivity, NormalActivity::class.java))
                }

                override fun onFail(e: Exception?) {
                    Toast.makeText(this@LoginActivity, "登录失败..请检查账号密码和您的网络状态..", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this@LoginActivity, "登录失败..请检查账号密码不能为空", Toast.LENGTH_SHORT).show()

        }
    }
}