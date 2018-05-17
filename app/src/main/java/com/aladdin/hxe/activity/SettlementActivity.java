package com.aladdin.hxe.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.BankBean;
import com.aladdin.hxe.bean.BankChallBean;
import com.aladdin.hxe.bean.CityBean;
import com.aladdin.hxe.bean.MyBankBean;
import com.aladdin.hxe.bean.ProvinceBean;
import com.aladdin.hxe.utils.CommonUtils;
import com.aladdin.hxe.utils.SharedPreferencesUtils;

import java.util.List;

public class SettlementActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton imb_back;
    private Button bt_edit;
    private String token;
    private List<ProvinceBean.DataBean> dataProvince;
    private String provinceId;
    private List<CityBean.DataBean> dataCity;
    private String provinceName;
    private String cityName;
    private String cityId;
    private List<BankBean.DataBean> dataBank;
    private int bankId;
    private List<BankChallBean.DataBean> dataBankChall;
    private String bankBranchName;
    private String bankName;
    private TextView tv_bankType;
    private TextView tv_bankChallName;
    private TextView tv_bankName;
    private TextView tv_userName;
    private TextView tv_bankNum;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        bt_edit.setOnClickListener(this);
        imb_back.setOnClickListener(this);
    }

    private void initData() {
        //获取银行卡信息
        Bundle bundle = getIntent().getExtras();
        final MyBankBean myBankBean = (MyBankBean) bundle.getSerializable("baen");
        int accountType = myBankBean.getData().get(0).getAccountType();
        if(accountType!=2){
            tv_bankType.setText("个人");
        }else if(accountType==2){
            tv_bankType.setText("企业");
        }
        tv_bankName.setText(myBankBean.getData().get(0).getOpenBank());
        tv_bankChallName.setText(myBankBean.getData().get(0).getSubBranch());
        tv_userName.setText(myBankBean.getData().get(0).getSettlementAccountName());
        tv_bankNum.setText(myBankBean.getData().get(0).getSettlementAccount());
    }

    private void initView() {
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        bt_edit = (Button) findViewById(R.id.bt_edit);
        tv_bankType = (TextView) findViewById(R.id.tv_bankType);
        tv_bankChallName = (TextView) findViewById(R.id.tv_bankChallName);
        tv_bankName = (TextView) findViewById(R.id.tv_bankName);
        tv_userName = (TextView) findViewById(R.id.tv_userName);
        tv_bankNum = (TextView) findViewById(R.id.tv_bankNum);
        token = SharedPreferencesUtils.getString(this, "token", "");
        userId = SharedPreferencesUtils.getString(SettlementActivity.this, "userId", "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_edit:
                CommonUtils.startActivity(this,AddBankcardActivity.class);
                break;
            case R.id.imb_back:
                finish();
                break;
            default:
                break;
        }
    }
}
