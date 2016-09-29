package com.tian.app.daydaystudy.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.tian.app.daydaystudy.R;
import com.tian.app.daydaystudy.adapter.DesginAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DesginFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    private int mPage;
    XRecyclerView xRecyclerView;
    private DesginAdapter adapter;
    private ArrayList<String> mlistData;
    public static DesginFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, page);
        DesginFragment fragment = new DesginFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_desgin, container, false);
        xRecyclerView= (XRecyclerView) view.findViewById(R.id.desgin_xv);
        initData();
        initXv();
        return view;
    }

    private void initData() {
        mlistData=new ArrayList<String>();
        for (int i='A';i<'J';i++){
            mlistData.add("cardView"+(char)i);
        }
    }

    private void initXv() {
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        xRecyclerView.setItemAnimator(new DefaultItemAnimator());
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        xRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉
                NoticeDataChange(1);
            }

            @Override
            public void onLoadMore() {
                //上拉
                if (mlistData.size()<15) {
                    NoticeDataChange(2);
                }else {
                    xRecyclerView.setIsnomore(true);
                    xRecyclerView.loadMoreComplete();
                }
            }
        });
        adapter=new DesginAdapter(getActivity(),mlistData);
        xRecyclerView.setAdapter(adapter);
        adapter.setmListener(new DesginAdapter.mainOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "我点击的是"+(mPage+1)+"页第"+(position+1)+"项", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    /**
     * 动作处理
     * @param tag
     */
    private void NoticeDataChange(int tag) {
        switch (tag){
            case 1:
                mlistData.add(0, "下拉刷新");
                break;
            case 2:
                mlistData.add(mlistData.size(), "上拉加载");
                break;

        }
        adapter.notifyDataSetChanged();
        xRecyclerView.refreshComplete();
        xRecyclerView.loadMoreComplete();
    }

}
