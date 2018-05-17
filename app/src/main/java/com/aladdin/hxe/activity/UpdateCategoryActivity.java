package com.aladdin.hxe.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.LoginBean;
import com.aladdin.hxe.bean.MessageEventC;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;

import org.greenrobot.eventbus.EventBus;

public class UpdateCategoryActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton imb_back;
    private EditText et_updateCategory;
    private TextView tv_saveUpdateCategory;
    private String token;
    private int myID;
    private TextView tv_deleteCategory;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_category);
        initView();
        initEvent();

    }

    private void initEvent() {
        //按键点击事件
        imb_back.setOnClickListener(this);
        tv_saveUpdateCategory.setOnClickListener(this);
        tv_deleteCategory.setOnClickListener(this);
    }

    private void initView() {
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        et_updateCategory = (EditText) findViewById(R.id.et_updateCategory);
        tv_saveUpdateCategory = (TextView) findViewById(R.id.tv_saveUpdateCategory);
        tv_deleteCategory = (TextView) findViewById(R.id.tv_deleteCategory);
        //修改、删除 参数
        myID = getIntent().getIntExtra("myID", 0);
        String myNAME = getIntent().getStringExtra("myNAME");
        et_updateCategory.setText(myNAME);
        token = SharedPreferencesUtils.getString(this, "token", "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.imb_back:
                finish();
                break;
            //保存
            case R.id.tv_saveUpdateCategory:
                name = et_updateCategory.getText().toString().trim();
                Log.d("TTTTTT",name);
                if (name.equals("")) {
                    Toast.makeText(this, "品类名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                getData(name, myID, token);
                break;
            case R.id.tv_deleteCategory:
                deleteData(myID, token);
                break;
            default:
                break;
        }
    }

    private void getData(String name, int myID, String token) {
        RequestManager.postJson().addParams("id", String.valueOf(myID))
                .addParams("name", name)
                .addParams("token", token).setUrl(Url.categoryUpdateURL)
                .builder().onUI().setCallback(new IRequestBeanListener<LoginBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(LoginBean baen) {
                if(baen.getStatus().equals("200")){
                    EventBus.getDefault().post(new MessageEventC("C"));
                    Toast.makeText(UpdateCategoryActivity.this, "修改品类成功", Toast.LENGTH_SHORT).show();
                    UpdateCategoryActivity.this.finish();
                }else{
                    return;
                }
            }
        });

    }
    private void deleteData( int myID, String token) {
        RequestManager.postJson().addParams("id", String.valueOf(myID))
                .addParams("token", token).setUrl(Url.categoryDelete)
                .builder().onUI().setCallback(new IRequestBeanListener<LoginBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(LoginBean baen) {
                if(baen.getStatus().equals("200")){
                    EventBus.getDefault().post(new MessageEventC("C"));
                    Toast.makeText(UpdateCategoryActivity.this, "删除品类成功", Toast.LENGTH_SHORT).show();
                    UpdateCategoryActivity.this.finish();
                }else{
                    Toast.makeText(UpdateCategoryActivity.this, "删除失败，请先删除该分类下的菜品", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}