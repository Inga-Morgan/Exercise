package com.display.fitness.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.display.fitness.R;
import com.display.fitness.base.BaseFragment;
import com.display.fitness.bean.UsersInfo;
import com.display.fitness.fitness.FitnessChartDataActivity;
import com.display.fitness.model.CommonJson;

/**
 * @author: ye's
 * @date: 2021/1/29
 * @desc:
 */
public class UserFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, @Nullable View view) {
        if (view != null) {
            ImageView userHeader = view.findViewById(R.id.user_head);
            TextView userNickName = view.findViewById(R.id.user_nick_name);
            TextView userLevel = view.findViewById(R.id.user_level);
            TextView userUID = view.findViewById(R.id.user_uid);
            ImageView userDetailsInfo = view.findViewById(R.id.user_edit_detail);
            TextView fitnessData = view.findViewById(R.id.fitness_data);
            TextView userInfoEdit = view.findViewById(R.id.user_info_edit);
            fitnessData.setOnClickListener(this);
            userDetailsInfo.setOnClickListener(this);
            userInfoEdit.setOnClickListener(this);
            CommonJson.UserInfo userInfo = UsersInfo.INSTANCE.getUserInfo();
            Glide.with(getActivity()).load(userInfo.getUserImage()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.header).into(userHeader);
            userNickName.setText(userInfo.getName());
            userLevel.setText(userInfo.getSchool());
            userUID.setText(userInfo.getSchool());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_edit_detail:
            case R.id.user_info_edit:
                startActivity(new Intent(getActivity(), UserInforEditAcitvity.class));
                break;
            case R.id.fitness_data:
                startActivity(new Intent(getActivity(), FitnessChartDataActivity.class));
                break;
            default:
                break;
        }
    }
}
