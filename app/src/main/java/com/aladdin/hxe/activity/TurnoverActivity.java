package com.aladdin.hxe.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.adapter.TurnoverAdapter;
import com.aladdin.hxe.bean.OrderByDataBean;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TurnoverActivity extends BaseActivity implements XRecyclerView.LoadingListener, View.OnClickListener {
    private int page = 1;
    private int size = 20;
    private String orderStatus = "0";
    private String isPay = "2";
    private XRecyclerView mRecyclerView;
    private ArrayList<OrderByDataBean.DataBean.RowsBean> listAll = new ArrayList<>();
    private TurnoverAdapter myAdapter = new TurnoverAdapter(this);
    private int total;
    private boolean isChecked = false;
    private List<OrderByDataBean.DataBean.RowsBean> rows;
    private String currentTime;
    private TextView tv_turnoverTime;
    private ImageButton imb_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turnover);
        initView();
        initData();
    }

    private void initData() {
         //设置适配器
        tv_turnoverTime.setText(currentTime.substring(0, 10));
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        mRecyclerView.getDefaultFootView().setLoadingHint("订单加载中");
        mRecyclerView.getDefaultFootView().setNoMoreHint("所有订单加载完毕");
        imb_back.setOnClickListener(this);
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

    private void initView() {
        //获取当前时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Date curDate = new Date(System.currentTimeMillis());
        currentTime = formatter.format(curDate);
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        mRecyclerView = (XRecyclerView) findViewById(R.id.recyclerview);
        tv_turnoverTime = (TextView) findViewById(R.id.tv_turnoverTime);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
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
        String token = SharedPreferencesUtils.getString(this, "token", "");
        RequestManager.postJson()
                .setUrl(Url.findOrdersByDate)
                .addParams("token", token).addParams("orderStatus", orderStatus).addParams("createTime", currentTime)
                .addParams("page", String.valueOf(page)).addParams("isPay", isPay)
                .addParams("size", String.valueOf(size))
                .builder().onUI()
                .setCallback(new IRequestBeanListener<OrderByDataBean>() {
                    @Override
                    public void onSuccess(OrderByDataBean baen) {
                        total = baen.getData().getTotal();
                        rows = baen.getData().getRows();
                        Log.d("EEEE", rows.size() + "");
                        //如果是刷新就清楚之前加载的数据
                        if (isRefrsh) {
                            listAll.clear();
                        }
                        listAll.addAll(rows);
                        Log.d("EEEE", listAll.size() + "");
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
    private void builderAdapter(ArrayList<OrderByDataBean.DataBean.RowsBean> listAll, Boolean isRefrsh) {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imb_back:
                finish();
                break;
        }
    }
}
