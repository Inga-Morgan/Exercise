package com.display.fitness.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.display.fitness.R;
import com.display.fitness.base.BaseActivity;
import com.display.fitness.forum.ScheduleFragment;
import com.display.fitness.fragment.HomeFragment;
import com.display.fitness.user.UserFragment;
import com.example.designnavigation.view.NormalNavigationBar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yis
 */
public class NormalActivity extends BaseActivity {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private NormalNavigationBar navigationBar;
    private ScheduleFragment scheduleFragment = new ScheduleFragment();
    private final String[] tabText = {"首页", "论坛", "我的"};
    /**
     * 未选中icon
     */
    private final int[] normalIcon = {R.mipmap.tab_home, R.mipmap.tab_schedule, R.mipmap.tab_mine};
    /**
     * 选中时icon
     */
    private final int[] selectIcon = {R.mipmap.tab_home_selected, R.mipmap.tab_schedule_selected, R.mipmap.tab_mine_selected};

    private final List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        initView();
    }

    private void initView() {
        navigationBar = findViewById(R.id.navigationBar);
        fragments.add(new HomeFragment());
        fragments.add(new ScheduleFragment());
        fragments.add(new UserFragment());


        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .canScroll(true)
                .build();
    }

    public NormalNavigationBar getNavigationBar() {
        return navigationBar;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode,data);
        scheduleFragment.onActivityResult(requestCode,resultCode,data);
    }
}