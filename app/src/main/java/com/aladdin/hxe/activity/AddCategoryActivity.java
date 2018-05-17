package com.aladdin.hxe.activity;

import android.os.Bundle;
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
public class AddCategoryActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_addCategory;
    private TextView tv_saveAddCategory;
    private ImageButton imb_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        initView();
    }

    private void initView() {
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        et_addCategory = (EditText) findViewById(R.id.et_addCategory);
        tv_saveAddCategory = (TextView) findViewById(R.id.tv_saveAddCategory);
        //按键点击事件
        imb_back.setOnClickListener(this);
        tv_saveAddCategory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imb_back:
                finish();
                break;
            case R.id.tv_saveAddCategory:
                String token = SharedPreferencesUtils.getString(this, "token", "");
                String name = et_addCategory.getText().toString().trim();
                if (name.equals("")) {
                    Toast.makeText(this, "品类名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                initEdit(name,  token);
                break;
            default:
                break;
        }
    }
    private void initEdit(String name, String token) {
        RequestManager.postJson()
                .addParams("name", name)
                .addParams("token", token).setUrl(Url.categoryAdd)
                .builder().onUI().setCallback(new IRequestBeanListener<LoginBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(LoginBean baen) {
                if(baen.getStatus().equals("200")){
                    EventBus.getDefault().post(new MessageEventC("C"));
                    Toast.makeText(AddCategoryActivity.this, "添加品类成功", Toast.LENGTH_SHORT).show();
                    AddCategoryActivity.this.finish();
                }else{
                    Toast.makeText(AddCategoryActivity.this, "添加品类失败", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }

}
