package com.display.fitness.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * @author : ye's
 * @date :   2021/3/15
 * @desc :
 */
public class HomeMenuAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public HomeMenuAdapter(@Nullable List<String> data) {
        super(android.R.layout.simple_list_item_1, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(android.R.id.text1, "item " + helper.getAdapterPosition());
    }
}
