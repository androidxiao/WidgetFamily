package kotyox.widget.state;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import family.widget.com.roundwidget.R;
import kotyox.widget.roundrawable.XRoundDrawable;

import static kotyoxutils.Px2DpUtil.dp2px;
import static kotyoxutils.XDrawableHelper.colorStateList;
import static kotyoxutils.XDrawableHelper.createColorStateList;
import static kotyoxutils.XDrawableHelper.createStateListDrawable;
import static kotyoxutils.XDrawableHelper.getOrientation;
import static kotyoxutils.XViewHelper.setBackgroundKeepingPadding;

/**
 * Created by wei.
 * Date: 2019/3/10 下午3:11
 * Description:
 */
public class XRoundTextViewState {

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
    private int mFontEnableColor;
    private int mFontPressColor;
    private int mFontDisableColor;
    private int mStartColor;
    private int mMiddleColor;
    private int mEndColor;
    private XRoundDrawable mEnableDrawable;
    private XRoundDrawable mPressDrawable;
    private XRoundDrawable mDisableDrawable;
    private View mView;
    private StateListDrawable mStateListDrawable;
    private int mGradientOrientation;

    public XRoundTextViewState(View view) {
        mReference = new WeakReference<>(view);
        mEnableDrawable = new XRoundDrawable();
        mPressDrawable = new XRoundDrawable();
        mDisableDrawable = new XRoundDrawable();
        mView = mReference.get();
        mContext = mView.getContext();
    }

    public void fromAttributeSet(Context context, AttributeSet set, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(set, R.styleable.XRoundTextView, defStyleAttr, 0);

        mColorBg = ta.getColorStateList(R.styleable.XRoundTextView_x_backgroundColor);
        mColorBorder = ta.getColorStateList(R.styleable.XRoundTextView_x_borderColor);
        mBorderWidth = ta.getDimensionPixelSize(R.styleable.XRoundTextView_x_borderWidth, 0);
        mIsRadiusAdjustBounds = ta.getBoolean(R.styleable.XRoundTextView_x_isRadiusAdjustBounds, false);
        mRadius = ta.getDimensionPixelSize(R.styleable.XRoundTextView_x_radius, 0);
        mRadiusTopLeft = ta.getDimensionPixelSize(R.styleable.XRoundTextView_x_radiusTopLeft, 0);
        mRadiusTopRight = ta.getDimensionPixelSize(R.styleable.XRoundTextView_x_radiusTopRight, 0);
        mRadiusBottomLeft = ta.getDimensionPixelSize(R.styleable.XRoundTextView_x_radiusBottomLeft, 0);
        mRadiusBottomRight = ta.getDimensionPixelSize(R.styleable.XRoundTextView_x_radiusBottomRight, 0);
        mDisableColor = ta.getColorStateList(R.styleable.XRoundTextView_x_disableColor);
        mPressColor = ta.getColorStateList(R.styleable.XRoundTextView_x_pressColor);
        mFontEnableColor = ta.getColor(R.styleable.XRoundTextView_x_fontEnableColor, 0);
        mFontPressColor = ta.getColor(R.styleable.XRoundTextView_x_fontPressColor, 0);
        mFontDisableColor = ta.getColor(R.styleable.XRoundTextView_x_fontDisableColor, 0);
        mStartColor = ta.getColor(R.styleable.XRoundTextView_x_startColor, 0);
        mMiddleColor = ta.getColor(R.styleable.XRoundTextView_x_middleColor, 0);
        mEndColor = ta.getColor(R.styleable.XRoundTextView_x_endColor, 0);
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

        if (mFontPressColor == 0) {
            mFontPressColor = mFontEnableColor;
        }
    }

