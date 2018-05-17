package com.aladdin.hxe.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.aladdin.hxe.R;

public class MessageActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initView();
        initEvent();
    }

    private void initEvent() {
        img_back.setOnClickListener(this);
    }

    private void initView() {
        img_back = (ImageButton) findViewById(R.id.img_back);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
