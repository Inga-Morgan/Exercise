package com.display.fitness.http;//package com.example.deamon.http;
//
//import android.graphics.Bitmap;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.example.deamon.model.CommonJson;
//import com.zhy.http.okhttp.OkHttpUtils;
//import com.zhy.http.okhttp.callback.BitmapCallback;
//import com.zhy.http.okhttp.callback.FileCallBack;
//
//import java.io.File;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.Call;
//import okhttp3.OkHttpClient;
//
///**
// * Created by hzwangchenyan on 2017/2/8.
// */
//public class HttpClient {
//    private static final String SPLASH_URL = "http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";
//    private static final String BASE_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting";
//    private static final String METHOD_GET_MUSIC_LIST = "baidu.ting.billboard.billList";
//    private static final String METHOD_DOWNLOAD_MUSIC = "baidu.ting.song.play";
//    private static final String METHOD_ARTIST_INFO = "baidu.ting.artist.getInfo";
//    private static final String METHOD_SEARCH_MUSIC = "baidu.ting.search.catalogSug";
//    private static final String METHOD_LRC = "baidu.ting.song.lry";
//    private static final String PARAM_METHOD = "method";
//    private static final String PARAM_TYPE = "type";
//    private static final String PARAM_SIZE = "size";
//    private static final String PARAM_OFFSET = "offset";
//    private static final String PARAM_SONG_ID = "songid";
//    private static final String PARAM_TING_UID = "tinguid";
//    private static final String PARAM_QUERY = "query";
//
//    static {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//                .addInterceptor(new HttpInterceptor())
//                .build();
//        OkHttpUtils.initClient(okHttpClient);
//    }
//
////    public static void getSplash(@NonNull final HttpCallback<Splash> callback) {
////        OkHttpUtils.get().url(SPLASH_URL).build()
////                .execute(new JsonCallback<Splash>(Splash.class) {
////                    @Override
////                    public void onResponse(Splash response, int id) {
////                        callback.onSuccess(response);
////                    }
////
////                    @Override
////                    public void onError(Call call, Exception e, int id) {
////                        callback.onFail(e);
////                    }
////
////                    @Override
////                    public void onAfter(int id) {
////                        callback.onFinish();
////                    }
////                });
////    }
//
//    public static void downloadFile(String url, String destFileDir, String destFileName, @Nullable final HttpCallback<File> callback) {
//        OkHttpUtils.get().url(url).build()
//                .execute(new FileCallBack(destFileDir, destFileName) {
//                    @Override
//                    public void inProgress(float progress, long total, int id) {
//                    }
//
//                    @Override
//                    public void onResponse(File file, int id) {
//                        if (callback != null) {
//                            callback.onSuccess(file);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        if (callback != null) {
//                            callback.onFail(e);
//                        }
//                    }
//
//                    @Override
//                    public void onAfter(int id) {
//                        if (callback != null) {
//                            callback.onFinish();
//                        }
//                    }
//                });
//    }
//
//
//    public static void getBitmap(String url, @NonNull final HttpCallback<Bitmap> callback) {
//        OkHttpUtils.get().url(url).build()
//                .execute(new BitmapCallback() {
//                    @Override
//                    public void onResponse(Bitmap bitmap, int id) {
//                        callback.onSuccess(bitmap);
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
//
//    public static void login(String tingUid, @NonNull final HttpCallback<CommonJson> callback) {
//        OkHttpUtils.post().url("http://118.31.58.177/user/login")
//                .addParams("telephone", "17395617791")
//                .addParams("password", tingUid)
//                .build()
//                .execute(new JsonCallback<CommonJson>(CommonJson.class) {
//                    @Override
//                    public void onResponse(CommonJson response, int id) {
//                        if (response.getCode().equals("200")){
//                            callback.onSuccess(response);
//                            ClientHelper.setUserData(response);
//                        }else {
//                            callback.onFail(null);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        callback.onFail(e);
//                        Log.e("TAG",call.toString()+"_____"+"call");
//                    }
//
//                    @Override
//                    public void onAfter(int id) {
//                        callback.onFinish();
//                    }
//                });
//    }
//
//    public static void getFitnessData(, @NonNull final HttpCallback<CommonJson> callback) {
//        OkHttpUtils.post().url("http://118.31.58.177/user/sportInfo")
//                .addParams("id", )
//                .addParams("timeInterval", userInfo.internal)
//                .build()
//                .execute(new JsonCallback<CommonJson>(CommonJson.class) {
//                    @Override
//                    public void onResponse(CommonJson response, int id) {
//                        if (response.getCode().equals("200")){
//                            callback.onSuccess(response);
//                            ClientHelper.setUserData(response);
//                        }else {
//                            callback.onFail(null);
//                            Log.e("TAG",response.getMessage().toString()+ "_____response");
//
//                        }
//                        Log.e("TAG",response.toString()+"_____"+"response");
//                    }
//
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        callback.onFail(e);
//                        Log.e("TAG",call.toString()+"_____"+"call");
//                    }
//
//                    @Override
//                    public void onAfter(int id) {
//                        callback.onFinish();
//                    }
//                });
//    }
//}
