package com.widget.family.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.widget.family.R;

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
        findViewById(R.id.xrcl_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestAnyBugActivity.this, "aa", Toast.LENGTH_SHORT).show();
            }
        });
        
        findViewById(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestAnyBugActivity.this, "bb", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
