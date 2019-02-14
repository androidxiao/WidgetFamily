package kotyox.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import family.widget.com.roundwidget.R;


/**
 * Created by wei.
 * Date: 2019/1/15 下午10:05
 * Description:
 * <p>
 * 可以方便生成圆角矩形/圆形 {@link android.graphics.drawable.Drawable}
 * <p>
 * <ul>
 * <li>使用 {@link #setBgData(ColorStateList)} 设置背景色</li>
 * <li>使用 {@link #setStrokeData(int, ColorStateList)} 设置描边大小、描边颜色</li>
 * </ul>
 * </p>
 */
public class XEditTextDrawable extends GradientDrawable {
    private ColorStateList mFillColors;
    private int mStrokeWidth = 0;
    private ColorStateList mStrokeColors;


    /**
     * 设置按钮的背景色(只支持纯色，不支持 Bitmap 或 Drawable)
     * 不支持按压效果
     */
    public void setBgData(@Nullable ColorStateList colors) {
        if (hasNativeStateListAPI()) {
            super.setColor(colors);
        } else {
            mFillColors = colors;
            final int currentColor;
            if (colors == null) {
                currentColor = Color.TRANSPARENT;
            } else {
                currentColor = colors.getColorForState(getState(), 0);
            }
            setColor(currentColor);
        }
    }

    /**
     * 设置按钮的描边粗细和颜色
     *
     * @return
     */
    public void setStrokeData(int width, @Nullable ColorStateList colors) {
        if (hasNativeStateListAPI()) {
            super.setStroke(width, colors);
        } else {
            mStrokeWidth = width;
            mStrokeColors = colors;
            final int currentColors;
            if (colors == null) {
                currentColors = Color.TRANSPARENT;
            } else {
                currentColors = colors.getColorForState(getState(), 0);
            }
            setStroke(width, currentColors);
        }
    }

    private boolean hasNativeStateListAPI() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    @Override
    protected boolean onStateChange(int[] stateSet) {
        boolean superRet = super.onStateChange(stateSet);
        if (mFillColors != null) {
            int color = mFillColors.getColorForState(stateSet, 0);
            setColor(color);
            superRet = true;
        }
        if (mStrokeColors != null) {
            int color = mStrokeColors.getColorForState(stateSet, 0);
            setStroke(mStrokeWidth, color);
            superRet = true;
        }
        return superRet;
    }

    @Override
    public boolean isStateful() {
        return (mFillColors != null && mFillColors.isStateful()) || (mStrokeColors != null && mStrokeColors.isStateful()) || super.isStateful();
    }


    public void fromAttributeSet(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XRoundButton, defStyleAttr, 0);
        ColorStateList colorBg = ta.getColorStateList(R.styleable.XRoundButton_x_backgroundColor);
        ColorStateList colorBorder = ta.getColorStateList(R.styleable.XRoundButton_x_borderColor);
        int borderWidth = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_borderWidth, 0);
        int mRadius = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_radius, 0);
        int mRadiusTopLeft = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_radiusTopLeft, 0);
        int mRadiusTopRight = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_radiusTopRight, 0);
        int mRadiusBottomLeft = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_radiusBottomLeft, 0);
        int mRadiusBottomRight = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_radiusBottomRight, 0);
        int enableColor = ta.getColor(R.styleable.XRoundButton_x_enableColor, ContextCompat.getColor(context, R.color.c_transparent));

        ta.recycle();
        setBgData(colorBg);
        setStrokeData(borderWidth, colorBorder);
        setColor(enableColor);
        if (mRadiusTopLeft > 0 || mRadiusTopRight > 0 || mRadiusBottomLeft > 0 || mRadiusBottomRight > 0) {
            float[] radii = new float[]{
                    mRadiusTopLeft, mRadiusTopLeft,
                    mRadiusTopRight, mRadiusTopRight,
                    mRadiusBottomLeft, mRadiusBottomLeft,
                    mRadiusBottomRight, mRadiusBottomRight
            };
            setCornerRadii(radii);
        } else {
            setCornerRadius(mRadius);
        }

    }


}
