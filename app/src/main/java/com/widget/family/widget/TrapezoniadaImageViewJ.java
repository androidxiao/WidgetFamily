package com.widget.family.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.widget.family.R;

/**
 * Created by wei.
 * Date: 2019-04-27 17:19
 * Description:
 */
public class TrapezoniadaImageViewJ extends AppCompatImageView {

    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    private Path clipPath;
    private Path borderPath;
    private Paint borderPaint;
    private Region clickRegion;
    private RectF clickRect;
    private int start = 1;
    private float distance = 0f;
    private float borderSize = 10f;
    private int borderColor = Color.BLACK;


    public TrapezoniadaImageViewJ(Context context) {
        super(context);
    }

    public TrapezoniadaImageViewJ(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public TrapezoniadaImageViewJ(Context context, AttributeSet attrs,int defStyleAttr) {
        super(context, attrs,defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.TrapezoniadaImageView);
        start = array.getInt(R.styleable.TrapezoniadaImageView_start, 0);
        distance = array.getDimension(R.styleable.TrapezoniadaImageView_distance, 0f);
        borderSize = array.getDimension(R.styleable.TrapezoniadaImageView_borderSize, 0f);
        borderColor = array.getColor(R.styleable.TrapezoniadaImageView_borderColor, Color.BLACK);
        array.recycle();

        borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(borderColor);
        borderPaint.setStrokeWidth(borderSize);
        clipPath = new Path();
        borderPath = new Path();
        clickRect = new RectF();
        clickRegion = new Region();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (clipPath.isEmpty()) {
            super.onDraw(canvas);
            return;
        }
        int save = canvas.save();
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
        canvas.drawPath(borderPath, borderPaint);
        canvas.restoreToCount(save);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            setClipPath();
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        setClipPath();
    }

    private void setClipPath() {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if (width <= 0 || height <= 0 || start == 0) {
            return;
        }

        clipPath.reset();
        borderPath.reset();
        if(start == RIGHT) {
            clipPath.moveTo(0f, 0f);
            clipPath.lineTo(width, 0f);
            clipPath.lineTo(width - distance, height);
            clipPath.lineTo(0f, height);
            borderPath.moveTo(width, 0f);
            borderPath.lineTo(width - distance, height);
        }else if(start == LEFT){
            clipPath.moveTo(distance, 0f);
            clipPath.lineTo(width, 0f);
            clipPath.lineTo(width, height);
            clipPath.lineTo(0f,height);
            borderPath.moveTo(distance, 0f);
            borderPath.lineTo(0f, height);
        }
        clipPath.close();
        borderPath.close();
        clipPath.computeBounds(clickRect, true);
        Region region = new Region((int)clickRect.left,
                (int)clickRect.top,
                (int)clickRect.right,
                (int)clickRect.bottom);
        clickRegion.setPath(clipPath, region);
    }
}
