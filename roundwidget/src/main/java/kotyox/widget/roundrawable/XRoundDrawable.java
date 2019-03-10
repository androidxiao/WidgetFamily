package kotyox.widget.roundrawable;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;


/**
 * 可以方便生成圆角矩形/圆形 {@link android.graphics.drawable.Drawable}
 * <p>
 * <ul>
 * <li>使用 {@link #setBgData(ColorStateList)} 设置背景色</li>
 * <li>使用 {@link #setStrokeData(int, ColorStateList)} 设置描边大小、描边颜色</li>
 * <li>使用 {@link #setIsRadiusAdjustBounds(boolean)} 设置圆角大小是否自动适应为 {@link View} 的高度一半，默认为 true</li>
 * <li>字体颜色的设置只对 <code>TextView</code> 控件及其子类有效，<code>ViewGroup</code> 无效</li>
 * </ul>
 * </p>
 */
public class XRoundDrawable extends GradientDrawable {
    /**
     * 圆角大小是否自适应为 View 高度的一半
     */
    private boolean mRadiusAdjustBounds = true;
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

    /**
     * 设置圆角大小是否自动适应为 View 高度的一半
     */
    public void setIsRadiusAdjustBounds(boolean isRadiusAdjustBounds) {
        mRadiusAdjustBounds = isRadiusAdjustBounds;
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

    @Override
    protected void onBoundsChange(Rect r) {
        super.onBoundsChange(r);
        if (mRadiusAdjustBounds) {
            //修改圆角为短边的一半
            setCornerRadius(Math.min(r.width(), r.height()) / 2);
        }
    }

    public void fromAttributeSet(ColorStateList colorBg, ColorStateList colorBorder, int borderWidth, int mRadiusTopLeft, int mRadiusTopRight, int mRadiusBottomLeft, int mRadiusBottomRight, boolean isRadiusAdjustBounds, int mRadius) {
        setBgData(colorBg);
        setStrokeData(borderWidth, colorBorder);
        if (mRadiusTopLeft > 0 || mRadiusTopRight > 0 || mRadiusBottomLeft > 0 || mRadiusBottomRight > 0) {
            float[] radii = new float[]{
                    mRadiusTopLeft, mRadiusTopLeft,
                    mRadiusTopRight, mRadiusTopRight,
                    mRadiusBottomRight, mRadiusBottomRight,
                    mRadiusBottomLeft, mRadiusBottomLeft
            };
            setCornerRadii(radii);
            isRadiusAdjustBounds = false;
        } else {
            setCornerRadius(mRadius);
            if (mRadius > 0) {
                isRadiusAdjustBounds = false;
            }
        }

        setIsRadiusAdjustBounds(isRadiusAdjustBounds);
    }
}
