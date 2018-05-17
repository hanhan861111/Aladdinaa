package com.aladdin.hxe.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.adapter.ScanAdapter;
import com.aladdin.hxe.bean.MessageEventTime;
import com.aladdin.hxe.bean.ScanBean;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.TimeUtils;
import com.aladdin.hxe.utils.Url;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScanActivity extends BaseActivity implements View.OnClickListener, XRecyclerView.LoadingListener {
    private int page = 1;
    private int size = 20;
    private String isPay = "2";
    private String orderStatus = "0";
    private ImageButton imb_back;
    private ViewPager vp_inquire;
    private TextView tv_today;
    private TextView tv_date;
    private Calendar calender;
    private String todayStr;
    private String token;
    private String mouth1, day1;
    private XRecyclerView mRecyclerView;
    private ArrayList<ScanBean.DataBean.RowsBean> listAll = new ArrayList<>();
    private ScanAdapter myAdapter = new ScanAdapter(this);
    private int total;
    private String time;
    private DatePickerDialog datePickerDialog;
    private static String dateStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        initView();
        initEvent();
    }

    private void initEvent() {
        imb_back.setOnClickListener(this);
        tv_date.setOnClickListener(this);
        tv_today.setOnClickListener(this);
    }

    private void initView() {
        //EventBus
        EventBus.getDefault().register(this);
        time = TimeUtils.GetCurrentTime();
        todayStr = TimeUtils.GetCurrentTime();
        calender = Calendar.getInstance();
        mRecyclerView = (XRecyclerView) findViewById(R.id.recyclerview);
        tv_today = (TextView) findViewById(R.id.tv_today);
        tv_date = (TextView) findViewById(R.id.tv_date);
        token = SharedPreferencesUtils.getString(this, "token", "");
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        //设置适配器
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imb_back:
                finish();
                break;
            case R.id.tv_today:
                EventBus.getDefault().post(new MessageEventTime(todayStr));
                tv_today.setTextColor(0xffffd200);
                tv_today.setBackgroundColor(Color.WHITE);
                tv_date.setTextColor(Color.WHITE);
                tv_date.setBackgroundColor(0xffffd200);
                break;
            //日历
            case R.id.tv_date:
                tv_date.setTextColor(0xffffd200);
                tv_date.setBackgroundColor(Color.WHITE);
                tv_today.setTextColor(Color.WHITE);
                tv_today.setBackgroundColor(0xffffd200);
                //通过values和values-v21下的MyDatePickerDialogTheme改变颜色，
                datePickerDialog = new DatePickerDialog(ScanActivity.this, R.style.MyDatePickerDialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int
                            dayOfMonth) {
                        if (monthOfYear <= 9) {
                            mouth1 = "0" + (monthOfYear + 1);
                        } else {
                            mouth1 = String.valueOf(monthOfYear + 1);
                        }
                        if (dayOfMonth <= 9) {
                            day1 = "0" + dayOfMonth;
                        } else {
                            day1 = String.valueOf(dayOfMonth);
                        }
                        dateStr = String.valueOf(year) + "-" + mouth1 + "-" + day1;
                        tv_date.setText(dateStr.substring(5, 10));
                        EventBus.getDefault().post(new MessageEventTime(dateStr + " " + "00:00:00"));
                    }
                }, calender.get(Calendar.YEAR), calender.get(Calendar.MONTH),
                        calender.get(Calendar.DAY_OF_MONTH));
                //设置起始日期和结束日期
                DatePicker datePicker = datePickerDialog.getDatePicker();
                // datePicker.setMinDate();
                datePicker.setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

//                //监听确定按钮
//                datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        datePickerDialog.dismiss();
//                    }
//                });


        }
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
        RequestManager.postJson()
                .setUrl(Url.findallqr0427)
                .addParams("token", token)
                .addParams("createTime", time)
                .addParams("size", String.valueOf(size))
                .addParams("page", String.valueOf(page)).addParams("isPay", isPay)
                .builder().onUI()
                .setCallback(new IRequestBeanListener<ScanBean>() {
                    @Override
                    public void onSuccess(ScanBean baen) {
                        if (baen.getStatus() == 200) {
                            total = baen.getData().getTotal();
                            List<ScanBean.DataBean.RowsBean> rows = baen.getData().getRows();

                            //如果是刷新就清楚之前加载的数据
                            if (isRefrsh) {
                                listAll.clear();
                            }
                            listAll.addAll(rows);
                            builderAdapter(listAll, isRefrsh);
                        }

                    }

                    @Override
                    public void onErr(String s) {

                    }
                });
    }

    /**
     * 刷新Adapter
     */
    private void builderAdapter(ArrayList<ScanBean.DataBean.RowsBean> listAll, Boolean isRefrsh) {
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
    public void Event(MessageEventTime messageEvent) {
        time = messageEvent.getMessage();
        requestData(true, time);
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
