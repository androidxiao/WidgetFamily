package com.widget.family.ui;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.commonutils.kotyox.net.data.Resource;
import com.commonutils.kotyox.net.data.Status;
import com.commonutils.kotyox.utils.EzLog;
import com.widget.family.R;
import com.widget.family.model.HomeListModel;
import com.widget.family.model.viewmodel.HomeListViewModel;
import com.widget.family.widget.TrapezoniadaImageView;
import com.widget.family.widget.TrapezoniadaImageView2;
import com.widget.family.widget.TrapezoniadaImageViewJ;

import java.util.List;

import kotyox.widget.XRoundButton;
import kotyox.widget.state.XRoundButtonState;
import retrofit2.HttpException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TrapezoniadaImageView iv=findViewById(R.id.trIv);
        TrapezoniadaImageView2 iv2=findViewById(R.id.trIv2);
        TrapezoniadaImageViewJ iv3=findViewById(R.id.trIv3);
        TrapezoniadaImageViewJ iv4=findViewById(R.id.trIv4);

//        Glide.with(this).load("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-742105.png").fitCenter().into(iv);
        Glide.with(this).load("https://upload-images.jianshu.io/upload_images/11034617-7361f5beeb566719.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/360/h/240").centerCrop().into(iv);
        Glide.with(this).load("https://upload-images.jianshu.io/upload_images/16027370-7b3aee78d75d5b23.jpeg?imageMogr2/auto-orient/strip|imageView2/1/w/360/h/240").centerCrop().into(iv2);
        Glide.with(this).load("https://upload-images.jianshu.io/upload_images/16027370-7b3aee78d75d5b23.jpeg?imageMogr2/auto-orient/strip|imageView2/1/w/360/h/240").centerCrop().into(iv4);

        Glide.with(this).load("https://upload-images.jianshu.io/upload_images/11034617-7361f5beeb566719.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/360/h/240").centerCrop().into(iv3);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了。。", Toast.LENGTH_SHORT).show();
            }
        });

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

        HomeListViewModel vm = new HomeListViewModel();
        vm.getModels().observe(this, new Observer<Resource<HomeListModel>>() {
            @Override
            public void onChanged(@Nullable Resource<HomeListModel> resource) {
                if (resource.status == Status.SUCCESS) {

                    EzLog.d("size-->"+resource.data.getDatas().size());
                    List<HomeListModel.DatasBean> datas = resource.data.getDatas();
                    for (HomeListModel.DatasBean bean : datas) {
                        EzLog.d("size-->"+bean.getAuthor());
                    }
                } else if (resource.status == Status.ERROR) {
                    EzLog.d("failed--->"+resource.errorMessage+"  throwable--->"+resource.mThrowable.getMessage()+"  cause--->"+resource.mThrowable.getCause()+" localMessage--->"+resource.mThrowable.getLocalizedMessage());
                    if (resource.mThrowable instanceof HttpException) {
                        HttpException exception = (HttpException) resource.mThrowable;
                        switch (exception.code()) {
                            case 400:
                                // Handle code 400
                                EzLog.d("400");
                                break;
                            case 500:
                                EzLog.d("500");
                                // Handle code 500
                                break;
                            case 404:
                                EzLog.d("404");
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        });
    }

    public void toUi() {
        startActivity(new Intent(this, TestActivity.class));
    }
}
