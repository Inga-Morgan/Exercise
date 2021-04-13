package com.example.baserecyclerhelper.listener;

import androidx.annotation.Nullable;

/**
 * @author: ye's
 * @date: 2021/2/23
 * @desc: LoadMore需要设置的接口。使用java定义，以兼容java写法
 */
public interface LoadMoreListenerImp {

    void setOnLoadMoreListener(@Nullable OnLoadMoreListener listener);
}
