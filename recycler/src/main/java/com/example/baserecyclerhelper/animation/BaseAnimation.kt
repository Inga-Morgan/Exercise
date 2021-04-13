package com.example.baserecyclerhelper.animation

import android.animation.Animator
import android.view.View

/**
 * @author: ye's
 * @date: 2021/2/23
 * @desc:
 */
interface BaseAnimation {
    fun animators(view: View): Array<Animator>
}