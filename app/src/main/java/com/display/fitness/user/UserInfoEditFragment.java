package com.display.fitness.user;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.display.fitness.R;
import com.display.fitness.base.BaseFragment;

import org.jetbrains.annotations.Nullable;


/**
 * @author : 六天
 * @date :   2021/3/31
 * @mail :   wangyijing01@bilibili.com
 */
public class UserInfoEditFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout header;
    private RelativeLayout birthLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, @Nullable View view) {
        header = view.findViewById(R.id.edit_init);
        birthLayout = view.findViewById(R.id.user_details_birth_layout);
        header.setOnClickListener(this);
        birthLayout.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_init:

                break;
            case R.id.user_details_birth_layout:
                break;
            default:
                break;

        }
    }
}
