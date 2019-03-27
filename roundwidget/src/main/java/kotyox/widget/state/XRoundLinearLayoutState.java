package kotyox.widget.state;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.lang.ref.WeakReference;

import family.widget.com.roundwidget.R;
import kotyox.widget.roundrawable.XRoundDrawable;

import static kotyoxutils.Px2DpUtil.dp2px;
import static kotyoxutils.XDrawableHelper.colorStateList;
import static kotyoxutils.XDrawableHelper.createStateListDrawable;
import static kotyoxutils.XDrawableHelper.getOrientation;
import static kotyoxutils.XViewHelper.setBackgroundKeepingPadding;

/**
 * Created by wei.
 * Date: 2019/3/10 下午3:11
 * Description:
 */
public class XRoundLinearLayoutState {

    private Context mContext;
    private WeakReference<View> mReference;
    private ColorStateList mColorBg;
    private ColorStateList mColorBorder;
    private int mBorderWidth;
    private boolean mIsRadiusAdjustBounds;
    private int mRadius;
    private int mRadiusTopLeft;
    private int mRadiusTopRight;
    private int mRadiusBottomLeft;
    private int mRadiusBottomRight;
    private ColorStateList mDisableColor;
    private ColorStateList mPressColor;
    private int mStartColor;
    private int mMiddleColor;
    private int mEndColor;
    private XRoundDrawable mEnableDrawable;
    private XRoundDrawable mPressDrawable;
    private XRoundDrawable mDisableDrawable;
    private View mView;
    private StateListDrawable mStateListDrawable;
    private int mGradientOrientation;

    public XRoundLinearLayoutState(View view) {
        mReference = new WeakReference<>(view);
        mEnableDrawable = new XRoundDrawable();
        mPressDrawable = new XRoundDrawable();
        mDisableDrawable = new XRoundDrawable();
        mView = mReference.get();
        mContext = mView.getContext();
    }

    public void fromAttributeSet(Context context , AttributeSet set, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(set, R.styleable.XRoundLinearLayout, defStyleAttr, 0);

        mColorBg = ta.getColorStateList(R.styleable.XRoundLinearLayout_x_backgroundColor);
        mColorBorder = ta.getColorStateList(R.styleable.XRoundLinearLayout_x_borderColor);
        mBorderWidth = ta.getDimensionPixelSize(R.styleable.XRoundLinearLayout_x_borderWidth, 0);
        mIsRadiusAdjustBounds = ta.getBoolean(R.styleable.XRoundLinearLayout_x_isRadiusAdjustBounds, false);
        mRadius = ta.getDimensionPixelSize(R.styleable.XRoundLinearLayout_x_radius, 0);
        mRadiusTopLeft = ta.getDimensionPixelSize(R.styleable.XRoundLinearLayout_x_radiusTopLeft, 0);
        mRadiusTopRight = ta.getDimensionPixelSize(R.styleable.XRoundLinearLayout_x_radiusTopRight, 0);
        mRadiusBottomLeft = ta.getDimensionPixelSize(R.styleable.XRoundLinearLayout_x_radiusBottomLeft, 0);
        mRadiusBottomRight = ta.getDimensionPixelSize(R.styleable.XRoundLinearLayout_x_radiusBottomRight, 0);
        mDisableColor = ta.getColorStateList(R.styleable.XRoundLinearLayout_x_disableColor);
        mPressColor = ta.getColorStateList(R.styleable.XRoundLinearLayout_x_pressColor);
        mStartColor = ta.getColor(R.styleable.XRoundLinearLayout_x_startColor, 0);
        mMiddleColor = ta.getColor(R.styleable.XRoundLinearLayout_x_middleColor, 0);
        mEndColor = ta.getColor(R.styleable.XRoundLinearLayout_x_endColor, 0);
        mGradientOrientation = ta.getInt(R.styleable.XRoundLinearLayout_x_gradientOrientation, -1);
        setDefaultColor();
        build();
    }

