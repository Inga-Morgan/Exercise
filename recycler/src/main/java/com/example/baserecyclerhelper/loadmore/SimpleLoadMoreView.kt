package com.example.baserecyclerhelper.loadmore


import android.view.View
import android.view.ViewGroup
import com.example.baserecyclerhelper.R
import com.example.baserecyclerhelper.utils.getItemView
import com.example.baserecyclerhelper.viewholder.BaseViewHolder

/**
 * @author: 六天
 * @date: 2021/2/23
 * @desc:
 */
class SimpleLoadMoreView : BaseLoadMoreView() {

    override fun getRootView(parent: ViewGroup): View =
            parent.getItemView(R.layout.brvah_quick_view_load_more)

    override fun getLoadingView(holder: BaseViewHolder): View =
            holder.getView(R.id.load_more_loading_view)

    override fun getLoadComplete(holder: BaseViewHolder): View =
            holder.getView(R.id.load_more_load_complete_view)

    override fun getLoadEndView(holder: BaseViewHolder): View =
            holder.getView(R.id.load_more_load_end_view)

    override fun getLoadFailView(holder: BaseViewHolder): View =
            holder.getView(R.id.load_more_load_fail_view)
}