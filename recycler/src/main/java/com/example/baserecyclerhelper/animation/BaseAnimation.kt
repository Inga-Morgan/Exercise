package com.example.baserecyclerhelper.animation

import android.animation.Animator
import android.view.View

/**
 * @author: 六天
 * @date: 2021/2/23
 * @desc:
 */
interface BaseAnimation {
    fun animators(view: View): Array<Animator>
}