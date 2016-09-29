package com.tian.app.daydaystudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tian.app.daydaystudy.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiujiu on 2016/9/29.
 */
public class DesginAdapter extends RecyclerView.Adapter<DesginAdapter.DesginViewHold>{
    Context context;
    List<String> data;
    mainOnItemClickListener mListener;
    protected LayoutInflater mInflater = null;
    public DesginAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        mInflater = LayoutInflater.from(context);
    }

    public void setmListener(mainOnItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public DesginViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.desgin_item_layout,parent,false);
        return new DesginViewHold(view);

    }

    @Override
    public void onBindViewHolder(final DesginViewHold holder, final int position) {
        holder.maintv.setText(data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(holder.itemView,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data==null){
            data=new ArrayList<>();
        }
        return data.size();
    }

    class DesginViewHold extends RecyclerView.ViewHolder{
        @ViewInject(R.id.liu)
        TextView maintv;
        public DesginViewHold(View itemView) {
            super(itemView);
            x.view().inject(this,itemView);
        }
    }
    public interface mainOnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
}
