package com.example.baserecyclerhelper.viewholder;

import android.view.View;

/**
 * @Author: Zaaach
 * @Date: 2019/11/22
 * @Email: zaaach@aliyun.com
 * @Description:
 */
public interface TransformersHolderCreator<T> {
    Holder<T> createHolder(View itemView);
    int getLayoutId();
}
