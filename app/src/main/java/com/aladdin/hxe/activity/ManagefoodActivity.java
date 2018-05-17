package com.aladdin.hxe.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.adapter.LeftListAdapter;
import com.aladdin.hxe.adapter.RightListAdapter;
import com.aladdin.hxe.bean.MessageEventC;
import com.aladdin.hxe.bean.ProductList;
import com.aladdin.hxe.interfaces.OnItemClickListener;
import com.aladdin.hxe.utils.CommonUtils;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


public class ManagefoodActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mLeftList;
    private RecyclerView mRightList;
    private String mCurrentType;
    private List<ProductList.DataBean> mLeftListDatas;
    private LeftListAdapter mLeftListAdapter;
    private List<ProductList.DataBean.ProductBean> mRightListDatas;
    private RightListAdapter rightListAdapter;
    private TextView tv_goods;
    private TextView tv_category;
    private ImageButton imb_back;
    private String token;
    private LinearLayoutManager leftLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managefood);
        initView();
        initData();
    }

    public void requestData() {
        token = SharedPreferencesUtils.getString(this, "token", "");
        RequestManager.postJson().addParams("token", token).
                setUrl(Url.findallallUrl).builder().onUI().
                setCallback(new IRequestBeanListener<ProductList>() {

                    @Override
                    public void onErr(String s) {
                    }

                    @Override
                    public void onSuccess(final ProductList productList) {
                        if(productList.getStatus()==200){
                            /**
                             * 左边列表的数据
                             */
                            if(productList.getData().size()<=0){
                                return;
                            }
                            mLeftListDatas = productList.getData();
                            mLeftListAdapter = new LeftListAdapter(mLeftListDatas);
                            mLeftList.setAdapter(mLeftListAdapter);
                            /**
                             * 右边列表的数据
                             */
                            List<ProductList.DataBean.ProductBean> product = productList.getData().get(0).getProduct();
                            rightListAdapter = new RightListAdapter(ManagefoodActivity.this, product);
                            mRightList.setAdapter(rightListAdapter);
                            /**
                             *  左边条目点击事件
                             **/
                            mLeftListAdapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    List<ProductList.DataBean.ProductBean> product = productList.getData().get(position).getProduct();
                                    rightListAdapter = new RightListAdapter(ManagefoodActivity.this, product);
                                    mRightList.setAdapter(rightListAdapter);
                                }


                            });

                            //右边的recyclerview点击事件
                            rightListAdapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
//                                goodsStatusId = mRightListDatas.get(position).getId();
//                                status = mRightListDatas.get(position).getStatus();
                                }


                            });
                        }else{
                            return;
                        }

                    }
                });

    }

    private void initView() {
        //注册订阅者
        EventBus.getDefault().register(this);
        mLeftList = (RecyclerView) findViewById(R.id.left_list);
        mRightList = (RecyclerView) findViewById(R.id.right_list);
        tv_goods = (TextView) findViewById(R.id.tv_goods);
        tv_category = (TextView) findViewById(R.id.tv_category);
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        leftLinearLayoutManager = new LinearLayoutManager(this);
        mLeftList.setLayoutManager(leftLinearLayoutManager);
        final LinearLayoutManager rightLinearLayoutManager = new LinearLayoutManager(this);
        mRightList.setLayoutManager(rightLinearLayoutManager);
        //按键点击事件
        imb_back.setOnClickListener(this);
        tv_category.setOnClickListener(this);
        tv_goods.setOnClickListener(this);
    }

    private void initData() {
        requestData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //分类管理
            case R.id.tv_category:
                CommonUtils.startActivity(ManagefoodActivity.this, CategoryManageActivity.class);
                break;
            //上架商品
            case R.id.tv_goods:
            CommonUtils.startActivity(ManagefoodActivity.this,AddFoodActivity.class);
                break;
            //返回
            case R.id.imb_back:
                finish();
                break;
            default:
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEventC messageEvent) {
        if (messageEvent.getMessage().equals("C")) {
            requestData();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

}
