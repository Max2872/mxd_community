package com.project.mxd.mxd_community;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by mxd on 2018/3/5.
 */

public abstract class GiftBoxAdapter<T> extends BaseAdapter {
    private ArrayList<T> itemData;
    private int itemLayoutRes;

    public GiftBoxAdapter() {}

    public GiftBoxAdapter(ArrayList<T> itemData,int itemLayoutRes) {
        this.itemData = itemData;
        this.itemLayoutRes = itemLayoutRes;
    }

    @Override
    public int getCount() {
        return itemData != null ? itemData.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return itemData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.bind(parent.getContext(), convertView, parent, itemLayoutRes
                , position);
        bindView(holder, getItem(position));
        return holder.getItemView();
    }
    public abstract void bindView(ViewHolder holder, T obj);

    //添加一个元素
    public void add(T data) {
        if (itemData == null) {
            itemData = new ArrayList<>();
        }
        itemData.add(data);
        notifyDataSetChanged();
    }

    //往特定位置，添加一个元素
    public void add(int position, T data) {
        if (itemData == null) {
            itemData = new ArrayList<>();
        }
        itemData.add(position, data);
        notifyDataSetChanged();
    }

    public void remove(T data) {
        if (itemData != null) {
            itemData.remove(data);
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (itemData != null) {
            itemData.remove(position);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        if (itemData != null) {
            itemData.clear();
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        private SparseArray<View> mViews;   //存储ListView 的 item中的View
        private View item;                  //存放convertView
        private int position;               //游标
        private Context context;            //Context上下文

        //构造方法，完成相关初始化
        private ViewHolder(Context context, ViewGroup parent, int layoutRes) {
            mViews = new SparseArray<>();
            this.context = context;
            View convertView = LayoutInflater.from(context).inflate(layoutRes, parent, false);
            convertView.setTag(this);
            item = convertView;
        }

        //绑定ViewHolder与item
        public static ViewHolder bind(Context context, View convertView, ViewGroup parent,
                                      int layoutRes, int position) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder(context, parent, layoutRes);
            } else {
                holder = (ViewHolder) convertView.getTag();
                holder.item = convertView;
            }
            holder.position = position;
            return holder;
        }

        @SuppressWarnings("unchecked")
        public <T extends View> T getView(int id) {
            T t = (T) mViews.get(id);
            if (t == null) {
                t = (T) item.findViewById(id);
                mViews.put(id, t);
            }
            return t;
        }


        /**
         * 获取当前条目
         */
        public View getItemView() {
            return item;
        }

        /**
         * 获取条目位置
         */
        public int getItemPosition() {
            return position;
        }

        /**
         * 设置文字
         */
        public ViewHolder setText(int id, CharSequence text) {
            View view = getView(id);
            if (view instanceof TextView) {
                ((TextView) view).setText(text);
            }
            return this;
        }

        /**
         * 设置图片
         */
        public ViewHolder setImageResource(int id, int drawableRes) {
            View view = getView(id);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(drawableRes);
            } else {
                view.setBackgroundResource(drawableRes);
            }
            return this;
        }


        /**
         * 设置点击监听
         */
        public ViewHolder setOnClickListener(int id, View.OnClickListener listener) {
            getView(id).setOnClickListener(listener);
            return this;
        }

        /**
         * 设置可见
         */
        public ViewHolder setVisibility(int id, int visible) {
            getView(id).setVisibility(visible);
            return this;
        }

        /**
         * 设置标签
         */
        public ViewHolder setTag(int id, Object obj) {
            getView(id).setTag(obj);
            return this;
        }

        //其他方法可自行扩展
    }
}
