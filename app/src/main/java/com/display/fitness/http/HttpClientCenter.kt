package com.display.fitness.http

import com.display.fitness.app.MyApplication
import com.display.fitness.bean.*
import com.display.fitness.constant.Constants
import com.display.fitness.model.CommonJson
import com.display.fitness.model.EachSportTime
import com.display.fitness.model.SportInfoBean
import com.display.fitness.model.SportInforEntity
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.FileCallBack
import com.zhy.http.okhttp.cookie.CookieJarImpl
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore
import okhttp3.Call
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * @author : ye's
 * @date :   2021/3/25
 *@desc
 */
object HttpClientCenter {
    init {
        val cookieJar = CookieJarImpl(PersistentCookieStore(MyApplication.getAppContext()))
        val okHttpClient = OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(HttpInterceptor())
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }


    //    public static void getSplash(@NonNull final HttpCallback<Splash> callback) {
    //        OkHttpUtils.get().url(SPLASH_URL).build()
    //                .execute(new JsonCallback<Splash>(Splash.class) {
    //                    @Override
    //                    public void onResponse(Splash response, int id) {
    //                        callback.onSuccess(response);
    //                    }
    //
    //                    @Override
    //                    public void onError(Call call, Exception e, int id) {
    //                        callback.onFail(e);
    //                    }
    //
    //                    @Override
    //                    public void onAfter(int id) {
    //                        callback.onFinish();
    //                    }
    //                });
    //    }
    fun downloadFile(url: String?, destFileDir: String?, destFileName: String?, callback: HttpCallback<File?>?) {
        OkHttpUtils.get().url(url).build()
                .execute(object : FileCallBack(destFileDir, destFileName) {
                    override fun inProgress(progress: Float, total: Long, id: Int) {}
                    override fun onResponse(file: File, id: Int) {
                        callback?.onSuccess(file)
                    }

                    override fun onError(call: Call, e: Exception, id: Int) {
                        callback?.onFail(e)
                    }

                    override fun onAfter(id: Int) {
                        callback?.onFinish()
                    }
                })
    }

    /**
     * 登陆
     */
    fun login(telephone: String, password: String, callback: HttpCallback<CommonJson?>) {
        OkHttpUtils.post().url(Constants.ROOT_URL + Constants.LOGIN_URL)
                .addParams("telephone", "17395676291")
                .addParams("password", "123456")
                .build()
                .execute(object : JsonCallback<CommonJson>(CommonJson::class.java) {
                    override fun onResponse(response: CommonJson?, id: Int) {
                        if (response?.code == "200") {
                            callback.onSuccess(response)
                            ClientHelper.setUserData(response)
                            UsersInfo.setUserInfo(response.userInfo)
                        } else {
                            callback.onFail(null)
                        }
                    }

                    override fun onError(call: Call, e: java.lang.Exception, id: Int) {
                        callback.onFail(e)
                    }

                    override fun onAfter(id: Int) {
                        callback.onFinish()
                    }
                })
    }


    fun getFitnessData(id: String, internal: String, callback: HttpCallback<SportInfoBean>) {
        OkHttpUtils.post().url("http://118.31.58.177/user/sportInfo")
                .addParams("id", id)
                .addParams("timeInterval", internal)
                .build()
                .execute(object : JsonCallback<SportInfoBean>(SportInfoBean::class.java) {
                    override fun onResponse(response: SportInfoBean?, id: Int) {
                        if (response?.code == "200") {
                            callback.onSuccess(response)
                        }
                    }

                    override fun onError(call: Call, e: java.lang.Exception, id: Int) {
                        callback.onFail(e)
                    }
                })
    }

    /**
     * 获取每个运动的时间
     */
    fun getEachSportTime(id: String, callback: HttpCallback<EachSportTime>) {
        OkHttpUtils.post().url(Constants.ROOT_URL + Constants.EACH_SPORT_TIME)
                .addParams("id", id)
                .build()
                .execute(object : JsonCallback<EachSportTime>(EachSportTime::class.java) {
                    override fun onResponse(response: EachSportTime?, id: Int) {
                        if (response?.code == "200") {
                            callback.onSuccess(response)
                        }
                    }

                    override fun onError(call: Call, e: java.lang.Exception, id: Int) {
                        callback.onFail(e)
                    }
                })
    }


    fun editUserNickName(key: String, need: String, callback: HttpCallback<SportInforEntity?>) {
        OkHttpUtils.post().url("http://118.31.58.177/user/update")
                .addParams(key, need)
                .build()
                .execute(object : JsonCallback<SportInfoBean>(SportInfoBean::class.java) {
                    override fun onResponse(response: SportInfoBean?, id: Int) {
                        if (response?.code == "200") {

                        }
                    }

                    override fun onError(call: Call, e: java.lang.Exception, id: Int) {
                        callback.onFail(e)
                    }
                })
    }

    /**
     * 更改用户昵称
     */
    fun editUserInfo(name: String, callback: HttpCallback<CommonJson>) {
        OkHttpUtils.post().url(Constants.ROOT_URL + Constants.USER_INFO_UPDATE)
                .addParams("name", name)
                .build()
                .execute(object : JsonCallback<CommonJson>(CommonJson::class.java) {
                    override fun onResponse(response: CommonJson?, id: Int) {
                        if (response?.code == "200") {
                            callback.onSuccess(response)
                            UsersInfo.setUserInfo(response.userInfo)
                        } else {
                            callback.onFail(null)
                        }
                    }

                    override fun onError(call: Call, e: java.lang.Exception, id: Int) {
                        callback.onFail(e)
                    }

                    override fun onAfter(id: Int) {
                        callback.onFinish()
                    }
                })
    }

