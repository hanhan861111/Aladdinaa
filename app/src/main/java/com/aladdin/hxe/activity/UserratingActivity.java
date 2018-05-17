package com.aladdin.hxe.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.adapter.UserRatingAdapter;
import com.aladdin.hxe.bean.FindallevaluationBean;
import com.aladdin.hxe.bean.FindallevaluationBean.DataBean.EvaluationlistBean.RowsBean;
import com.aladdin.hxe.manager.RatingBar;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserratingActivity extends BaseActivity implements XRecyclerView.LoadingListener {

    private RatingBar star1;
    private RatingBar star2;
    private ArrayList<RowsBean> listAll = new ArrayList<>();
    private XRecyclerView mRecyclerView;
    private int page = 1;
    private int size = 20;
    private int total;
    private UserRatingAdapter myAdapter = new UserRatingAdapter(this);
    private TextView tv_score;
    private ImageButton imb_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userrating);
        initView();
        initEvent();
    }

    private void initEvent() {
        imb_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initStar(FindallevaluationBean bean) {
        star1.setClickable(false);
        star2.setClickable(false);
        tv_score.setText(bean.getData().getSumscore());
        star1.setStar(Float.parseFloat(bean.getData().getShopscore()));
        star2.setStar(Float.parseFloat(bean.getData().getProductscore()));
    }

    private void initView() {
        star1 = (RatingBar) findViewById(R.id.star1);
        star2 = (RatingBar) findViewById(R.id.star2);
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        tv_score = (TextView) findViewById(R.id.tv_score);
        mRecyclerView = (XRecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
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
     * 刷新Adapter
     */
    private void builderAdapter(ArrayList<RowsBean> listAll, Boolean isRefrsh) {
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
        } else {
            mRecyclerView.setLoadingMoreEnabled(true);
        }

    }

    public void requestData(final Boolean isRefrsh) {
        String token = SharedPreferencesUtils.getString(this, "token", "");
        RequestManager.postJson()
                .setUrl(Url.findallevaluationUrl).addParams("token", token).addParams("size", String.valueOf(size))
                .addParams("page", String.valueOf(page))
                .builder().onUI()
                .setCallback(new IRequestBeanListener<FindallevaluationBean>() {
                    @Override
                    public void onSuccess(FindallevaluationBean bean) {
                        if(bean.getData().getEvaluationlist().getRows().size()<=0){
                            return;
                        }
                        initStar(bean);
                        total = bean.getData().getEvaluationlist().getRows().size();
                        List<RowsBean> rows = bean.getData().getEvaluationlist().getRows();
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
}
