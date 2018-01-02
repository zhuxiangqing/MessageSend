package com.zhuxiangqing.messageforwarder.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuxiangqing.messageforwarder.db.MessageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuxi on 2018/1/2.
 */

public abstract class BaseRVAdapter<T> extends RecyclerView.Adapter<BaseRVAdapter.BaseRVViewHolder<T>> {
    private List<T> list;
    public void refresh(List<T> list){
        if (null == list){
            return;
        }
        if (null == this.list){
            this.list = new ArrayList<>();
        }else {
            this.list.clear();
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public BaseRVViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRVViewHolder<>(LayoutInflater.from(parent.getContext()).inflate(getLayoutId(),parent,false),getVariant());
    }

    protected abstract int getLayoutId();

    protected abstract int getVariant();

    public void setListener(ItemViewClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(BaseRVViewHolder<T> holder, int position) {
        holder.bindData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return null == list ? 0 : list.size();
    }

    public static  class BaseRVViewHolder<T> extends RecyclerView.ViewHolder {

        private ViewDataBinding dataBinding;
        private int br;
        public BaseRVViewHolder(View itemView,int br) {
            super(itemView);
            dataBinding  = DataBindingUtil.bind(itemView);
            this.br = br;
        }

        protected  void bindData(T t){
            dataBinding.setVariable(br,t);
        };
    }

    public interface ItemViewClickListener {
        void onClick(RecyclerView.ViewHolder holder);
    }
}
