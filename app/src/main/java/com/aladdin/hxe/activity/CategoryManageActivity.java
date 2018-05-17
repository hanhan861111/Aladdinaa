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
import com.aladdin.hxe.adapter.CategoryManagerAdapter;
import com.aladdin.hxe.bean.MessageEventC;
import com.aladdin.hxe.bean.ProductList;
import com.aladdin.hxe.utils.CommonUtils;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class CategoryManageActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView rv_categoryManager;
    private List<ProductList.DataBean> mLeftListDatas;
    private ImageButton imb_back;
    private TextView tv_addCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_manage);
        initView();
        getData();
    }

    private void getData() {
        imb_back.setOnClickListener(this);
        tv_addCategory.setOnClickListener(this);
        requestData();
    }

    private void initView() {
        //注册订阅者
        EventBus.getDefault().register(this);
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        rv_categoryManager = (RecyclerView) findViewById(R.id.rv_categoryManager);
        tv_addCategory = (TextView) findViewById(R.id.tv_addCategory);
        rv_categoryManager.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imb_back:
                finish();
                break;
            case R.id.tv_addCategory:
                CommonUtils.startActivity(this,AddCategoryActivity.class);
                break;
            default:
                break;
        }
    }

    public void requestData() {
        String token = SharedPreferencesUtils.getString(this, "token", "");
        RequestManager.postJson().addParams("token", token).
                setUrl(Url.findallallUrl).builder().onUI().
                setCallback(new IRequestBeanListener<ProductList>() {

                    @Override
                    public void onErr(String s) {
                    }

                    @Override
                    public void onSuccess(final ProductList productList) {
                        /**
                         * 左边列表的数据
                         */
                        mLeftListDatas = productList.getData();
                        //适配器
                        CategoryManagerAdapter categoryManagerAdapter = new CategoryManagerAdapter(CategoryManageActivity.this, mLeftListDatas);
                        rv_categoryManager.setAdapter(categoryManagerAdapter);

                    }
                });


    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEventC messageEvent){
        if(messageEvent.getMessage().equals("C")){
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


