package com.example.designnavigation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.designnavigation.R;
import com.example.designnavigation.view.NormalNavigationBar;
import com.google.android.material.navigation.NavigationView;

/**
 * @auther: yis
 * @date: 2021/1/29
 * @desc:
 */
public class HomeFragment extends Fragment {

    private TextView jumpToEnd;
    private NormalNavigationBar navigationBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
//        initNormalToolbar();
        jumpToEnd = view.findViewById(R.id.jumpToEnd);
        return view;
    }
}