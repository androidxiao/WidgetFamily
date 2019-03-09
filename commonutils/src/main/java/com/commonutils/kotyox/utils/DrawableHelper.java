package com.commonutils.kotyox.utils;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.blankj.utilcode.util.Utils;


/**
 *
 * @see {@link CheckBox#setCompoundDrawables(Drawable, Drawable, Drawable, Drawable)}
 * @author wei
 * Desc: custom drawableLeft/drawablexxxx  size of CheckBox，RadioButton，TextView,Button,etc
 */
public class DrawableHelper {

    private Drawable left = null;
    private Drawable top = null;
    private Drawable right = null;
    private Drawable bottom = null;
    private View view;
    private int leftWSize;
    private int leftHSize;
    private int topWSize;
    private int topHSize;
    private int rightWSize;
    private int rightHSize;
    private int bottomWSize;
    private int bottomHSize;
    private Rect mRectLeft;
    private Rect mRectTop;
    private Rect mRectRight;
    private Rect mRectBottom;
    private Context mContext;
    private static DrawableHelper sHelper;

    private DrawableHelper() {
        mContext = Utils.getApp();
        initReference();
    }

    public static DrawableHelper instance() {
        if (sHelper == null) {
            synchronized (DrawableHelper.class) {
                if (sHelper == null) {
                    sHelper = new DrawableHelper();
                }
            }
        }
        return sHelper;
    }

    private void initReference() {
        mRectLeft = new Rect();
        mRectTop = new Rect();
        mRectRight = new Rect();
        mRectBottom = new Rect();
    }

    public DrawableHelper setView(View view) {
        this.view = view;
        return this;
    }

    public DrawableHelper setDrawableLeft(int left) {
        this.left = ContextCompat.getDrawable(mContext, left);
        return this;
    }

    public DrawableHelper setDrawableLeft(Drawable left) {
        this.left = left;
        return this;
    }

    public DrawableHelper setDrawableTop(int top) {
        this.top = ContextCompat.getDrawable(mContext, top);
        return this;
    }

    public DrawableHelper setDrawableTop(Drawable top) {
        this.top = top;
        return this;
    }

    public DrawableHelper setDrawableRight(int right) {
        this.right = ContextCompat.getDrawable(mContext, right);
        return this;
    }

    public DrawableHelper setDrawableRight(Drawable right) {
        this.right = right;
        return this;
    }

    public DrawableHelper setDrawableBottom(int bottom) {
        this.right = ContextCompat.getDrawable(mContext, bottom);
        return this;
    }

    public DrawableHelper setDrawableBottom(Drawable bottom) {
        this.bottom = bottom;
        return this;
    }

    public DrawableHelper setLeftSize(int w_size, int h_size) {
        leftWSize = w_size;
        leftHSize = h_size;
        return this;
    }

    public DrawableHelper setTopSize(int w_size, int h_size) {
        topWSize = w_size;
        topHSize = h_size;
        return this;
    }

    public DrawableHelper setRightSize(int w_size, int h_size) {
        rightWSize = w_size;
        rightHSize = h_size;
        return this;
    }

    public DrawableHelper setBottomSize(int w_size, int h_size) {
        bottomWSize = w_size;
        bottomHSize = h_size;
        return this;
    }

    public DrawableHelper setAllSize(int w_size, int h_size) {
        leftWSize = w_size;
        leftHSize = h_size;
        topWSize = w_size;
        topHSize = h_size;
        rightWSize = w_size;
        rightHSize = h_size;
        bottomWSize = w_size;
        bottomHSize = h_size;
        return this;
    }

    public DrawableHelper build() {

        if (mContext == null) {
            throw new RuntimeException("Context 没有初始化");
        }

        if (view == null) {
            throw new RuntimeException("view 没有初始化");
        }

        if (left != null && leftWSize > 0) {
            mRectLeft.top = 0;
            mRectLeft.bottom = leftHSize;
            mRectLeft.left = 0;
            mRectLeft.right = leftWSize;
            left.setBounds(mRectLeft);
        }

        if (top != null && topWSize > 0) {
            mRectTop.top = 0;
            mRectTop.bottom = topHSize;
            mRectTop.left = 0;
            mRectTop.right = topWSize;
            top.setBounds(mRectTop);
        }

        if (right != null && rightWSize > 0) {
            mRectRight.top = 0;
            mRectRight.bottom = rightHSize;
            mRectRight.left = 0;
            mRectRight.right = rightWSize;
            right.setBounds(mRectRight);
        }

        if (bottom != null && bottomWSize > 0) {
            mRectBottom.top = 0;
            mRectBottom.bottom = bottomHSize;
            mRectBottom.left = 0;
            mRectBottom.right = bottomWSize;
            bottom.setBounds(mRectBottom);
        }

        if (view instanceof CheckBox) {
            ((CheckBox) view).setCompoundDrawables(left, top, right, bottom);
        } else if (view instanceof RadioButton) {
            ((RadioButton) view).setCompoundDrawables(left, top, right, bottom);
        } else if (view instanceof TextView) {
            ((TextView) view).setCompoundDrawables(left, top, right, bottom);
        } else if (view instanceof Button) {
            ((Button) view).setCompoundDrawables(left, top, right, bottom);
        }

        return this;
    }

    /**
     * tint drawable by colorId
     * @param drawable
     * @param colorId
     * @return
     */
    public static Drawable tintDrawable(Drawable drawable,int colorId) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(Utils.getApp(), colorId));
        return wrappedDrawable;
    }

}
