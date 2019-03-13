package com.widget.family.ui;

import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.widget.family.R;

import kotyox.widget.XRoundButton;
import kotyox.widget.state.XRoundButtonState;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XRoundButton btn = findViewById(R.id.btn3);
        XRoundButtonState buttonState = new XRoundButtonState(btn);
        buttonState.setBg( R.color.c_1aad19)
                .setPressColor( R.color.c_font_press)
                .setBorderWidth(1)
                .setColorBorder(R.color.cardview_dark_background)
                .setFontEnableColor( R.color.cardview_dark_background)
                .setIsRadiusAdjustBounds(true)
                .build();
        StateListDrawable state = (StateListDrawable) btn.getBackground();
//        state.setBg(ContextCompat.getColor(this, R.color.c_1aad19));

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toUi();
            }
        });
    }

    public void toUi() {
        startActivity(new Intent(this, TestActivity.class));
    }
}
