package com.display.fitness.forum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.display.fitness.R

/**
 * @author : 六天
 * @date : 2021/4/14
 * @desc :
 */
class TipsDetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_recycler_tips, container, false)

        return view
    }

}