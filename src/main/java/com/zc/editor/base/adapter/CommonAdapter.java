package com.zc.editor.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zc.editor.R;
import com.zc.editor.base.beans.BaseRecyclerBean;
import com.zc.editor.base.utils.StaticFinalValues;
import com.zc.editor.base.viewHolder.IBaseRecyclerItemClickListener;
import com.zc.editor.base.viewHolder.TextViewHolder;

import java.util.ArrayList;
import java.util.List;



public class CommonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private Activity mActivity;
    private List<BaseRecyclerBean> mBaseRecyclerBean = new ArrayList<>();
    protected IBaseRecyclerItemClickListener mIBaseRecyclerItemClickListener;
    public CommonAdapter(Context context, Activity activity, List<BaseRecyclerBean> classBeen,IBaseRecyclerItemClickListener listener) {
        mContext = context;
        mActivity = activity;
        mBaseRecyclerBean = classBeen;
        mIBaseRecyclerItemClickListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mBaseRecyclerBean != null ){
            return mBaseRecyclerBean.get(position % mBaseRecyclerBean.size()).getViewType();
        }
        return StaticFinalValues.VIEW_HOLDER_TEXT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case StaticFinalValues.VIEW_HOLDER_CLASS:
                view = LayoutInflater.from(mContext).inflate(R.layout.common_item, parent, false);
                return new CommonViewHolder(view);
            case StaticFinalValues.VIEW_HOLDER_TEXT:
                view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_text_item, parent, false);
                return new TextViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BaseRecyclerBean classBean = mBaseRecyclerBean.get(position % mBaseRecyclerBean.size());
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).setDataSource(classBean,holder.getAdapterPosition(),mIBaseRecyclerItemClickListener);
        }else if (holder instanceof CommonViewHolder){
            ((CommonViewHolder) holder).setDataSource(classBean,mActivity);
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mBaseRecyclerBean.size() > 0) {
            ret = mBaseRecyclerBean.size();
        }
        return ret;
    }



}
