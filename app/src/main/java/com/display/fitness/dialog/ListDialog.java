package com.display.fitness.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.display.fitness.R;

/**
 * @author : ye's
 * @date :   2021/4/8
 * @desc :   wangyijing01@bilibili.com
 */
public class ListDialog extends Dialog {


    private Activity activity;
    public Dialog dialog;
    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public ListDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ListDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public ListDialog(Activity activity, RecyclerView.Adapter adapter) {
        super(activity);
        this.activity = activity;
        this.adapter = adapter;
        setLayout();
    }

    private void setLayout() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_list);
        recyclerView = findViewById(R.id.dialog_list_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
    }
}
