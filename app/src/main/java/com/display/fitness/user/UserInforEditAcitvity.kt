package com.display.fitness.user

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.display.fitness.R
import com.display.fitness.bean.UserInfoBean
import com.display.fitness.bean.UsersInfo
import com.display.fitness.dialog.LoadingDialog
import com.display.fitness.http.HttpCallback
import com.display.fitness.http.HttpClientCenter
import com.display.fitness.model.CommonJson
import com.display.fitness.toolbar.CustomToolBar
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File

class UserInforEditAcitvity : AppCompatActivity(), View.OnClickListener {

    private var userInfos: UserInfoBean? = null
    private lateinit var userInfo: CommonJson.UserInfo

    //相机拍照保存的位置
    private lateinit var photoFile: File
    private lateinit var photoUri: Uri
    private lateinit var header: RelativeLayout
    private lateinit var userHeader: CircleImageView
    private lateinit var userNickyName: TextView

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 1000 //权限
        private const val REQUEST_CODE_ALBUM = 1001 //相册
        private const val REQUEST_CODE_CAMERA = 1002 //相机
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_toolbar)
        initview()
    }


    fun initview() {
        val mToolbar = findViewById<CustomToolBar>(R.id.toolbar)
        mToolbar.setMyCenterTitle(R.string.info_details,true)
        mToolbar.setNavigationOnClickListener{ this@UserInforEditAcitvity.finish() }
        header = findViewById(R.id.user_details_header_layout)

        val user_nick_layout = findViewById<RelativeLayout>(R.id.user_info_nick_layout)
        userHeader = findViewById(R.id.user_info_header)
        userNickyName = findViewById<TextView>(R.id.details_nicky_content)

        userInfo = UsersInfo.getUserInfo()!!
        userNickyName.text = userInfo.name
        Glide.with(this).load(userInfo.userImage).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.header).into(userHeader)
        header.setOnClickListener(this)
        user_nick_layout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.user_info_nick_layout -> startActivityForResult(Intent(this, UserNickNameEditAcitvity::class.java), REQUEST_CODE_CAMERA)
            R.id.user_details_header_layout -> {
                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
                checkPermission()
            }
            else -> {

            }
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            openCamera()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CODE_PERMISSIONS)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                Toast.makeText(this, "拒绝会导致无法使用相机", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = getDestinationPath()
        photoUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
            FileProvider.getUriForFile(this@UserInforEditAcitvity, "com.display.fitness.provider", File(photoFile.path))
        } else {
            Uri.fromFile(photoFile)
        }
        //android11以后强制分区存储，外部资源无法访问，所以添加一个输出保存位置，然后取值操作
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        startActivityForResult(intent, REQUEST_CODE_CAMERA)
    }

    private fun getDestinationPath(): File {
        val fileName = String.format("fr_crop_%s.jpg", System.currentTimeMillis())
        return File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                userInfos?.setUserImg(photoFile)
                postUserImg(photoFile)

            }
            2 -> {
                userInfos?.setUserName(data?.extras?.get("result").toString())
                reportInfo(data?.extras?.get("result").toString())
            }

        }

    }


    private fun postUserImg(file: File) {
        val dialog = LoadingDialog(this@UserInforEditAcitvity)
        dialog.show()

        HttpClientCenter.postUserImg(file, object : HttpCallback<CommonJson>() {
            override fun onSuccess(t: CommonJson?) {
                dialog.dismiss()
                userHeader.post { Glide.with(this@UserInforEditAcitvity).load(userInfo.userImage)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.header).into(userHeader) }
            }

            override fun onFail(e: java.lang.Exception?) {
                dialog.dismiss()
                Toast.makeText(this@UserInforEditAcitvity, "头像上传失败", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun reportInfo(name: String) {
        Log.e("TAG_user", name + "_______" + userInfos?.userImg)
        HttpClientCenter.editUserInfo(name, object : HttpCallback<CommonJson>() {
            override fun onSuccess(t: CommonJson?) {
                userNickyName.post { userNickyName.text = t?.userInfo?.name }
            }

            override fun onFail(e: Exception?) {
                Toast.makeText(this@UserInforEditAcitvity, "昵称修改失败", Toast.LENGTH_SHORT).show()
            }

        })
    }


}