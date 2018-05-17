package com.aladdin.hxe.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.adapter.TestArrayAdapterA;
import com.aladdin.hxe.bean.LoginBean;
import com.aladdin.hxe.bean.MessageEventC;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class AddTicketActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton imb_back;
    private TextView tv_stage;
    private EditText et_ticketNum;
    private TextView tv_bunding;
    ArrayList<String> listName = new ArrayList<>();
    ArrayList<String> listType = new ArrayList<>();
    ArrayList<String> listKitchenType = new ArrayList<>();
    private Spinner spinnerBrandName;
    private Spinner spinnerTypeName;
    private Spinner spinnerKitchenName;
    private String brandName;
    private String typeName;
    private String token;
    private String kitchenType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);
        initView();
        initEvent();
    }

    private void initEvent() {
        tv_bunding.setOnClickListener(this);
        imb_back.setOnClickListener(this);
        //设置下拉列表风格(这句不些也行)
        //mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBrandName.setAdapter(new TestArrayAdapterA(AddTicketActivity.this, listName));
        spinnerTypeName.setAdapter(new TestArrayAdapterA(AddTicketActivity.this, listType));
        spinnerKitchenName.setAdapter(new TestArrayAdapterA(AddTicketActivity.this, listKitchenType));
        //监听Item选中事件
        spinnerBrandName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                brandName = listName.get(position);
                if( brandName.equals("风驰")){
                   brandName = "FC";
                }else if (brandName.equals("飞印")){
                    brandName="FY";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerTypeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeName = listType.get(position);
                if(typeName.equals("GPRS")){
                    typeName="GP";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerKitchenName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kitchenType = listKitchenType.get(position);
                if( kitchenType.equals("收银")){
                    kitchenType = "SY";
                }else if (kitchenType.equals("后厨")){
                    kitchenType="HC";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initView() {
        token = SharedPreferencesUtils.getString(this, "token", "");
        listName.add("飞印");
        listName.add("风驰");
        listType.add("GPRS");
        listKitchenType.add("收银");
        listKitchenType.add("后厨");
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        tv_stage = (TextView) findViewById(R.id.tv_name);
        et_ticketNum = (EditText) findViewById(R.id.et_ticketNum);
        tv_bunding = (TextView) findViewById(R.id.tv_bunding);
        spinnerBrandName = (Spinner) findViewById(R.id.spinnerBrandName);
        spinnerTypeName = (Spinner) findViewById(R.id.spinnerTypeName);
        spinnerKitchenName = (Spinner) findViewById(R.id.spinnerKitchenName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bunding:
                String ticketNum = et_ticketNum.getText().toString().trim();
                if(ticketNum.equals("")){
                    Toast.makeText(AddTicketActivity.this,"票机编号不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    bindTicket(ticketNum);
                }

                break;
            case R.id.imb_back:
                finish();
                break;
            default:
                break;
        }
    }
    private void bindTicket(String ticketNum) {
        RequestManager.postJson()
                .addParams("deviceNo", ticketNum)
                .addParams("brand",brandName)
                .addParams("name",kitchenType)
                .addParams("type",typeName)
                .addParams("token", token).setUrl(Url.bindTicketURL)
                .builder().onUI().setCallback(new IRequestBeanListener<LoginBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(LoginBean baen) {
                if(baen.getStatus().equals("200")){
                    EventBus.getDefault().post(new MessageEventC("UNBIND"));
                    Log.d("AAAAAAA",kitchenType);
                    Toast.makeText(AddTicketActivity.this, "绑定票机成功", Toast.LENGTH_SHORT).show();
                    AddTicketActivity.this.finish();
                }else{
                    Toast.makeText(AddTicketActivity.this, "绑定票机失败,请检查类型", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }

}
