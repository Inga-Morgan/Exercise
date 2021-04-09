package com.display.fitness.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.display.fitness.R;
import com.display.fitness.bean.GroupIconsBean;
import com.display.fitness.listener.RecyclerOnScrollerListener;

import java.util.List;

/**
 * @author : 六天
 * @date :   2021/4/8
 * @mail :   wangyijing01@bilibili.com
 */
public class ListDialogAdapter extends RecyclerView.Adapter<ListDialogAdapter.MyViewHolder> implements View.OnClickListener {

    private List<GroupIconsBean.CircleIcons> mList;
    private RecyclerOnScrollerListener mOnScrollerListener;
    private RecyclerViewItemClickListener recyclerViewItemClickListener;
    private Context context;

    public ListDialogAdapter(List<GroupIconsBean.CircleIcons> list, RecyclerViewItemClickListener click, Context context) {
        this.mList = list;
        this.recyclerViewItemClickListener = click;
        this.context = context;
    }


    private OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int pos = holder.getLayoutPosition();
        GroupIconsBean.CircleIcons items = mList.get(position);
        Glide.with(context).load(items.getIcon())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.header)
                .centerCrop()
                .into(holder.circleIcon);
        holder.circleName.setText(items.getName());
        holder.circleAttenNumber.setText(items.getUser() + context.getString(R.string.circle_number));
        holder.circleUserNumber.setText(items.getUser() + context.getString(R.string.circle_tips));
        holder.selected.setText("选择");
        holder.selected.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder, pos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (mOnItemClickListener != null) {
            switch (v.getId()) {
                case R.id.circle_selected:
                    mOnItemClickListener.onClick(v, ViewName.PRACTISE, position);
                    break;
                default:
                    mOnItemClickListener.onClick(v, ViewName.ITEM, position);
                    break;
            }
        }
    }

    /** item里面有多个控件可以点击 */
    public enum ViewName {
        ITEM,
        PRACTISE
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, ViewName viewName, int position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView circleIcon;
        public TextView circleName;
        public TextView circleUserNumber;
        public TextView circleAttenNumber;
        public Button selected;

        public MyViewHolder(View itemView) {
            super(itemView);
            circleIcon = itemView.findViewById(R.id.circle_icon);
            circleName = itemView.findViewById(R.id.circle_name);
            circleUserNumber = itemView.findViewById(R.id.circle_user_number);
            circleAttenNumber = itemView.findViewById(R.id.circle_tips_number);
            selected = itemView.findViewById(R.id.circle_selected);
            selected.setOnClickListener(ListDialogAdapter.this);
        }


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

    public void setData(List<GroupIconsBean.CircleIcons> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int currentPage);
    }

    public interface OnItemClickListener {
        void onItemClick(MyViewHolder viewHolder, int position);
    }

    private TipsAdapter.OnLoadMoreListener mOnLoadMoreListener;

    public void setOnLoadMoreListener(TipsAdapter.OnLoadMoreListener listener) {
        this.mOnLoadMoreListener = listener;
    }

    public interface RecyclerViewItemClickListener {
        void clickOnItem(String data);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
}
