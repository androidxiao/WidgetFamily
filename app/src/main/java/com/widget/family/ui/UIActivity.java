package com.widget.family.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.widget.family.R;

import kotyox.statedrawable.XStateCheckbox;
import kotyox.widget.XRoundButton;
import kotyox.widget.XRoundRelativeLayout;
import kotyox.widget.XRoundTextView;
import kotyox.widget.state.XRoundButtonState;
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
        XRoundRelativeLayout cl = findViewById(R.id.cl);
        cl.setSelected(true);

        final XRoundTextView tv = findViewById(R.id.btn1);
        tv.setSelected(true);
        EzLog.d("aaa--->"+tv.isSelected());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setSelected(false);
                Toast.makeText(UIActivity.this, "aa", Toast.LENGTH_SHORT).show();
            }
        });


        XStateCheckbox ch = findViewById(R.id.cb1);
        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EzLog.d("aaa-->" + isChecked);
                } else {
                    EzLog.d("bbb-->" + isChecked);
                }
            }
        });

//        setBtnStyle();
    }

    private XRoundButton btn;

    private void setBtnStyle() {
        XRoundButtonState state = new XRoundButtonState(btn);
        state.setFontEnableColor(R.color.c_font_normal)
                .setRadius(4)
                .setColorBorder(R.color.colorAccent)
                .setBorderWidth(1)
                .setBg(R.color.bg_gradient)
                .setBottomLeft(1)
                .setEndColor(R.color.colorAccent)
                .setBottomRight(1)
                .setDisableColor(R.color.colorAccent)
                .setFontPressColor(R.color.colorAccent)
                .setGradientOrientation(1)
                .setIsRadiusAdjustBounds(true)
                .setStartColor(R.color.colorAccent)
                .setMiddleColor(R.color.colorAccent)
                .setTopLeft(1)
                .setTopRight(1)
                .setPressColor(R.color.colorAccent)
                .build();
    }
}
