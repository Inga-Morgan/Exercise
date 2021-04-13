package com.display.fitness.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.display.fitness.R;
import com.display.fitness.bean.TipsBean;
import com.display.fitness.bean.TipsInfo;
import com.display.fitness.listener.RecyclerOnScrollerListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author yis
 */
public class TipsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TipsInfo> mList;
    private Context mContext;
    private RecyclerOnScrollerListener mOnScrollerListener;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private boolean isCanLoadMore = true;
    private static final int PER_PAGE = 3;
    private Animation rotateAnimation;


    public TipsAdapter(Context context, List<TipsInfo> list) {
        this.mContext = context;
        this.mList = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if (mContext == null) {
            mContext = viewGroup.getContext();
        }
        if (rotateAnimation == null) {
            rotateAnimation = AnimationUtils.loadAnimation(mContext, R.anim.loading);
            rotateAnimation.setInterpolator(new LinearInterpolator());
        }
        if (viewType == TYPE_ITEM) {
            return new DataViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_item_tips, viewGroup, false));
        } else {
            return new FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_item_footer, viewGroup, false));
        }
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder myHolder, final int index) {
        if (myHolder.getItemViewType() == TYPE_ITEM) {
            TipsInfo item = mList.get(index);
            ((DataViewHolder) myHolder).name.setText(item.getUserName());
            ((DataViewHolder) myHolder).content.setText(item.getContent());
            ((DataViewHolder) myHolder).userId.setText(item.getUser());
            Glide.with(mContext).asBitmap().fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.default_place_holder)
                    .load(item.getUserImage())
                    .into(((DataViewHolder) myHolder).userHeader);
            ((DataViewHolder) myHolder).visionNum.setText(item.getViews());
            ((DataViewHolder) myHolder).praiseNum.setText(item.getViews());
            ((DataViewHolder) myHolder).answerNum.setText(item.getViews());

            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClickListener(v, index, myHolder);
                    }
                }
            });
        } else {
            if (isCanLoadMore) {
                ((FooterViewHolder) myHolder).showLoading();
            } else {
                Toast.makeText(mContext, "我也是有底线的", Toast.LENGTH_SHORT).show();
                ((FooterViewHolder) myHolder).showText("暂无更多数据");
            }
        }
    }


    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public interface OnItemClickListener {
        void OnItemClickListener(View view, int position, RecyclerView.ViewHolder myHolder);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public static class DataViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView userId;
        TextView content;
        TextView visionNum;
        TextView praiseNum;
        TextView answerNum;
        CircleImageView userHeader;

        public DataViewHolder(View viewAsk) {
            super(viewAsk);
            userHeader = viewAsk.findViewById(R.id.user_info_header);
            name = viewAsk.findViewById(R.id.user_nick_name);
            userId = viewAsk.findViewById(R.id.user_info_id);
            content = viewAsk.findViewById(R.id.tips_item_content);
            visionNum = viewAsk.findViewById(R.id.item_vision_num);
            answerNum = viewAsk.findViewById(R.id.item_answer_num);
            praiseNum = viewAsk.findViewById(R.id.item_praise_num);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {

        ImageView image = itemView.findViewById(R.id.footer_img);
        TextView tips = itemView.findViewById(R.id.footer_tips);

        public FooterViewHolder(View view) {
            super(view);
        }

        void showText(String s) {
            image.setVisibility(View.INVISIBLE);
            tips.setText(s);
        }

        void showLoading() {
            image.setImageResource(R.mipmap.ic_loading);
            tips.setText("正在加载中...");
            if (image != null) {
                image.startAnimation(rotateAnimation);
            }
        }
    }

    /**
     * 将RecycleView附加到Adapter上
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mOnScrollerListener = new RecyclerOnScrollerListener(recyclerView) {
            @Override
            public void onLoadMore(int currentPage) {
                mOnLoadMoreListener.onLoadMore(currentPage);
            }
        };
        recyclerView.addOnScrollListener(mOnScrollerListener);

        if (mList.size() < PER_PAGE) {
            setCanLoadMore(false);
        } else {
            setCanLoadMore(true);
        }
    }

    public void setCanLoadMore(boolean isCanLoadMore) {
        this.isCanLoadMore = isCanLoadMore;
        mOnScrollerListener.setCanLoadMore(isCanLoadMore);
        mOnScrollerListener.setLoading(false);
    }

    /**
     * 将RecycleView从Adapter解除
     */
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mOnScrollerListener != null) {
            recyclerView.removeOnScrollListener(mOnScrollerListener);
        }

        mOnScrollerListener = null;
    }

    public void setData(List<TipsInfo> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int currentPage);
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mOnLoadMoreListener = listener;
    }
}


