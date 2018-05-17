package com.aladdin.hxe.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.adapter.SpinnerAdapterBank;
import com.aladdin.hxe.adapter.SpinnerAdapterBankChall;
import com.aladdin.hxe.adapter.SpinnerAdapterCity;
import com.aladdin.hxe.adapter.SpinnerAdapterProvince;
import com.aladdin.hxe.adapter.TestArrayAdapterA;
import com.aladdin.hxe.bean.BankBean;
import com.aladdin.hxe.bean.BankChallBean;
import com.aladdin.hxe.bean.CityBean;
import com.aladdin.hxe.bean.LoginBean;
import com.aladdin.hxe.bean.ProvinceBean;
import com.aladdin.hxe.utils.PhoneUtils;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;

import java.util.ArrayList;
import java.util.List;


public class AddBankcardActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton imb_back;
    private Spinner spinner1, spinner2, spinner3, spinner4, spinner5;
    private EditText et_bankCardNum;
    private EditText et_bankCardName;
    private String token;
    private String userId;
    private List<BankChallBean.DataBean> dataBankChall;
    private List<BankBean.DataBean> dataBank;
    private List<ProvinceBean.DataBean> dataProvince;
    private List<CityBean.DataBean> dataCity;
    private String cityName;
    private String cityId;
    private int bankId;
    private String bankName;
    private String bankBranchName;
    private String provinceId;
    private String provinceName;
    private Button bt_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bankcard);
        initView();
        initEvent();
    }

    private void initView() {
        token = SharedPreferencesUtils.getString(this, "token", "");
        userId = SharedPreferencesUtils.getString(AddBankcardActivity.this, "userId", "");
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner5 = (Spinner) findViewById(R.id.spinner5);
        bt_submit = (Button) findViewById(R.id.bt_submit);
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        et_bankCardNum = (EditText) findViewById(R.id.et_bankCardNum);
        et_bankCardName = (EditText) findViewById(R.id.et_bankCardName);
    }

    private void initEvent() {
        imb_back.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
        loadAccountType();
        //加载省份列表
        loadProvince();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imb_back:
                finish();
                break;
            case R.id.bt_submit:
                String bankCardName = et_bankCardName.getText().toString().trim();
                String bankCardNum = et_bankCardNum.getText().toString().trim();
                if ("".equals(bankCardName) || "".equals(bankCardNum)) {
                    Toast.makeText(AddBankcardActivity.this, "持卡人姓名或银行卡号不能为空", Toast.LENGTH_SHORT).show();
                } else if (PhoneUtils.matchLuhn(bankCardNum)==false) {
                    Toast.makeText(AddBankcardActivity.this, "您输入的银行卡号有误", Toast.LENGTH_SHORT).show();
                } else {
                    addBankAccount(bankCardName, bankCardNum);
                }
                break;
            default:
                break;
        }
    }

    private void addBankAccount(String bankCardName, String bankCardNum) {
        RequestManager.postJson()
                .addParams("token", token)
                .addParams("openBank", bankName)
                .addParams("subBranch", bankBranchName)
                .addParams("settlementAccount", bankCardNum)
                .addParams("settlementAccountName", bankCardName)
                .addParams("sys_user_id", userId)
                .setUrl(Url.addBankAccountURL).builder().onUI()
                .setCallback(new IRequestBeanListener<LoginBean>() {
                    @Override
                    public void onErr(String s) {

                    }

                    @Override
                    public void onSuccess(LoginBean baen) {
                        if (baen.getStatus().equals("200")) {
                            Toast.makeText(AddBankcardActivity.this, "添加银行卡成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(AddBankcardActivity.this, "添加银行卡失败，清查看银行卡信息", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void loadAccountType() {
        ArrayList<String> listName = new ArrayList<>();
        listName.add("个人");
        listName.add("企业");
        TestArrayAdapterA testArrayAdapterA = new TestArrayAdapterA(this, listName);
        spinner5.setAdapter(testArrayAdapterA);
    }

    private void loadBankChall() {
        RequestManager.postJson()
                .addParams("token", token)
                .addParams("cityId", cityId)
                .addParams("provinceId", provinceId)
                .addParams("parentBankId", String.valueOf(bankId))
                .setUrl(Url.findbankBranchallURL)
                .builder().onUI().setCallback(new IRequestBeanListener<BankChallBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(BankChallBean baen) {
                if (baen.getStatus() != 200) {
                    return;
                }
                dataBankChall = baen.getData();
                SpinnerAdapterBankChall adapterOne = new SpinnerAdapterBankChall(AddBankcardActivity.this, dataBankChall, R.layout.item_spinner);
                spinner4.setAdapter(adapterOne);
                //设置spinner3的监听事件
                spinner4.setOnItemSelectedListener(new Spinner4ClickListener());
            }
        });
    }

    private void loadBank() {
        RequestManager.postJson()
                .addParams("token", token)
                .setUrl(Url.findbankcodeallURL)
                .builder().onUI().setCallback(new IRequestBeanListener<BankBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(BankBean baen) {
                if (baen.getStatus() != 200) {
                    return;
                }
                dataBank = baen.getData();
                SpinnerAdapterBank adapterOne = new SpinnerAdapterBank(AddBankcardActivity.this, dataBank, R.layout.item_spinner);
                spinner3.setAdapter(adapterOne);
                //设置spinner3的监听事件
                spinner3.setOnItemSelectedListener(new Spinner3ClickListener());

            }
        });
    }

    /**
     * 加载省份列表
     */
    public void loadProvince() {
        RequestManager.postJson()
                .addParams("token", token)
                .setUrl(Url.findprovinceallURL)
                .builder().onUI().setCallback(new IRequestBeanListener<ProvinceBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(ProvinceBean baen) {
                if (baen.getStatus() != 200) {
                    return;
                }
                dataProvince = baen.getData();
                SpinnerAdapterProvince adapterOne = new SpinnerAdapterProvince(AddBankcardActivity.this, dataProvince, R.layout.item_spinner);
                spinner1.setAdapter(adapterOne);
                //设置spinner1的监听事件
                spinner1.setOnItemSelectedListener(new Spinner1ClickListener());
            }
        });

    }

    /**
     * 加载城市列表
     */
    public void loadCity(String provinceId) {
        RequestManager.postJson()
                .addParams("token", token)
                .addParams("cityUpId", provinceId)
                .setUrl(Url.findcitybyprovURL)
                .builder().onUI().setCallback(new IRequestBeanListener<CityBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(CityBean baen) {
                if (baen.getStatus() != 200) {
                    return;
                }
                dataCity = baen.getData();
                SpinnerAdapterCity modelTwo = new SpinnerAdapterCity(AddBankcardActivity.this, dataCity, R.layout.item_spinner);
                spinner2.setAdapter(modelTwo);

            }
        });

    }

    /**
     * Spinner1点击事件
     */
    public class Spinner1ClickListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = String.valueOf(adapterView.getItemAtPosition(i));
            //判断是否选择城市，如果没有选择那么就隐藏Spinner2，否则显示Spinner2下拉框
//            if (str.equals("请选择")) {
//                spinner2.setVisibility(View.INVISIBLE);
//            } else {
//                spinner2.setVisibility(View.VISIBLE);
//
//                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
//                spinner2.setSelection(0);
//            }
            // spinner2.setSelection(0);
            provinceId = dataProvince.get(i).getProvinceId();
            provinceName = dataProvince.get(i).getProvinceName();
            //加载城市列表
            loadCity(provinceId);
            //设置spinner2的监听事件
            spinner2.setOnItemSelectedListener(new Spinner2ClickListener());


        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    /**
     * Spinner2点击事件
     */
    public class Spinner2ClickListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            cityName = dataCity.get(i).getCityName();
            cityId = dataCity.get(i).getCityId();
            //加载银行列表
            loadBank();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    /**
     * Spinner3点击事件
     */
    public class Spinner3ClickListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            bankId = dataBank.get(i).getBankId();
            bankName = dataBank.get(i).getBankName();
            //加载支行列表
            loadBankChall();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    /**
     * Spinner4点击事件
     */
    public class Spinner4ClickListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            bankBranchName = dataBankChall.get(i).getBankBranchName();

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}
