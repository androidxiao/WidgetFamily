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
public class XRoundRelativeLayoutState {

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

    public XRoundRelativeLayoutState(View view) {
        mReference = new WeakReference<>(view);
        mEnableDrawable = new XRoundDrawable();
        mPressDrawable = new XRoundDrawable();
        mDisableDrawable = new XRoundDrawable();
        mView = mReference.get();
        mContext = mView.getContext();
    }

    public void fromAttributeSet(Context context, AttributeSet set, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(set, R.styleable.XRoundRelativeLayout, defStyleAttr, 0);

        mColorBg = ta.getColorStateList(R.styleable.XRoundRelativeLayout_x_backgroundColor);
        mColorBorder = ta.getColorStateList(R.styleable.XRoundRelativeLayout_x_borderColor);
        mBorderWidth = ta.getDimensionPixelSize(R.styleable.XRoundRelativeLayout_x_borderWidth, 0);
        mIsRadiusAdjustBounds = ta.getBoolean(R.styleable.XRoundRelativeLayout_x_isRadiusAdjustBounds, false);
        mRadius = ta.getDimensionPixelSize(R.styleable.XRoundRelativeLayout_x_radius, 0);
        mRadiusTopLeft = ta.getDimensionPixelSize(R.styleable.XRoundRelativeLayout_x_radiusTopLeft, 0);
        mRadiusTopRight = ta.getDimensionPixelSize(R.styleable.XRoundRelativeLayout_x_radiusTopRight, 0);
        mRadiusBottomLeft = ta.getDimensionPixelSize(R.styleable.XRoundRelativeLayout_x_radiusBottomLeft, 0);
        mRadiusBottomRight = ta.getDimensionPixelSize(R.styleable.XRoundRelativeLayout_x_radiusBottomRight, 0);
        mDisableColor = ta.getColorStateList(R.styleable.XRoundRelativeLayout_x_disableColor);
        mPressColor = ta.getColorStateList(R.styleable.XRoundRelativeLayout_x_pressColor);
        mStartColor = ta.getColor(R.styleable.XRoundRelativeLayout_x_startColor, 0);
        mMiddleColor = ta.getColor(R.styleable.XRoundRelativeLayout_x_middleColor, 0);
        mEndColor = ta.getColor(R.styleable.XRoundRelativeLayout_x_endColor, 0);
        mGradientOrientation = ta.getInt(R.styleable.XRoundRelativeLayout_x_gradientOrientation, -1);
        build();
    }

    private void setDefaultColor() {
        if (mStartColor != 0) {
            if (mMiddleColor == 0) {
                mMiddleColor = mStartColor;
            }
            int colors[] = {mStartColor, mMiddleColor, mEndColor};
            mEnableDrawable.setColors(colors);
            if (mRadius == 0) {
                float[] radii = new float[]{mRadiusTopLeft, mRadiusTopLeft, mRadiusTopRight, mRadiusTopRight, mRadiusBottomLeft, mRadiusBottomLeft, mRadiusBottomRight, mRadiusBottomRight};
                mEnableDrawable.setCornerRadii(radii);
            } else {
                mEnableDrawable.setCornerRadius(mRadius);
            }
            mEnableDrawable.setOrientation(getOrientation(mGradientOrientation));
        }

        if (mPressColor == null) {
            mPressColor = mColorBg;
        }

        if (mDisableColor == null) {
            mDisableColor = mColorBg;
        }
    }

    public XRoundRelativeLayoutState build() {
        setDefaultColor();

        mEnableDrawable.fromAttributeSet(mColorBg, mColorBorder, mBorderWidth, mRadiusTopLeft, mRadiusTopRight, mRadiusBottomLeft, mRadiusBottomRight, mIsRadiusAdjustBounds, mRadius);

        mPressDrawable.fromAttributeSet(mPressColor, mColorBorder, mBorderWidth, mRadiusTopLeft, mRadiusTopRight, mRadiusBottomLeft, mRadiusBottomRight, mIsRadiusAdjustBounds, mRadius);

        mDisableDrawable.fromAttributeSet(mDisableColor, mColorBorder, mBorderWidth, mRadiusTopLeft, mRadiusTopRight, mRadiusBottomLeft, mRadiusBottomRight, mIsRadiusAdjustBounds, mRadius);

        mStateListDrawable = createStateListDrawable(mEnableDrawable, mPressDrawable, mDisableDrawable);

        setBackgroundKeepingPadding(mView, mStateListDrawable);

        return this;
    }

    public XRoundRelativeLayoutState setBg(int colorBg) {
        mColorBg = colorStateList(ContextCompat.getColor(mContext, colorBg));
        return this;
    }

    public XRoundRelativeLayoutState setColorBorder(int colorBorder) {
        mColorBorder = colorStateList(ContextCompat.getColor(mContext, colorBorder));
        return this;
    }

    public XRoundRelativeLayoutState setBorderWidth(int borderWidth) {
        mBorderWidth = dp2px(mContext, borderWidth);
        return this;
    }

    public XRoundRelativeLayoutState setIsRadiusAdjustBounds(boolean isRadiusAdjustBounds) {
        mIsRadiusAdjustBounds = isRadiusAdjustBounds;
        return this;
    }

    public XRoundRelativeLayoutState setRadius(int radius) {
        mRadius = dp2px(mContext, radius);
        return this;
    }

    public XRoundRelativeLayoutState setTopLeft(int topLeft) {
        mRadiusTopLeft = dp2px(mContext, topLeft);
        return this;
    }

    public XRoundRelativeLayoutState setTopRight(int topRight) {
        mRadiusTopRight = dp2px(mContext, topRight);
        return this;
    }

    public XRoundRelativeLayoutState setBottomLeft(int bottomLeft) {
        mRadiusBottomLeft = dp2px(mContext, bottomLeft);
        return this;
    }

    public XRoundRelativeLayoutState setBottomRight(int bottomRight) {
        mRadiusBottomRight = dp2px(mContext, bottomRight);
        return this;
    }

    public XRoundRelativeLayoutState setPressColor(int pressColor) {
        mPressColor = colorStateList(ContextCompat.getColor(mContext, pressColor));
        return this;
    }

    public XRoundRelativeLayoutState setDisableColor(int disableColor) {
        mDisableColor = colorStateList(ContextCompat.getColor(mContext, disableColor));
        return this;
    }

    public XRoundRelativeLayoutState setStartColor(int startColor) {
        mStartColor = ContextCompat.getColor(mContext, startColor);
        return this;
    }

    public XRoundRelativeLayoutState setMiddleColor(int middleColor) {
        mMiddleColor = ContextCompat.getColor(mContext, middleColor);
        return this;
    }

    public XRoundRelativeLayoutState setEndColor(int endColor) {
        mEndColor = ContextCompat.getColor(mContext, endColor);
        return this;
    }

    public XRoundRelativeLayoutState setGradientOrientation(int orientation) {
        mGradientOrientation = orientation;
        return this;
    }

}
