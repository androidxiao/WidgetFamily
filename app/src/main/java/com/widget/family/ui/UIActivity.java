package com.widget.family.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;

import com.widget.family.R;

import kotyox.statedrawable.XStateCheckbox;
import kotyox.widget.XRoundButton;
import kotyoxutils.EzLog;


/**
 * Created by wei.
 * Date: 2019/2/1 下午7:28
 * Description:
 */
public class UIActivity extends AppCompatActivity {

    private XRoundButton mBtn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        mBtn2 = findViewById(R.id.btn2);
//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(UIActivity.this, "aa", Toast.LENGTH_SHORT).show();
//            }
//        });

        XStateCheckbox ch = findViewById(R.id.cb1);
        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EzLog.d("aaa-->"+isChecked);
                }else{
                    EzLog.d("bbb-->"+isChecked);
                }
            }
        });

        setBtnStyle();
    }

    private void setBtnStyle() {
//        XRoundButtonDrawable drawable = (XRoundButtonDrawable) mBtn2.getBackground();
//        mBtn2.setPadding(40, 40, 40, 40);
//        drawable.setEnableColor(R.color.design_default_color_primary)
////        .setPressedColor(R.color.colorAccent)
//                .setDisableColor(R.color.c_disable)
//                .setFontEnableColor(R.color.colorAccent)
//                .setFontDisableColor(R.color.c_ffffff)
//                .setFontPressColor(R.color.colorPrimaryDark)
//                .setEnable(true)
//                .build();
//        drawable.setCornerRadius(20);
//        drawable.setStroke(3, ContextCompat.getColor(this, R.color.colorPrimary));
    }
}
