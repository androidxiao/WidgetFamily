package com.commonutils.kotyox.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.commonutils.kotyox.interfaces.DrawablePosition;
import com.commonutils.kotyox.interfaces.OnDrawableClickListener;


/**
 * @author wei
 * Data:2018/11/12
 * Desc: designated drawableLeft/drawableTop/drawableRight/drawableBottom has click event{@link #setOnDrawableClickListener}
 */
public class EditDrawableText extends AppCompatEditText {

    private Drawable mDrawableLeft;
    private Drawable mDrawableTop;
    private Drawable mDrawableRight;
    private Drawable mDrawableBottom;
    private int positionX;
    private int positionY;
    private OnDrawableClickListener mOnDrawableClickListener;
    public static final int DEFAULT_EXTRA_AREA = 13;


    public EditDrawableText(Context context) {
        super(context);
    }

    public EditDrawableText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditDrawableText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        if (left != null) {
            mDrawableLeft = left;
        }
        if (top != null) {
            mDrawableTop = top;
        }
        if (right != null) {
            mDrawableRight = right;
        }
        if (bottom != null) {
            mDrawableBottom = bottom;
        }
        super.setCompoundDrawables(left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        positionX = (int) event.getX();
        positionY = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (calLeftTouch(event)) return false;
                Boolean x = calRightTouch(event);
                if (x != null) return x;

                if (calTopTouch(event)) return false;

                if (calBottomTouch(event)) return false;
                return super.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    public void setOnDrawableClickListener(OnDrawableClickListener listener) {
        mOnDrawableClickListener = listener;
    }

    private boolean calBottomTouch(MotionEvent event) {
        Rect bounds;
        if (mDrawableBottom != null) {
            bounds = mDrawableBottom.getBounds();

            if (Math.abs((getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft() - positionX) <= bounds.width() / 2 + DEFAULT_EXTRA_AREA) {
                mOnDrawableClickListener.onClick(DrawablePosition.BOTTOM);
                event.setAction(MotionEvent.ACTION_CANCEL);
                return true;
            }
        }
        return false;
    }

    private boolean calTopTouch(MotionEvent event) {
        Rect bounds;
        if (mDrawableTop != null) {
            bounds = mDrawableTop.getBounds();

            if (Math.abs((getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft() - positionX) <= bounds.width() / 2 + DEFAULT_EXTRA_AREA) {
                mOnDrawableClickListener.onClick(DrawablePosition.TOP);
                event.setAction(MotionEvent.ACTION_CANCEL);
                return true;
            }
        }
        return false;
    }

    private Boolean calRightTouch(MotionEvent event) {
        if (mDrawableRight != null) {
            boolean touchable = positionX > (getWidth() - getTotalPaddingRight()) && (positionX < ((getWidth() - getPaddingRight())));
            if (touchable && mOnDrawableClickListener != null) {
                mOnDrawableClickListener.onClick(DrawablePosition.RIGHT);
                event.setAction(MotionEvent.ACTION_CANCEL);
                return false;
            }

            return super.onTouchEvent(event);
        }
        return null;
    }

    private boolean calLeftTouch(MotionEvent event) {
        Rect bounds;
        if (mDrawableLeft != null) {
            bounds = mDrawableLeft.getBounds();
            int xClickPos;
            int yClickPos;
            int extraClickArea = (int) (DEFAULT_EXTRA_AREA * getResources().getDisplayMetrics().density + 0.5);
            xClickPos = positionX;
            yClickPos = positionY;
            if (!bounds.contains(positionX, positionY)) {
                xClickPos = positionX - extraClickArea;
                yClickPos = positionY - extraClickArea;
                if (xClickPos <= 0) xClickPos = positionX;
                if (yClickPos <= 0) yClickPos = positionY;

                /** Creates square from the smallest value  from x or y*/
                if (xClickPos < yClickPos) yClickPos = xClickPos;
            }

            if (bounds.contains(xClickPos, yClickPos) && mOnDrawableClickListener != null) {
                mOnDrawableClickListener.onClick(DrawablePosition.LEFT);
                event.setAction(MotionEvent.ACTION_CANCEL);
                return true;
            }
        }
        return false;
    }
}
