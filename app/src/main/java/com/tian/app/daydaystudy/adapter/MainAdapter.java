package com.tian.app.daydaystudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tian.app.daydaystudy.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHold>{
    Context context;
    List<String> data;
    mainOnItemClickListener mListener;
    public MainAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    public void setmListener(mainOnItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public MainViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.textview_layout,null);
        return new MainViewHold(view);

    }

    @Override
    public void onBindViewHolder(final MainViewHold holder, final int position) {
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

    class MainViewHold extends RecyclerView.ViewHolder{
        @ViewInject(R.id.main_text)
        TextView maintv;
        public MainViewHold(View itemView) {
            super(itemView);
            x.view().inject(this,itemView);
        }
    }
    public interface mainOnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
}
