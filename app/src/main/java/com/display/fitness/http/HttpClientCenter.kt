package com.display.fitness.http

import android.util.Log
import com.display.fitness.app.MyApplication
import com.display.fitness.constant.Constans
import com.display.fitness.http.ClientHelper
import com.display.fitness.http.HttpCallback
import com.display.fitness.http.HttpInterceptor
import com.display.fitness.http.JsonCallback
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
 * @author : 六天
 * @date :   2021/3/25
 * @mail :   wangyijing01@bilibili.com
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

    fun login(telephone: String, password: String, callback: HttpCallback<CommonJson?>) {
        OkHttpUtils.post().url("http://118.31.58.177/user/login")
                .addParams("telephone", "17395676291")
                .addParams("password", "123456")
                .build()
                .execute(object : JsonCallback<CommonJson>(CommonJson::class.java) {
                    override fun onResponse(response: CommonJson?, id: Int) {
                        if (response?.code == "200") {
                            callback.onSuccess(response)
                            ClientHelper.setUserData(response)
                        } else {
                            callback.onFail(null)
                        }
                    }

                    override fun onError(call: Call, e: java.lang.Exception, id: Int) {
                        callback.onFail(e)
                        Log.e("TAG", call.toString() + "_____" + "call")
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
                        if(response?.code == "200") {
                            callback.onSuccess(response)
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
     * 获取每个运动的时间
     */
    fun getEachSportTime(id: String, callback: HttpCallback<EachSportTime>) {
        OkHttpUtils.post().url(Constans.ROOT_URL + Constans.EACH_SPORT_TIME)
                .addParams("id", id)
                .build()
                .execute(object : JsonCallback<EachSportTime>(EachSportTime::class.java) {
                    override fun onResponse(response: EachSportTime?, id: Int) {
                        if(response?.code == "200") {
                            callback.onSuccess(response)
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


    fun editUserNickName(key: String ,need: String,callback: HttpCallback<SportInforEntity?>) {
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

                    override fun onAfter(id: Int) {
                        callback.onFinish()
                    }
                })
    }
}