package com.display.fitness.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.display.fitness.R;
import com.display.fitness.base.BaseFragment;
import com.gyf.immersionbar.ImmersionBar;

/**
 * @author: 六天
 * @date: 2021/1/29
 */
public class SchduleFragment extends BaseFragment {


    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f)
                .navigationBarColor(R.color.green)
                .init();
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, @Nullable View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_story;
    }
}
