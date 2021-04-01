package com.display.fitness.base

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.display.fitness.R
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.components.ImmersionFragment
import java.util.*


/**
 * @author : yees
 * @date :   2021/2/22
 * @mail :
 */
abstract class BaseFragment() : ImmersionFragment() {

    private var mDrawableList: MutableList<Int> = ArrayList()
    private var mPictureList: MutableList<Int> = ArrayList()
    private var mActivity: Activity? = null
    private var mRootView: View? = null
    private var toolbar: Toolbar? = null
    private var statusBarView: View? = null
    protected lateinit var mImmersionBar: ImmersionBar
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBeforeView(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(layoutId, container, false)
        } else {
            val viewGroup = mRootView!!.parent as ViewGroup
            viewGroup.removeView(mRootView)
        }
        initData(3)
        initView(savedInstanceState, mRootView)

        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        fitsLayoutOverlap()
        setListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.destroy(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        //旋转屏幕为什么要重新设置布局与状态栏重叠呢？因为旋转屏幕有可能使状态栏高度不一样，如果你是使用的静态方法修复的，所以要重新调用修复
        fitsLayoutOverlap()
    }

    protected fun initDataBeforeView(savedInstanceState: Bundle?) {}

    /**
     * Gets layout id.
     *
     * @return the layout id
     */
    protected abstract val layoutId: Int

    /**
     * init view
     */
    protected abstract fun initView(savedInstanceState: Bundle?, view: View?)

    /**
     * 设置监听
     */
    private fun setListener() {}
    private fun fitsLayoutOverlap() {
        if (statusBarView != null) {
            ImmersionBar.setStatusBarView(this, statusBarView)
        } else {
            ImmersionBar.setTitleBar(this, toolbar)
        }
    }

    /**
     * 初始化statusBar
     */
    override fun initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar
                .statusBarDarkFont(true, 0.2f)//设置状态栏图片为深色，(如果android 6.0以下就是半透明)
                .fitsSystemWindows(true)//设置这个是为了防止布局和顶部的状态栏重叠
                .statusBarColor(R.color.white)//这里的颜色，你可以自定义。
                .init();

    }

    /**
     * 初始化banner背景
     */
    private fun initData(j: Int) {
        mDrawableList.clear()
        for (i in 0..j) {
            val drawable = resources.getIdentifier("bg_card$i", "drawable", context?.packageName)
            mDrawableList.add(drawable)
        }
    }

    /**
     * 获取banner图片
     */
    protected fun getPicList(count: Int): MutableList<Int> {
        mPictureList.clear()
        for (i in 0..count) {
            val drawable = resources.getIdentifier("banner$i", "mipmap", context?.packageName)
            mPictureList.add(drawable)
        }
        return mPictureList
    }

    @ColorInt
    protected fun getColor(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(requireContext(), colorRes)
    }
}