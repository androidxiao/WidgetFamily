package com.widget.family.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Printer;
import android.view.View;

import com.commonutils.kotyox.widget.SpanStyleText;
import com.widget.family.R;



public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Display "9.5 excellent!"
        final SpanStyleText spanstyletext1 = (SpanStyleText) findViewById(R.id.text);
        spanstyletext1.addStyleCss(new SpanStyleText.StyleCss.Builder(" #明星  ")
                .backgroundColor(ContextCompat.getColor(this,R.color.c_073680))
                .textColor(Color.RED)
                .roundCorner(15)
                .build());
        spanstyletext1.addStyleCss(new SpanStyleText.StyleCss.Builder("当你老了，头白了，睡意昏沉， 当你老了，头白了，睡意昏沉， 当你老了，头白了，睡意昏沉， 当你老了，头白了，睡意昏沉， 当你老了，头白了，睡意昏沉， 当你老了，头白了，睡意昏沉， 当你老了，头白了，睡意昏沉， 当你老了，头白了，睡意昏沉， 当你老了，头白了，睡意昏沉， 当你老了，头白了，睡意昏沉， 当你老了，头白了，睡意昏沉， 当你老了，头白了，睡意昏沉， 当你老了，头白了，睡意昏沉， 当你老了，头白了，睡意昏沉， 当你老了，头白了，睡意昏沉， 当你老了，头白了，睡意昏沉， ").backgroundColor(ContextCompat.getColor(this, R.color.c_800736)).build());
        spanstyletext1.addStyleCss(new SpanStyleText.StyleCss.Builder(" 当你老了，头白了，睡意昏沉， 　　\n" +
                "炉火旁打盹，请取下这部诗歌， 　　\n" +
                "慢慢读，回想你过去眼神的柔和， 　　\n" +
                "回想它们昔日浓重的阴影； 　　\n" +
                "多少人爱你青春欢畅的时辰， 　　\n" +
                "爱慕你的美丽，假意或真心， 　　\n" +
                "只有一个人爱你那朝圣者的灵魂， 　　\n" +
                "爱你衰老了的脸上痛苦的皱纹； 　　\n" +
                "垂下头来，在红光闪耀的炉子旁， 　　\n" +
                "凄然地轻轻诉说那爱情的消逝， 　　\n" +
                "在头顶的山上它缓缓踱着步子， 　　\n" +
                "在一群星星中间隐藏着脸庞。 ")
                .backgroundColor(ContextCompat.getColor(this,R.color.c_DFF1FE))
                .textColor(ContextCompat.getColor(this,R.color.c_800736))
                .style(Typeface.BOLD)
                .build());
        spanstyletext1.display();

        // Display "3.4 horrible!"
        final SpanStyleText spanstyletext2 = (SpanStyleText) findViewById(R.id.text2);
        spanstyletext2.addStyleCss(new SpanStyleText.StyleCss.Builder("  电视剧  ")
                .backgroundColor(Color.parseColor("#800736"))
                .textColor(Color.WHITE)
                .build());
        spanstyletext2.addStyleCss(new SpanStyleText.StyleCss.Builder(" 完结！ ")
                .backgroundColor(Color.parseColor("#fedfe2"))
                .textColor(Color.parseColor("#800736"))
                .style(Typeface.BOLD)
                .build());
        spanstyletext2.display();

        // Display "starting at $420"
        SpanStyleText spanstyletext3 = (SpanStyleText) findViewById(R.id.text3);
        spanstyletext3.addStyleCss(new SpanStyleText.StyleCss.Builder("乒乓球 ")
                .textColor(Color.parseColor("#50AF2C"))
                .build());
        spanstyletext3.addStyleCss(new SpanStyleText.StyleCss.Builder("地表最强！")
                .textColor(Color.parseColor("#50AF2C"))
                .textSizeRelative(1.2f)
                .style(Typeface.BOLD)
                .build());
        spanstyletext3.display();

        // Display "nightly price"
        SpanStyleText spanstyletext4 = (SpanStyleText) findViewById(R.id.text4);
        spanstyletext4.addStyleCss(new SpanStyleText.StyleCss.Builder("nightly price  ")
                .textColor(Color.parseColor("#5F5F5F"))
                .superscript()
                .textSizeRelative(0.9f)
                .style(Typeface.BOLD)
                .build());
        spanstyletext4.addStyleCss(new SpanStyleText.StyleCss.Builder("$256")
                .textColor(Color.parseColor("#5F5F5F"))
                .superscript()
                .strike()
                .textSizeRelative(0.9f)
                .style(Typeface.BOLD)
                .build());
        spanstyletext4.addStyleCss(new SpanStyleText.StyleCss.Builder(" $179")
                .textColor(Color.parseColor("#9E0719"))
                .textSizeRelative(1.5f)
                .style(Typeface.BOLD)
                .build());
        spanstyletext4.display();

        // Display "new York"
        SpanStyleText spanstyletext5 = (SpanStyleText) findViewById(R.id.text5);
        spanstyletext5.addStyleCss(new SpanStyleText.StyleCss.Builder("New York, United States\n")
                .textColor(Color.parseColor("#414141"))
                .style(Typeface.BOLD)
                .build());
        spanstyletext5.addStyleCss(new SpanStyleText.StyleCss.Builder("870 7th Av, New York, Ny\n")
                .textColor(Color.parseColor("#969696"))
                .textSizeRelative(0.9f)
                .style(Typeface.BOLD)
                .build());
        spanstyletext5.addStyleCss(new SpanStyleText.StyleCss.Builder("10019, United States of America")
                .textColor(Color.parseColor("#969696"))
                .textSizeRelative(0.8f)
                .build());
        spanstyletext5.display();

        // Display "Central Park"
        SpanStyleText spanstyletext6 = (SpanStyleText) findViewById(R.id.text6);
        spanstyletext6.addStyleCss(new SpanStyleText.StyleCss.Builder("Central Park, NY\n")
                .textColor(Color.parseColor("#414141"))
                .build());
        spanstyletext6.addStyleCss(new SpanStyleText.StyleCss.Builder("1.2 mi ")
                .textColor(Color.parseColor("#0081E2"))
                .textSizeRelative(0.9f)
                .build());
        spanstyletext6.addStyleCss(new SpanStyleText.StyleCss.Builder("from here")
                .textColor(Color.parseColor("#969696"))
                .textSizeRelative(0.9f)
                .build());
        spanstyletext6.display();

        // Display "Bryant Park Hotel"
        SpanStyleText spanstyletext7 = (SpanStyleText) findViewById(R.id.text7);
        spanstyletext7.addStyleCss(new SpanStyleText.StyleCss.Builder("The Bryant Park Hotel\n")
                .textColor(Color.parseColor("#414141"))
                .build());
        spanstyletext7.addStyleCss(new SpanStyleText.StyleCss.Builder("#6 of 434 ")
                .textColor(Color.parseColor("#0081E2"))
                .build());
        spanstyletext7.addStyleCss(new SpanStyleText.StyleCss.Builder("in New York City\n")
                .textColor(Color.parseColor("#969696"))
                .build());
        spanstyletext7.addStyleCss(new SpanStyleText.StyleCss.Builder("2487 reviews\n")
                .textColor(Color.parseColor("#969696"))
                .build());
        spanstyletext7.addStyleCss(new SpanStyleText.StyleCss.Builder("$540")
                .textColor(Color.parseColor("#F7B53F"))
                .style(Typeface.BOLD)
                .build());
        spanstyletext7.addStyleCss(new SpanStyleText.StyleCss.Builder(" per night")
                .textColor(Color.parseColor("#969696"))
                .build());
        spanstyletext7.display();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpanStyleText.StyleCss aStyleCss = spanstyletext1.getStyleCss(0);
                aStyleCss.setText("  8.8  ");
                aStyleCss.setTextColor(R.color.first_drop_down_text_view_title);
                aStyleCss.roundCorner(2);
                spanstyletext1.display();
            }
        });
    }
}
