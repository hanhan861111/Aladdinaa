package com.aladdin.hxe.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.adapter.InquireAllOrderAdapter;
import com.aladdin.hxe.adapter.InquireIngOrderAdapter;
import com.aladdin.hxe.bean.MessageEventD;
import com.aladdin.hxe.bean.MessageEventTime;
import com.aladdin.hxe.bean.NewOrderBean;
import com.aladdin.hxe.interfaces.OnItemClickListener;
import com.aladdin.hxe.utils.CommonUtils;
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
 * Created by ${韩永光} on on 2018/4/27 0027 09:52..
 */

public class InquireIngOrderFragment extends Fragment implements XRecyclerView.LoadingListener, View.OnClickListener {
    private View view;
    private int page = 1;
    private int size = 20;
    private XRecyclerView mRecyclerView;
    private ArrayList<NewOrderBean.DataBean.RowsBean> listAll = new ArrayList<>();
    private InquireIngOrderAdapter myAdapter = new InquireIngOrderAdapter(getActivity());
    private int total;
    private String time;
    private ListView lv_orderproducts;
    private List<NewOrderBean.DataBean.RowsBean> rows;
    private LinearLayout ll_call;
    private TextView tv_open;
    private LinearLayout ll_inquire_hide;
    private TextView tv_dots;
    private boolean isChecked = false;
    private List<NewOrderBean.DataBean.RowsBean.OrdersProductsBean> ordersProducts;
    private NewOrderBean.DataBean.RowsBean rowsBean;
    private TextView tv_fistfoodName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_inquireallorder, container, false);
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
            public void onItemClick(View view, final int position) {
                ll_call = (LinearLayout) view.findViewById(R.id.ll_call);
                //打电话
                ll_call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
                        }
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + rows.get(position).getPhone());
                        intent.setData(data);
                        startActivity(intent);
                    }
                });
            }

        });
        //  recyclerView按钮点击事件
        myAdapter.buttonSetOnclick(new InquireAllOrderAdapter.ButtonInterface() {
            @Override
            public void onclick(View view, final int position) {
                tv_fistfoodName = (TextView) view.findViewById(R.id.tv_fistfoodName);
                tv_dots = (TextView) view.findViewById(R.id.tv_dots);
                rowsBean = listAll.get(position);
                ordersProducts = listAll.get(position).getOrdersProducts();
                tv_open = (TextView) view.findViewById(R.id.tv_open);
                ll_inquire_hide = (LinearLayout) view.findViewById(R.id.ll_inquire_hide);
                lv_orderproducts = (ListView) view.findViewById(R.id.lv_orderproducts);
                if (ordersProducts.size() > 0) {
                    lv_orderproducts.setAdapter(new BaseAdapter() {
                        @Override
                        public int getCount() {
                            return ordersProducts == null ? 0 : ordersProducts.size();
                        }

                        @Override
                        public Object getItem(int position) {
                            return null;
                        }

                        @Override
                        public long getItemId(int position) {
                            return 0;
                        }

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            MyViewHolder holder;
                            if (convertView == null) {
                                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderinfo_item, parent, false);
                                holder = new MyViewHolder();
                                holder.tv_goodsName = (TextView) convertView.findViewById(R.id.tv_goodsName);
                                holder.tv_goodsCount = (TextView) convertView.findViewById(R.id.tv_goodsCount);
                                holder.tv_goodsPrice = (TextView) convertView.findViewById(R.id.tv_goodsPrice);
                                convertView.setTag(holder);
                            } else {
                                holder = (MyViewHolder) convertView.getTag();
                            }
                            holder.tv_goodsName.setText(ordersProducts.get(position).getGoodsName());
                            holder.tv_goodsCount.setText("X" + ordersProducts.get(position).getGoodsNum() + "");
                            holder.tv_goodsPrice.setText(ordersProducts.get(position).getGoodsPrice() + "元");
                            return convertView;
                        }
                    });
                    //动态设置listView的高度
                    ListAdapter listAdapter = lv_orderproducts.getAdapter();
                    if (listAdapter == null) {
                        return;
                    }
                    int totalHeight = 0;
                    for (int i = 0; i < listAdapter.getCount(); i++) {
                        View listItem = listAdapter.getView(i, null, lv_orderproducts);
                        listItem.measure(0, 0);
                        totalHeight += listItem.getMeasuredHeight();
                    }
                    ViewGroup.LayoutParams params = lv_orderproducts.getLayoutParams();
                    params.height = totalHeight + (lv_orderproducts.getDividerHeight() * (listAdapter.getCount() - 1)) + CommonUtils.dip2px(30);
                    lv_orderproducts.setLayoutParams(params);
                    if (isChecked) {
                        tv_fistfoodName.setVisibility(View.VISIBLE);
                        tv_fistfoodName.setText(ordersProducts.get(0).getGoodsName());
                        tv_dots.setText("...");
                        tv_open.setText("展开");
                        ll_inquire_hide.setVisibility(View.GONE);
                        isChecked = false;
                    } else {
                        tv_dots.setText("");
                        tv_open.setText("收起");
                        tv_fistfoodName.setVisibility(View.GONE);
                        tv_dots.setVisibility(View.VISIBLE);
                        ll_inquire_hide.setVisibility(View.VISIBLE);
                        isChecked = true;
                    }
                }
            }
        });


    }

    private void initView(View view) {
        //EventBus
        EventBus.getDefault().register(this);
        time = TimeUtils.GetCurrentTime();
        mRecyclerView = (XRecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        //设置适配器
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        mRecyclerView.getDefaultFootView().setLoadingHint("订单加载中");
        mRecyclerView.getDefaultFootView().setNoMoreHint("所有订单加载完毕");
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
        requestData(true, time);
    }

    @Override
    public void onLoadMore() {
        page++;
        requestData(false, time);
    }

    /**
     * 请求数据
     *
     * @param isRefrsh:标记是刷新还是加载
     */
    public void requestData(final Boolean isRefrsh, String Time) {
        String token = SharedPreferencesUtils.getString(getActivity(), "token", "");
        RequestManager.postJson()
                .setUrl(Url.findOrdersByStatus)
                .addParams("token", token)
                .addParams("size", String.valueOf(size))
                .addParams("startTime", time)
                .addParams("endTime", TimeUtils.getAddTime(time))
                .addParams("page", String.valueOf(page))
                .builder().onUI()
                .setCallback(new IRequestBeanListener<NewOrderBean>() {
                    @Override
                    public void onSuccess(NewOrderBean baen) {
                        total = baen.getData().getTotal();
                        rows = baen.getData().getRows();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEventTime messageEvent) {
        time = messageEvent.getMessage();
        requestData(true, time);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEventD messageEvent) {
        String message = messageEvent.getMessage();
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

    /*存放控件*/
    public class MyViewHolder {
        public TextView tv_goodsName;
        public TextView tv_goodsCount;
        public TextView tv_goodsPrice;
    }
}
