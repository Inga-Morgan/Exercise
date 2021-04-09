package com.example.baserecyclerhelper.adapter;

import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;


import com.example.baserecyclerhelper.R;
import com.example.baserecyclerhelper.module.LoadMoreModule;
import com.example.baserecyclerhelper.utils.SpannableStringUtils;
import com.example.baserecyclerhelper.utils.Status;
import com.example.baserecyclerhelper.utils.Tips;
import com.example.baserecyclerhelper.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author: 六天
 * @date: 2021/2/23
 * @desc:
 */
public class LoadMoreAdapter extends BaseQuickAdapter<Status, BaseViewHolder> implements LoadMoreModule {

    public LoadMoreAdapter() {
        super(R.layout.layout_animation);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, @Nullable Status item) {
        switch (helper.getLayoutPosition() % 3) {
            case 0:
                helper.setImageResource(R.id.img, R.mipmap.animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.img, R.mipmap.animation_img2);
                break;
            case 2:
                helper.setImageResource(R.id.img, R.mipmap.animation_img3);
                break;
            default:
                break;
        }
        helper.setText(R.id.tweetName, "Hoteis in Rio de Janeiro");
        String msg = "\"He was one of Australia's most of distinguished artistes, renowned for his portraits\"";
        ((TextView) helper.getView(R.id.tweetText)).setText(SpannableStringUtils.getBuilder(msg).append("landscapes and nedes").setClickSpan(clickableSpan).create());
        ((TextView) helper.getView(R.id.tweetText)).setMovementMethod(LinkMovementMethod.getInstance());
    }

    ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            Tips.show("事件触发了 landscapes and nedes");
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getContext().getResources().getColor(R.color.clickspan_color));
            ds.setUnderlineText(true);
        }
    };
}
