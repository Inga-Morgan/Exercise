package com.example.baserecyclerhelper.animation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.example.baserecyclerhelper.animation.BaseAnimation

/**
 * @author: 六天
 * @date: 2021/2/23
 * @desc:
 */
class SlideInRightAnimation : BaseAnimation {
    override fun animators(view: View): Array<Animator> {
        val animator = ObjectAnimator.ofFloat(view, "translationX", view.rootView.width.toFloat(), 0f)
        animator.duration = 400L
        animator.interpolator = DecelerateInterpolator(1.8f)
        return arrayOf(animator)
    }
}