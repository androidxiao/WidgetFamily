package kotyox.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import kotyox.alpha.XAlphaLinearLayout;


/**
 * Created by wei.
 * Date: 2019/1/7 下午10:38
 * Description:
 */
public class XLinearLayout extends XAlphaLinearLayout implements XLayout {

    private XLayoutHelper mLayoutHelper;

    public XLinearLayout(@NonNull Context context) {
        this(context, null);
    }

    public XLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mLayoutHelper = new XLayoutHelper(context, attrs, 0, this);
        setChangeAlphaWhenDisable(false);
        setChangeAlphaWhenPress(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = mLayoutHelper.getMeasuredWidthSpec(widthMeasureSpec);
        heightMeasureSpec = mLayoutHelper.getMeasuredHeightSpec(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int minW = mLayoutHelper.handleMiniWidth(widthMeasureSpec, getMeasuredWidth());
        int minH = mLayoutHelper.handleMiniHeight(heightMeasureSpec, getMeasuredHeight());
        if (widthMeasureSpec != minW || heightMeasureSpec != minH) {
            super.onMeasure(minW, minH);
        }
    }

    @Override
    public void updateTopDivider(int topInsetLeft, int topInsetRight, int topDividerHeight, int topDividerColor) {
        mLayoutHelper.updateTopDivider(topInsetLeft, topInsetRight, topDividerHeight, topDividerColor);
        invalidate();
    }

    @Override
    public void updateBottomDivider(int bottomInsetLeft, int bottomInsetRight, int bottomDividerHeight, int bottomDividerColor) {
        mLayoutHelper.updateBottomDivider(bottomInsetLeft, bottomInsetRight, bottomDividerHeight, bottomDividerColor);
        invalidate();
    }

    @Override
    public void updateLeftDivider(int leftInsetTop, int leftInsetBottom, int leftDividerWidth, int leftDividerColor) {
        mLayoutHelper.updateLeftDivider(leftInsetTop, leftInsetBottom, leftDividerWidth, leftDividerColor);
        invalidate();
    }

    @Override
    public void updateRightDivider(int rightInsetTop, int rightInsetBottom, int rightDividerWidth, int rightDividerColor) {
        mLayoutHelper.updateRightDivider(rightInsetTop, rightInsetBottom, rightDividerWidth, rightDividerColor);
        invalidate();
    }

    @Override
    public void onlyShowTopDivider(int topInsetLeft, int topInsetRight,
                                   int topDividerHeight, int topDividerColor) {
        mLayoutHelper.onlyShowTopDivider(topInsetLeft, topInsetRight, topDividerHeight, topDividerColor);
        invalidate();
    }

    @Override
    public void onlyShowBottomDivider(int bottomInsetLeft, int bottomInsetRight,
                                      int bottomDividerHeight, int bottomDividerColor) {
        mLayoutHelper.onlyShowBottomDivider(bottomInsetLeft, bottomInsetRight, bottomDividerHeight, bottomDividerColor);
        invalidate();
    }

    @Override
    public void onlyShowLeftDivider(int leftInsetTop, int leftInsetBottom, int leftDividerWidth, int leftDividerColor) {
        mLayoutHelper.onlyShowLeftDivider(leftInsetTop, leftInsetBottom, leftDividerWidth, leftDividerColor);
        invalidate();
    }

    @Override
    public void onlyShowRightDivider(int rightInsetTop, int rightInsetBottom, int rightDividerWidth, int rightDividerColor) {
        mLayoutHelper.onlyShowRightDivider(rightInsetTop, rightInsetBottom, rightDividerWidth, rightDividerColor);
        invalidate();
    }


    @Override
    public void setTopDividerAlpha(int dividerAlpha) {
        mLayoutHelper.setTopDividerAlpha(dividerAlpha);
        invalidate();
    }

    @Override
    public void setBottomDividerAlpha(int dividerAlpha) {
        mLayoutHelper.setBottomDividerAlpha(dividerAlpha);
        invalidate();
    }

    @Override
    public void setLeftDividerAlpha(int dividerAlpha) {
        mLayoutHelper.setLeftDividerAlpha(dividerAlpha);
        invalidate();
    }

    @Override
    public void setRightDividerAlpha(int dividerAlpha) {
        mLayoutHelper.setRightDividerAlpha(dividerAlpha);
        invalidate();
    }

    @Override
    public void setRadiusAndShadow(int radius, int shadowElevation, final float shadowAlpha) {
        mLayoutHelper.setRadiusAndShadow(radius, shadowElevation, shadowAlpha);
    }

    @Override
    public void setRadiusAndShadow(int radius, @HideRadiusSide int hideRadiusSide, int shadowElevation, final float shadowAlpha) {
        mLayoutHelper.setRadiusAndShadow(radius, hideRadiusSide, shadowElevation, shadowAlpha);
    }

    @Override
    public void setRadiusAndShadow(int radius, int hideRadiusSide, int shadowElevation, int shadowColor, float shadowAlpha) {
        mLayoutHelper.setRadiusAndShadow(radius, hideRadiusSide, shadowElevation, shadowColor, shadowAlpha);
    }

    @Override
    public void setRadius(int radius) {
        mLayoutHelper.setRadius(radius);
    }

    @Override
    public void setRadius(int radius, @HideRadiusSide int hideRadiusSide) {
        mLayoutHelper.setRadius(radius, hideRadiusSide);
    }

    @Override
    public int getRadius() {
        return mLayoutHelper.getRadius();
    }

    @Override
    public void setOutlineInset(int left, int top, int right, int bottom) {
        mLayoutHelper.setOutlineInset(left, top, right, bottom);
    }

    @Override
    public void setHideRadiusSide(int hideRadiusSide) {
        mLayoutHelper.setHideRadiusSide(hideRadiusSide);
    }

    @Override
    public int getHideRadiusSide() {
        return mLayoutHelper.getHideRadiusSide();
    }

    @Override
    public void setBorderColor(@ColorInt int borderColor) {
        mLayoutHelper.setBorderColor(borderColor);
        invalidate();
    }

    @Override
    public void setBorderWidth(int borderWidth) {
        mLayoutHelper.setBorderWidth(borderWidth);
        invalidate();
    }

    @Override
    public void setShowBorderOnlyBeforeL(boolean showBorderOnlyBeforeL) {
        mLayoutHelper.setShowBorderOnlyBeforeL(showBorderOnlyBeforeL);
        invalidate();
    }

    @Override
    public boolean setWidthLimit(int widthLimit) {
        if (mLayoutHelper.setWidthLimit(widthLimit)) {
            requestLayout();
            invalidate();
        }
        return true;
    }

    @Override
    public boolean setHeightLimit(int heightLimit) {
        if (mLayoutHelper.setHeightLimit(heightLimit)) {
            requestLayout();
            invalidate();
        }
        return true;
    }

    @Override
    public void setUseThemeGeneralShadowElevation() {
        mLayoutHelper.setUseThemeGeneralShadowElevation();
    }

    @Override
    public void setOutlineExcludePadding(boolean outlineExcludePadding) {
        mLayoutHelper.setOutlineExcludePadding(outlineExcludePadding);
    }

    @Override
    public void setShadowElevation(int elevation) {
        mLayoutHelper.setShadowElevation(elevation);
    }

    @Override
    public int getShadowElevation() {
        return mLayoutHelper.getShadowElevation();
    }

    @Override
    public void setShadowAlpha(float shadowAlpha) {
        mLayoutHelper.setShadowAlpha(shadowAlpha);
    }

    @Override
    public float getShadowAlpha() {
        return mLayoutHelper.getShadowAlpha();
    }

    @Override
    public void setShadowColor(int shadowColor) {
        mLayoutHelper.setShadowColor(shadowColor);
    }

    @Override
    public int getShadowColor() {
        return mLayoutHelper.getShadowColor();
    }

    @Override
    public void setOuterNormalColor(int color) {
        mLayoutHelper.setOuterNormalColor(color);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        mLayoutHelper.drawDividers(canvas, getWidth(), getHeight());
        mLayoutHelper.dispatchRoundBorderDraw(canvas);
    }
}
