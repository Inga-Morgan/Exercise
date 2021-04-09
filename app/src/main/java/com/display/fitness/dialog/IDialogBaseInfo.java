package com.display.fitness.dialog;

import android.app.Activity;

/**
 * @author : 六天
 * @date :   2021/3/15
 * @desc :
 */
public interface IDialogBaseInfo {
    /**
     * 获取context
     */
    Activity getActivity();
    boolean isBackPressClose();
}
