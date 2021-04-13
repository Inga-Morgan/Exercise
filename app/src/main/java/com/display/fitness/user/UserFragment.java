package com.display.fitness.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.display.fitness.R;
import com.display.fitness.base.BaseFragment;
import com.display.fitness.fitness.FitnessChartDataActivity;

/**
 * @author: ye's
 * @date: 2021/1/29
 * @desc:
 */
public class UserFragment extends BaseFragment implements View.OnClickListener {

    private ImageView userHeader;
    private TextView fitnessData;
    private TextView userInfoEdit;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, @Nullable View view) {
        if (view != null) {
            userHeader = view.findViewById(R.id.user_edit_detail);
            fitnessData = view.findViewById(R.id.fitness_data);
            userInfoEdit = view.findViewById(R.id.user_info_edit);
            fitnessData.setOnClickListener(this);
            userHeader.setOnClickListener(this);
            userInfoEdit.setOnClickListener(this);

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
