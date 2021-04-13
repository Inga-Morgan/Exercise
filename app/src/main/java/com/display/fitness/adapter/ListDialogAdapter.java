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

import java.util.List;

/**
 * @author : ye's
 * @date :   2021/4/8
 * @desc :
 */
public class ListDialogAdapter extends RecyclerView.Adapter<ListDialogAdapter.MyViewHolder> implements View.OnClickListener {

    private List<GroupIconsBean.CircleIcons> mList;
    private final Context context;

    public ListDialogAdapter(List<GroupIconsBean.CircleIcons> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_list, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.selected.setTag(position);
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
    }

    @Override
    public int getItemCount() {
        return mList.size();
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
            itemView.setOnClickListener(ListDialogAdapter.this);
        }
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    /**
     * item里面有多个控件可以点击
     */
    public enum ViewName {
        ITEM,
        PRACTISE
    }


    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, ViewName viewName, int position);
    }

    @Override
    public void onClick(View v) {
        //注意这里使用getTag方法获取数据
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
}
