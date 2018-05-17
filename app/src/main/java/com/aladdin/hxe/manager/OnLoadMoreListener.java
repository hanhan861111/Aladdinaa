package com.aladdin.hxe.manager;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ${韩永光} on on 2018/1/26 0026 10:53..
 */

public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {
    private final LinearLayoutManager linearLayoutManager;
    private int newState;
    private int visibleItemCount;
    private boolean isLoding=false;
    private int preTotalItem;

    public OnLoadMoreListener(LinearLayoutManager linearLayoutManager) {
        super();
        this.linearLayoutManager =linearLayoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        this.newState=newState;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
            //当前课件条目数
            visibleItemCount = recyclerView.getChildCount();
            //可见的最后的条目索引
            int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            //总共条目
            int childCount = linearLayoutManager.getItemCount();
        if(childCount!=preTotalItem){
            //可以进行加载
            isLoding=false;
            //上次加载的总个数等于当前总个数
            preTotalItem=childCount;
        }
        if(!isLoding){
            if(lastVisibleItemPosition==childCount-1){
                isLoding=true;
                onLoadMore();
            }
        }

    }

    public abstract void onLoadMore();
}