    public XRoundTextViewState build() {
        setDefaultColor();
        mEnableDrawable.fromAttributeSet(mColorBg, mColorBorder, mBorderWidth, mRadiusTopLeft, mRadiusTopRight, mRadiusBottomLeft, mRadiusBottomRight, mIsRadiusAdjustBounds, mRadius);

        mPressDrawable.fromAttributeSet(mPressColor, mColorBorder, mBorderWidth, mRadiusTopLeft, mRadiusTopRight, mRadiusBottomLeft, mRadiusBottomRight, mIsRadiusAdjustBounds, mRadius);

        mDisableDrawable.fromAttributeSet(mDisableColor, mColorBorder, mBorderWidth, mRadiusTopLeft, mRadiusTopRight, mRadiusBottomLeft, mRadiusBottomRight, mIsRadiusAdjustBounds, mRadius);

        mStateListDrawable = createStateListDrawable(mEnableDrawable, mPressDrawable, mDisableDrawable);

        setBackgroundKeepingPadding(mView, mStateListDrawable);

        if (mView instanceof TextView) {
            ((TextView) mView).setTextColor(createColorStateList(mFontEnableColor, mFontPressColor, mFontEnableColor, mFontDisableColor));
        }
        return this;
    }


    public XRoundTextViewState setBg(int colorBg) {
        mColorBg = colorStateList(ContextCompat.getColor(mContext, colorBg));
        return this;
    }

    public XRoundTextViewState setColorBorder(int colorBorder) {
        mColorBorder = colorStateList(ContextCompat.getColor(mContext, colorBorder));
        return this;
    }

    public XRoundTextViewState setBorderWidth(int borderWidth) {
        mBorderWidth = dp2px(mContext, borderWidth);
        return this;
    }

    public XRoundTextViewState setIsRadiusAdjustBounds(boolean isRadiusAdjustBounds) {
        mIsRadiusAdjustBounds = isRadiusAdjustBounds;
        return this;
    }

    public XRoundTextViewState setRadius(int radius) {
        mRadius = dp2px(mContext, radius);
        return this;
    }

    public XRoundTextViewState setTopLeft(int topLeft) {
        mRadiusTopLeft = dp2px(mContext, topLeft);
        return this;
    }

    public XRoundTextViewState setTopRight(int topRight) {
        mRadiusTopRight = dp2px(mContext, topRight);
        return this;
    }

    public XRoundTextViewState setBottomLeft(int bottomLeft) {
        mRadiusBottomLeft = dp2px(mContext, bottomLeft);
        return this;
    }

    public XRoundTextViewState setBottomRight(int bottomRight) {
        mRadiusBottomRight = dp2px(mContext, bottomRight);
        return this;
    }

    public XRoundTextViewState setPressColor(int pressColor) {
        mPressColor = colorStateList(ContextCompat.getColor(mContext, pressColor));
        return this;
    }

    public XRoundTextViewState setDisableColor(int disableColor) {
        mDisableColor = colorStateList(ContextCompat.getColor(mContext, disableColor));
        return this;
    }

    public XRoundTextViewState setFontEnableColor(int fontEnableColor) {
        mFontEnableColor = ContextCompat.getColor(mContext, fontEnableColor);
        return this;
    }

    public XRoundTextViewState setFontPressColor(int fontPressColor) {
        mFontPressColor = ContextCompat.getColor(mContext, fontPressColor);
        return this;
    }

    public XRoundTextViewState setFontDisableColor(int fontDisableColor) {
        mFontDisableColor = ContextCompat.getColor(mContext, fontDisableColor);
        return this;
    }

    public XRoundTextViewState setStartColor(int startColor) {
        mStartColor = ContextCompat.getColor(mContext, startColor);
        return this;
    }

    public XRoundTextViewState setMiddleColor(int middleColor) {
        mMiddleColor = ContextCompat.getColor(mContext, middleColor);
        return this;
    }

    public XRoundTextViewState setEndColor(int endColor) {
        mEndColor = ContextCompat.getColor(mContext, endColor);
        return this;
    }

    public XRoundTextViewState setGradientOrientation(int orientation) {
        mGradientOrientation = orientation;
        return this;
    }

}
