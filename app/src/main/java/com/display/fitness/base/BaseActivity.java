package com.display.fitness.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.display.fitness.R;

/**
 * @author: 六天
 * @date: 2021/2/1
 * @desc:
 */
public abstract class BaseActivity extends BaseToolbarActivity {
    /**
     * 当前Activity是否处于前台
     */
    public boolean isActive = false;

    private int navigationBarBackgroundColorValue = Color.MAGENTA;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_toolbar, (ViewGroup) getWindow().getDecorView().getRootView(), false);

        mToolbar = view.findViewById(R.id.toolbar);
        mToolbar_tv_title = view.findViewById(R.id.toolbar_title);


        steepTitle();
        setContentView(view);
        setSupportActionBar(mToolbar);

        setTitle(getTitle());
        initDatas();
    }

    /**
     * 设置状态栏状态
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );


            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams winParams = window.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            window.setAttributes(winParams);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setNavigationBarColor(navigationBarBackgroundColorValue);
        }
    }

    @Override
    protected void onResume() {
        isActive = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isActive = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 可被重写的接口
     */

    public abstract void initDatas();

    /**
     * 透明状态栏
     * TODO:(子类界面重写此方法)
     *
     * @return
     */
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return false;
    }

    /**
     * 状态栏背景颜色
     *
     * @return
     */
    protected Object registSatusbarBgcolor() {
        return "";
    }

    /**
     * 状态栏字体颜色
     * 深色/浅色切换
     *
     * @return
     * @dark true 深色
     * @light false 浅色
     * TODO:(子类界面重写此方法)
     */
    protected boolean isRegistSatusbarFontDark() {
        return false;
    }

    /**
     * 是否设置毛玻璃效果
     *
     * @return
     */
    protected View isRegistBlurTitle() {
        return null;
    }


    /**
     * 加载沉浸式状态栏
     */
    @Override
    public void steepTitle() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //注意要清除 FLAG_TRANSLUCENT_STATUS flag
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
    }

    /**
     * bar 中间标题
     *
     * @param title
     */
    @Override
    public void setTitle(CharSequence title) {
        mToolbar_tv_title.setText(title);
    }
}