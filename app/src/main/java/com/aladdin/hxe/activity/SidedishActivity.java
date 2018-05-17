package com.aladdin.hxe.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.aladdin.hxe.R;

public class SidedishActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton imb_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidedish);
        initView();
        initEvent();
    }

    private void initEvent() {
        imb_back.setOnClickListener(this);
    }

    private void initView() {
        imb_back = (ImageButton) findViewById(R.id.imb_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imb_back:
                finish();
                break;
        }
    }
}