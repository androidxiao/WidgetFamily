package kotyox.roundwidget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xutils.kotyo.XViewHelper;

import java.lang.ref.WeakReference;

import family.widget.com.roundwidget.R;


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
public class XRoundButtonDrawable extends GradientDrawable {
    /**
     * 圆角大小是否自适应为 View 高度的一半
     */
    private boolean mRadiusAdjustBounds = true;
    private ColorStateList mFillColors;
    private int mStrokeWidth = 0;
    private ColorStateList mStrokeColors;
    private boolean mIsTouchPass = true;
    private WeakReference<View> mReference;

    public XRoundButtonDrawable(View view) {
        mReference = new WeakReference<>(view);
    }

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
     * View 的 touch 事件
     */
    public void setTouchListener() {
        mReference.get().setEnabled(isEnable);
        if (isEnable) {
            setColor(mEnableColor);
            setViewTextColor(mReference.get(), mFontEnableColor);
            mReference.get().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    XViewHelper.setBackgroundKeepingPadding(mReference.get(), XRoundButtonDrawable.this);
                    return setColor(mReference.get(), event.getAction());
                }
            });
        } else {
            setColor(mUnEnableColor);
            setViewTextColor(mReference.get(), mFontUnEnableColor);
        }
    }

    private void setViewTextColor(View view, int color) {
        if (view instanceof XRoundButton) {
            ((XRoundButton) view).setTextColor(color);
        } else if (view instanceof XRoundTextView) {
            ((XRoundTextView) view).setTextColor(color);
        }
    }

    //处理按下去的颜色
    private boolean setColor(View view, int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                setColor(mPressColor);
                setViewTextColor(view, mFontPressColor);
                break;
            case MotionEvent.ACTION_UP:
                setColor(mEnableColor);
                if (isEnable) {
                    setViewTextColor(view, mFontEnableColor);
                } else {
                    setViewTextColor(view, mFontUnEnableColor);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                setColor(mEnableColor);
                if (isEnable) {
                    setViewTextColor(view, mFontEnableColor);
                } else {
                    setViewTextColor(view, mFontUnEnableColor);
                }
                break;
        }

        return mIsTouchPass;
    }

    public void setIsTouchPass(boolean isTouchPass) {
        mIsTouchPass = isTouchPass;
    }

    /**
     * 使用配置的样式
     *
     * @return
     */
    public XRoundButtonDrawable build() {
        setTouchListener();
        return this;
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

    public void fromAttributeSet(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XRoundButton, defStyleAttr, 0);
        ColorStateList colorBg = ta.getColorStateList(R.styleable.XRoundButton_x_backgroundColor);
        ColorStateList colorBorder = ta.getColorStateList(R.styleable.XRoundButton_x_borderColor);
        int borderWidth = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_borderWidth, 0);
        boolean isRadiusAdjustBounds = ta.getBoolean(R.styleable.XRoundButton_x_isRadiusAdjustBounds, false);
        int mRadius = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_radius, 0);
        int mRadiusTopLeft = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_radiusTopLeft, 0);
        int mRadiusTopRight = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_radiusTopRight, 0);
        int mRadiusBottomLeft = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_radiusBottomLeft, 0);
        int mRadiusBottomRight = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_radiusBottomRight, 0);
        int enableColor = ta.getColor(R.styleable.XRoundButton_x_enableColor, ContextCompat.getColor(context, R.color.c_transparent));
        int unEnableColor = ta.getColor(R.styleable.XRoundButton_x_unEnableColor, ContextCompat.getColor(context, R.color.c_transparent));
        int pressColor = ta.getColor(R.styleable.XRoundButton_x_pressColor, ContextCompat.getColor(context, R.color.c_transparent));
        boolean isEnable = ta.getBoolean(R.styleable.XRoundButton_x_enable, true);
        int fontEnableColor = ta.getColor(R.styleable.XRoundButton_x_fontEnableColor, ContextCompat.getColor(context, android.R.color.black));
        int fontPressColor = ta.getColor(R.styleable.XRoundButton_x_fontPressColor, ContextCompat.getColor(context, android.R.color.black));
        int fontUnEnableColor = ta.getColor(R.styleable.XRoundButton_x_fontUnEnableColor, ContextCompat.getColor(context, android.R.color.black));

        ta.recycle();
        setBgData(colorBg);
        setStrokeData(borderWidth, colorBorder);
        if (isEnable) {
            setColor(enableColor);
        } else {
            setColor(unEnableColor);
        }
        if (mRadiusTopLeft > 0 || mRadiusTopRight > 0 || mRadiusBottomLeft > 0 || mRadiusBottomRight > 0) {
            float[] radii = new float[]{
                    mRadiusTopLeft, mRadiusTopLeft,
                    mRadiusTopRight, mRadiusTopRight,
                    mRadiusBottomLeft, mRadiusBottomLeft,
                    mRadiusBottomRight, mRadiusBottomRight
            };
            setCornerRadii(radii);
            isRadiusAdjustBounds = false;
        } else {
            setCornerRadius(mRadius);
            if (mRadius > 0) {
                isRadiusAdjustBounds = false;
            }
        }
        mPressColor = pressColor;
        mEnableColor = enableColor;
        mUnEnableColor = unEnableColor;
        mFontPressColor = fontPressColor;
        mFontEnableColor = fontEnableColor;
        mFontUnEnableColor = fontUnEnableColor;
        setIsRadiusAdjustBounds(isRadiusAdjustBounds);
        setEnable(isEnable);
    }


    private int mPressColor;
    private int mEnableColor;
    private int mUnEnableColor;
    private int mFontEnableColor;
    private int mFontPressColor;
    private int mFontUnEnableColor;
    private boolean isEnable;

    public XRoundButtonDrawable setEnable(boolean isEnable) {
        this.isEnable = isEnable;
        return this;
    }

    public boolean getEnable() {
        return isEnable;
    }

    public XRoundButtonDrawable setPressColor(int pressColor) {
        isEnable = true;
        mPressColor = ContextCompat.getColor(mReference.get().getContext(), pressColor);
        return this;
    }

    public XRoundButtonDrawable setEnableColor(int enableColor) {
        mEnableColor = ContextCompat.getColor(mReference.get().getContext(), enableColor);
        isEnable = true;
        return this;
    }

    public XRoundButtonDrawable setUnEnableColor(int unEnableColor) {
        mUnEnableColor = ContextCompat.getColor(mReference.get().getContext(), unEnableColor);
        isEnable = false;
        return this;
    }

    public XRoundButtonDrawable setFontEnableColor(int fontEnableColor) {
        mFontEnableColor = ContextCompat.getColor(mReference.get().getContext(), fontEnableColor);
        isEnable = true;
        return this;
    }

    public XRoundButtonDrawable setFontPressColor(int fontPressColor) {
        mFontPressColor = ContextCompat.getColor(mReference.get().getContext(), fontPressColor);
        isEnable = true;
        return this;
    }

    public XRoundButtonDrawable setFontUnEnableColor(int fontUnEnableColor) {
        mFontUnEnableColor = ContextCompat.getColor(mReference.get().getContext(), fontUnEnableColor);
        isEnable = false;
        return this;
    }

    public int getPressColor() {
        return mPressColor;
    }

    public int getEnableColor() {
        return mEnableColor;
    }

    public int getUnEnableColor() {
        return mUnEnableColor;
    }

    public int getFontEnableColor() {
        return mFontEnableColor;
    }

    public int getFontPressColor() {
        return mFontPressColor;
    }

    public int getFontUnEnableColor() {
        return mFontUnEnableColor;
    }

}
