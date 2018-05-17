package com.aladdin.hxe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.adapter.CallNumAdapter;
import com.aladdin.hxe.bean.MessageEvent;
import com.aladdin.hxe.bean.NewOrderBean;
import com.aladdin.hxe.interfaces.OnItemClickListener;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.TimeUtils;
import com.aladdin.hxe.utils.Url;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${韩永光} on on 2018/1/24 0024 10:50..
 */

public class CallNumFragment extends Fragment implements XRecyclerView.LoadingListener {
    private View view;
    private ToggleButton mToggleButton;
    private int page = 1;
    private int size = 20;
    private String isPay="2";
    private String orderStatus = "0";
    private XRecyclerView mRecyclerView;
    private ArrayList<NewOrderBean.DataBean.RowsBean> listAll = new ArrayList<>();
    private CallNumAdapter myAdapter = new CallNumAdapter(getActivity());
    private int total;
    private boolean isChecked = false;
    private String message;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_callnum, null, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(view);
        initData();
    }

    private void initData() {
        //recyclerView条目点击事件
        myAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                TextView tv_callNum = (TextView) view.findViewById(R.id.tv_callNum);
                tv_callNum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"点击了叫号",Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }

    private void initView(View view) {
        //EventBus
        EventBus.getDefault().register(this);
        mRecyclerView = (XRecyclerView) view.findViewById(R.id.recyclerview);
        mToggleButton = (ToggleButton) view.findViewById(R.id.ToggleButton);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        //设置适配器
        mRecyclerView.setAdapter(myAdapter);
        //初始化第一屏幕的数据
        // requestData(true);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//     mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        mRecyclerView.getDefaultFootView().setLoadingHint("订单加载中");
        mRecyclerView.getDefaultFootView().setNoMoreHint("所有订单加载完毕");


//        mRecyclerView.setLimitNumberToCallLoadMore(2);
        /**
         * 设置列表下拉刷新、上拉加载的监听
         */
        mRecyclerView.setLoadingListener(this);
        //设置允许下拉刷新
        mRecyclerView.setPullRefreshEnabled(true);
        //设置允许上拉加载
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.refresh();
    }

    @Override
    public void onRefresh() {
        page = 1;
       requestData(true);
    }

    @Override
    public void onLoadMore() {
        page++;
        requestData(false);
    }

    /**
     * 请求数据
     *
     * @param isRefrsh:标记是刷新还是加载
     */
    public void requestData(final Boolean isRefrsh) {
        Log.d("TTTTT",TimeUtils.GetCurrentTime());
        String token = SharedPreferencesUtils.getString(getActivity(), "token", "");
        RequestManager.postJson()
                .setUrl(Url.findallallUrlA)
                .addParams("token", token).addParams("orderStatus", orderStatus)
                .addParams("createTime", TimeUtils.GetCurrentTime())
                .addParams("size", String.valueOf(size))
                .addParams("page", String.valueOf(page)).addParams("isPay", isPay)
                .builder().onUI()
                .setCallback(new IRequestBeanListener<NewOrderBean>() {
                    @Override
                    public void onSuccess(NewOrderBean baen) {
                        total = baen.getData().getTotal();
                        List<NewOrderBean.DataBean.RowsBean> rows = baen.getData().getRows();

                        //如果是刷新就清楚之前加载的数据
                        if (isRefrsh) {
                            listAll.clear();
                        }
                        listAll.addAll(rows);
                        builderAdapter(listAll, isRefrsh);
                    }

                    @Override
                    public void onErr(String s) {

                    }
                });
    }

    /**
     * 刷新Adapter
     */
    private void builderAdapter(ArrayList<NewOrderBean.DataBean.RowsBean> listAll, Boolean isRefrsh) {
        myAdapter.setDatas(listAll);
        //模拟 表示服务器响应数据已经请求完了，可以屏蔽上拉加载操作
        if (isRefrsh) {
            //表示刷新完成
            mRecyclerView.refreshComplete();
            mRecyclerView.setLoadingMoreEnabled(true);
        } else {
            //表示加载完成
            mRecyclerView.loadMoreComplete();
        }
        if (total <= listAll.size()) {
            mRecyclerView.setLoadingMoreEnabled(false);
//            Toast.makeText(getActivity(), "没有更多的数据了", Toast.LENGTH_SHORT).show();
        } else {
            mRecyclerView.setLoadingMoreEnabled(true);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        message = messageEvent.getMessage();
        if (message.equals("NOTICE")) {
            onRefresh();
        }

    }

    //解除EventBus
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

    }
}