    /**
     * 更改用户头像
     */
    fun postUserImg(file: File?, callback: HttpCallback<CommonJson>) {
        OkHttpUtils.post().url(Constants.ROOT_URL + Constants.USER_INFO_UPDATE)
                .addFile("userImage", file?.name, file)
                .build()
                .execute(object : JsonCallback<CommonJson>(CommonJson::class.java) {
                    override fun onResponse(response: CommonJson?, id: Int) {
                        if (response?.code == "200") {
                            callback.onSuccess(response)
                            UsersInfo.setUserInfo(response.userInfo)
                        } else {
                            callback.onFail(null)
                        }
                    }

                    override fun onError(call: Call, e: java.lang.Exception, id: Int) {
                        callback.onFail(e)
                    }
                })
    }

    /**
     * 获取帖子列表
     */
    fun getTipsList(id: String, callback: HttpCallback<TipsBean>) {
        OkHttpUtils.post().url(Constants.ROOT_URL + Constants.FORUM_LIST)
                .addParams("group",id)
                .build()
                .execute(object : JsonCallback<TipsBean>(TipsBean::class.java) {
                    override fun onResponse(response: TipsBean?, id: Int) {
                        if (response?.code == "200") {
                            callback.onSuccess(response)
                        } else {
                            callback.onFail(null)
                        }
                    }

                    override fun onError(call: Call?, e: java.lang.Exception?, id: Int) {
                        callback.onFail(null)
                    }

                })
    }

    fun uploadTips(content: String, group: String, callback: HttpCallback<TipsBean.TipsUpInfo>) {
        OkHttpUtils.post().url(Constants.ROOT_URL + Constants.POST_CREATE)
                .addParams("content", content)
                .addParams("group",group)
                .build()
                .execute(object : JsonCallback<TipsBean.TipsUpInfo>(TipsBean.TipsUpInfo::class.java) {
                    override fun onResponse(response: TipsBean.TipsUpInfo?, id: Int) {
                        if (response?.code == "200") {
                            callback.onSuccess(response)
                        } else {
                            callback.onFail(null)
                        }
                    }

                    override fun onError(call: Call, e: Exception, id: Int) {
                        callback.onFail(e)
                    }
                })
    }

    fun getCircleIcons(callback: HttpCallback<GroupIconsBean>) {
        OkHttpUtils.post().url(Constants.ROOT_URL + Constants.GROUP_LIST)
                .build()
                .execute(object : JsonCallback<GroupIconsBean>(GroupIconsBean::class.java) {
                    override fun onResponse(response: GroupIconsBean?, id: Int) {
                        if (response?.code == "200") {
                            callback.onSuccess(response)
                        } else {
                            callback.onFail(null)
                        }
                    }

                    override fun onError(call: Call, e: Exception, id: Int) {
                        callback.onFail(e)
                    }
                })
    }

    fun getTipsCommentList(id: String?, callback: HttpCallback<CommentBean?>) {
        OkHttpUtils.post().url(Constants.ROOT_URL + Constants.COMMENT_LIST)
                .addParams("id",id)
                .build()
                .execute(object : JsonCallback<CommentBean>(CommentBean::class.java) {
                    override fun onResponse(response: CommentBean?, id: Int) {
                        if (response?.code == "200") {
                            callback.onSuccess(response)
                        } else {
                            callback.onFail(null)
                        }
                    }

                    override fun onError(call: Call, e: Exception, id: Int) {
                        callback.onFail(e)
                    }
                })
    }

    fun postCommentContent(id: String?, comment: String?, callback: HttpCallback<CommentBean.ReplyCommentBean?>) {
        OkHttpUtils.post().url(Constants.ROOT_URL + Constants.COMMENT_CREATE)
                .addParams("post", id)
                .addParams("comment", comment)
                .build()
                .execute(object : JsonCallback<CommentBean.ReplyCommentBean>(CommentBean.ReplyCommentBean::class.java) {
                    override fun onResponse(response: CommentBean.ReplyCommentBean?, id: Int) {
                        if (response?.code == "200") {
                            callback.onSuccess(response)
                        } else {
                            callback.onFail(null)
                        }
                    }

                    override fun onError(call: Call, e: Exception, id: Int) {
                        callback.onFail(e)
                    }
                })
    }

    /**
     * 添加关注
     */
    fun addForkCircle(id: String?, callback: HttpCallback<CommentBean.ReplyCommentBean?>) {
        OkHttpUtils.post().url(Constants.ROOT_URL + Constants.GROUP_FORK)
                .addParams("group", id)
                .build()
                .execute(object : JsonCallback<CommentBean.ReplyCommentBean>(CommentBean.ReplyCommentBean::class.java) {
                    override fun onResponse(response: CommentBean.ReplyCommentBean?, id: Int) {
                        if (response?.code == "200") {
                            callback.onSuccess(response)
                        } else {
                            callback.onFail(null)
                        }
                    }

                    override fun onError(call: Call, e: Exception, id: Int) {
                        callback.onFail(e)
                    }
                })
    }

    /**
     * 查看关注
     */
    fun checkForkInfo(callback: HttpCallback<CircleForked?>) {
        OkHttpUtils.post().url(Constants.ROOT_URL + Constants.GROUP_MY_FORK)
                .build()
                .execute(object : JsonCallback<CircleForked>(CircleForked::class.java) {
                    override fun onResponse(response: CircleForked?, id: Int) {
                        if (response?.code == "200") {
                            callback.onSuccess(response)
                            UsersInfo.setCircleForked(response.data)
                        } else {
                            callback.onFail(null)
                        }
                    }

                    override fun onError(call: Call, e: Exception, id: Int) {
                        callback.onFail(e)
                    }
                })
    }

}