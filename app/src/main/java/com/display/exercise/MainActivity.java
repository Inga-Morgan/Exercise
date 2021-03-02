package com.display.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.designnavigation.view.NormalNavigationBar;

public class MainActivity extends AppCompatActivity {
    private NormalNavigationBar navigationBar;

//    private final String[] tabText = {"首页","论坛","我的"};
//    /**
//     * 未选中icon
//     */
//    private final int[] normalIcon = {R.mipmap.index, R.mipmap.find_noselected, R.mipmap.me};
//    /**
//     * 选中时icon
//     */
//    private final int[] selectIcon = {R.mipmap.index_selected, R.mipmap.find_selected, R.mipmap.me_selected};
//
//    private final List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        navigationBar = findViewById(R.id.navigationBar);
//        fragments.add(new MineFragment());
//        fragments.add(new StoryFragment());
//        fragments.add(new UserFragment());
//
//        navigationBar.titleItems(tabText)
//                .normalIconItems(normalIcon)
//                .selectIconItems(selectIcon)
//                .fragmentList(fragments)
//                .fragmentManager(getSupportFragmentManager())
//                .canScroll(true)
//                .build();


    }


    public NormalNavigationBar getNavigationBar() {
        return navigationBar;
    }

}
