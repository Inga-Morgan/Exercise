package com.example.baserecyclerhelper;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.baserecyclerhelper.loadmore.BaseLoadMoreView;
import com.example.baserecyclerhelper.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

/**
 * @author: ye's
 * @date: 2021/2/23
 * @desc:
 */
public final class CustomLoadMoreView extends BaseLoadMoreView {

    @NotNull
    @Override
    public View getRootView(@NotNull ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.view_load_more, parent, false);
    }

    @NotNull
    @Override
    public View getLoadingView(@NotNull BaseViewHolder holder) {
        return holder.findView(R.id.load_more_loading_view);
    }

    @NotNull
    @Override
    public View getLoadComplete(@NotNull BaseViewHolder holder) {
        return holder.findView(R.id.load_more_load_complete_view);
    }

    @NotNull
    @Override
    public View getLoadEndView(@NotNull BaseViewHolder holder) {
        return holder.findView(R.id.load_more_load_end_view);
    }

    @NotNull
    @Override
    public View getLoadFailView(@NotNull BaseViewHolder holder) {
        return holder.findView(R.id.load_more_load_fail_view);
    }

}
