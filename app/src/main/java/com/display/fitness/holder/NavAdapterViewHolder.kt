package com.example.deamon.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.display.fitness.R
import com.display.fitness.bean.HomeMenuItem
import com.example.baserecyclerhelper.viewholder.Holder

/**
 * @author : 六天
 * @date :   2021/3/15
 * @mail :   wangyijing01@bilibili.com
 */
class NavAdapterViewHolder internal constructor(itemView: View) : Holder<HomeMenuItem?>(itemView) {
    private var icon: ImageView? = null
    private var text: TextView? = null
    override fun initView(itemView: View) {
        icon = itemView.findViewById(R.id.iv_menu_icon)
        text = itemView.findViewById(R.id.tv_menu_text)
    }

    override fun onBind(context: Context?, list: List<HomeMenuItem?>?, data: HomeMenuItem?, position: Int) {
        if (data == null) return
        text!!.text = data.text
        icon?.setImageResource(data.getIcon());
        icon?.let {
            Glide.with(context!!)
                    .asBitmap()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.default_place_holder)
                    .load(data.getUrl())
                    .into(it)
        }

    }
}
