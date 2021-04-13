package com.display.fitness.dialog;

import android.app.Activity;

/**
 * @author : ye's
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
