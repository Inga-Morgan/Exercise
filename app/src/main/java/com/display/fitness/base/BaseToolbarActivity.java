package com.display.fitness.base;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.display.fitness.R;


/**
 * @author: yees
 * @date: 2021/2/10
 * @desc:
 */
public abstract class BaseToolbarActivity extends AppCompatActivity {

    protected Toolbar mToolbar;
    protected TextView mToolbar_tv_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_toolbar, (ViewGroup) getWindow().getDecorView().getRootView(), false);

        mToolbar = view.findViewById(R.id.toolbar);
        mToolbar_tv_title = view.findViewById(R.id.toolbar_title);

        setTitle(getTitle());

        steepTitle();
        setContentView(view);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    /**
     * 加载沉浸式状态栏
     */
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
