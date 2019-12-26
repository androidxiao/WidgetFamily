package com.widget.family.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.widget.family.R;

import kotyox.widget.XRoundButton;
import kotyox.widget.state.XRoundButtonState;

/**
 * Created by wei.
 * Date: 2019-12-17 21:49
 * Description:
 */
public class TestAnyBugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_any_bug);
//        findViewById(R.id.xrcl_container).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(TestAnyBugActivity.this, "aa", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        findViewById(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(TestAnyBugActivity.this, "bb", Toast.LENGTH_SHORT).show();
//            }
//        });
//
        findViewById(R.id.btn_continue_publish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestAnyBugActivity.this, "123", Toast.LENGTH_SHORT).show();
            }
        });
        XRoundButton mBtnPublish = findViewById(R.id.btn_continue_publish);
        XRoundButtonState state = new XRoundButtonState(mBtnPublish);
        state.setBg(R.color.white)
                .setBorderWidth(1)
                .setColorBorder(R.color.c_74AFFE)
                .setFontEnableColor(R.color.c_74AFFE)
                .setRadius(16)
                .build();
    }
}
