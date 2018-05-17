package com.aladdin.hxe.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.aladdin.hxe.R;


public class BlueSetActivity extends BaseActivity {

    private TextView tv_blueName;
    private TextView tv_statuse;
    private String name;
    private Button tv_textPrint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_set);
        initView();

    }


    private void initView() {
        tv_blueName = (TextView) findViewById(R.id.tv_blueName);
        tv_statuse = (TextView) findViewById(R.id.tv_statuse);
        tv_textPrint = (Button) findViewById(R.id.tv_textPrint);

    }




}
