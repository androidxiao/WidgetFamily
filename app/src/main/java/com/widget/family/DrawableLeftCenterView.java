package com.widget.family;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * Created by wei.
 * Date: 2019/1/14 下午11:33
 * Description:可控制 drawableLeft 和 text 的距离
 */
public class DrawableLeftCenterView extends android.support.v7.widget.AppCompatRadioButton {

    private int mDrawableSize;
    private int mDrawablePadding;

    public DrawableLeftCenterView(Context context) {
        super(context);
    }

    public DrawableLeftCenterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DrawableLeftCenterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DrawableLeftCenterView);
        for (int i = 0; i < array.getIndexCount(); ++i) {
            int attr = array.getIndex(i);
            if (attr == R.styleable.DrawableLeftCenterView_android_drawablePadding) {
                mDrawablePadding = array.getDimensionPixelSize(attr, 0);
            } else if (attr == R.styleable.DrawableLeftCenterView_android_drawableLeft) {
                mDrawableSize = array.getDrawable(attr).getIntrinsicWidth();
            }
        }
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        float textWidth = getPaint().measureText((String) getText());
        canvas.translate((getWidth() - mDrawableSize - textWidth - mDrawablePadding) / 2, 0);
        super.onDraw(canvas);
        canvas.restore();
    }


}
