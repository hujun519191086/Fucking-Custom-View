package com.darren.custom.v12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CommonRecyAdapter<T> extends RecyclerView.Adapter<ViewRecyHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public CommonRecyAdapter(Context context, List<T> datas, int layoutId) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }

    public void setNewData(List<T> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewRecyHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        ViewRecyHolder viewHolder = ViewRecyHolder.get(mContext, null, parent, mLayoutId, -1);
        setListener(parent, viewHolder, viewType);
        return viewHolder;
    }

    protected int getPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }


    protected void setListener(final ViewGroup parent, final ViewRecyHolder viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    mOnItemClickListener.onItemClick(parent, v, mDatas.get(position), position);
                }
            }
        });


        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    return mOnItemClickListener.onItemLongClick(parent, v, mDatas.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewRecyHolder holder, int position) {
        holder.updatePosition(position);
        convert(holder, mDatas.get(position), position);
    }

    public abstract void convert(ViewRecyHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    //网上教程新增 增加删除item方法
    public void Add(int pos, T item) {
        mDatas.add(pos, item);
        notifyItemInserted(pos);
    }

    public void Delete(int pos) {
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }

    /** 移动进行数据交换
     * @param fromPosition
     * @param toPosition
     */
    public final void Move(int fromPosition, int toPosition) {
        Collections.swap(mDatas, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    public List<T> getData() {
        if (null != mDatas) {
            return mDatas;
        } else {
            return new ArrayList<>();
        }
    }
}
