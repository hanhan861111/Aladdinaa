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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.adapter.RefundAdapter;
import com.aladdin.hxe.bean.MessageEvent;
import com.aladdin.hxe.bean.RefundORderBean;
import com.aladdin.hxe.interfaces.OnItemClickListener;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.TimeUtils;
import com.aladdin.hxe.utils.Url;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${韩永光} on on 2018/2/27 0027 09:33..
 */

public class RefundFragment extends Fragment implements XRecyclerView.LoadingListener {

    private View view;
    private int page = 1;
    private int size = 20;
    private String orderStatus = "3";
    private  String isPay="2";
    private XRecyclerView mRecyclerView;
    private ArrayList<RefundORderBean.DataBean.RowsBean> listAll = new ArrayList<>();
    private RefundAdapter myAdapter = new RefundAdapter(getActivity());
    private int total;
    private boolean isChecked=false;
    private List<RefundORderBean.DataBean.RowsBean> rows;
    private String message;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_refund, null, false);
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
                final LinearLayout order_hide = (LinearLayout) view.findViewById(R.id.order_hide);
                LinearLayout ll_call = (LinearLayout) view.findViewById(R.id.ll_call);
                ListView lv_orderproducts = (ListView) view.findViewById(R.id.lv_orderproducts);
                final List<RefundORderBean.DataBean.RowsBean.OrdersProductsBean> ordersProducts = rows.get(position).getOrdersProducts();
                lv_orderproducts.setAdapter(new CommonAdapter<RefundORderBean.DataBean.RowsBean.OrdersProductsBean>(getActivity(), R.layout.orderinfo_item, ordersProducts) {
                    @Override
                    protected void convert(ViewHolder viewHolder, RefundORderBean.DataBean.RowsBean.OrdersProductsBean item, int position) {
                        viewHolder.setText(R.id.tv_goodsName, item.getGoodsName());
                        viewHolder.setText(R.id.tv_goodsCount, "X" + item.getGoodsNum());
                        viewHolder.setText(R.id.tv_goodsPrice, item.getGoodsPrice() + "元");
                    }
                });
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
                params.height = totalHeight + (lv_orderproducts.getDividerHeight() * (listAdapter.getCount() - 1));
                lv_orderproducts.setLayoutParams(params);
                if (isChecked) {
                    order_hide.setVisibility(View.GONE);
                    isChecked = false;
                } else {
                    order_hide.setVisibility(View.VISIBLE);
                    isChecked = true;
                }

            }

        });

    }

    private void initView(View view) {
        //EventBus
        EventBus.getDefault().register(this);
        mRecyclerView = (XRecyclerView) view.findViewById(R.id.recyclerview);
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
        String token = SharedPreferencesUtils.getString(getActivity(), "token", "");
        RequestManager.postJson()
                .setUrl(Url.findallallUrlA)
                .addParams("token", token).addParams("orderStatus", orderStatus)
                .addParams("createTime", TimeUtils.GetCurrentTime())
                .addParams("size", String.valueOf(size))
                .addParams("page", String.valueOf(page)).addParams("isPay",isPay)
                .builder().onUI()
                .setCallback(new IRequestBeanListener<RefundORderBean>() {
                    @Override
                    public void onSuccess(RefundORderBean baen) {
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
    private void builderAdapter(ArrayList<RefundORderBean.DataBean.RowsBean> listAll, Boolean isRefrsh) {
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
