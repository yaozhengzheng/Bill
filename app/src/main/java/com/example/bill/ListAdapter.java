package com.example.bill;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * description:.
 * author https://github.com/yaozhengzheng.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> implements View.OnClickListener {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> list;
    private OnItemClickListener clickListener;

    public ListAdapter(Context context, ArrayList<String> list) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.tvTime.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        if (clickListener != null) {
            clickListener.onItemClick((Integer) view.getTag());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        clickListener = itemClickListener;
    }
}