    private void setDefaultColor() {
        if (mStartColor != 0) {
            int colors[] = {mStartColor, mMiddleColor, mEndColor};
            mEnableDrawable.setColors(colors);
            mEnableDrawable.setOrientation(getOrientation(mGradientOrientation));
        }

        if (mPressColor == null) {
            mPressColor = mColorBg;
        }
    }

    public XRoundLinearLayoutState setBg(int colorBg) {
        mColorBg = colorStateList(ContextCompat.getColor(mContext, colorBg));
        return this;
    }

    public XRoundLinearLayoutState setColorBorder(int colorBorder) {
        mColorBorder = colorStateList(ContextCompat.getColor(mContext, colorBorder));
        return this;
    }

    public XRoundLinearLayoutState setBorderWidth(int borderWidth) {
        mBorderWidth = dp2px(mContext, borderWidth);
        return this;
    }

    public XRoundLinearLayoutState setIsRadiusAdjustBounds(boolean isRadiusAdjustBounds) {
        mIsRadiusAdjustBounds = isRadiusAdjustBounds;
        return this;
    }

    public XRoundLinearLayoutState setRadius(int radius) {
        mRadius = dp2px(mContext, radius);
        return this;
    }

    public XRoundLinearLayoutState setTopLeft(int topLeft) {
        mRadiusTopLeft = dp2px(mContext, topLeft);
        return this;
    }

    public XRoundLinearLayoutState setTopRight(int topRight) {
        mRadiusTopRight = dp2px(mContext, topRight);
        return this;
    }

    public XRoundLinearLayoutState setBottomLeft(int bottomLeft) {
        mRadiusBottomLeft = dp2px(mContext, bottomLeft);
        return this;
    }

    public XRoundLinearLayoutState setBottomRight(int bottomRight) {
        mRadiusBottomRight = dp2px(mContext, bottomRight);
        return this;
    }

    public XRoundLinearLayoutState setPressColor(int pressColor) {
        mPressColor = colorStateList(ContextCompat.getColor(mContext, pressColor));
        return this;
    }

    public XRoundLinearLayoutState setDisableColor(int disableColor) {
        mDisableColor = colorStateList(ContextCompat.getColor(mContext, disableColor));
        return this;
    }

    public XRoundLinearLayoutState setStartColor(int startColor) {
        mStartColor = ContextCompat.getColor(mContext, startColor);
        return this;
    }

    public XRoundLinearLayoutState setMiddleColor(int middleColor) {
        mMiddleColor = ContextCompat.getColor(mContext, middleColor);
        return this;
    }

    public XRoundLinearLayoutState setEndColor(int endColor) {
        mEndColor = ContextCompat.getColor(mContext, endColor);
        return this;
    }

    public XRoundLinearLayoutState setGradientOrientation(int orientation) {
        mGradientOrientation = orientation;
        return this;
    }

    public XRoundLinearLayoutState build() {
        setDefaultColor();
        mEnableDrawable.fromAttributeSet(mColorBg, mColorBorder, mBorderWidth, mRadiusTopLeft, mRadiusTopRight, mRadiusBottomLeft, mRadiusBottomRight, mIsRadiusAdjustBounds, mRadius);

        mPressDrawable.fromAttributeSet(mPressColor, mColorBorder, mBorderWidth, mRadiusTopLeft, mRadiusTopRight, mRadiusBottomLeft, mRadiusBottomRight, mIsRadiusAdjustBounds, mRadius);

        mDisableDrawable.fromAttributeSet(mDisableColor, mColorBorder, mBorderWidth, mRadiusTopLeft, mRadiusTopRight, mRadiusBottomLeft, mRadiusBottomRight, mIsRadiusAdjustBounds, mRadius);

        mStateListDrawable = createStateListDrawable(mEnableDrawable, mPressDrawable, mDisableDrawable);

        setBackgroundKeepingPadding(mView, mStateListDrawable);

        return this;
    }

}