package kotyox.layout;

import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by wei.
 * Date: 2019/1/6 下午6:20
 * Description:
 */
public interface XLayout {

    int HIDE_RADIUS_SIDE_NONE = 0;
    int HIDE_RADIUS_SIDE_TOP = 1;
    int HIDE_RADIUS_SIDE_RIGHT = 2;
    int HIDE_RADIUS_SIDE_BOTTOM = 3;
    int HIDE_RADIUS_SIDE_LEFT = 4;

    @IntDef(value = {
            HIDE_RADIUS_SIDE_NONE,
            HIDE_RADIUS_SIDE_TOP,
            HIDE_RADIUS_SIDE_RIGHT,
            HIDE_RADIUS_SIDE_BOTTOM,
            HIDE_RADIUS_SIDE_LEFT,
    })
    @Retention(RetentionPolicy.SOURCE)
    @interface HideRadiusSide {
    }

    /**
     * 限制宽度
     *
     * @param widthLimit
     * @return
     */
    boolean setWidthLimit(int widthLimit);

    /**
     * 限制高度
     *
     * @param heightLimit
     * @return
     */
    boolean setHeightLimit(int heightLimit);

    /**
     * 使用 Theme 的阴影效果
     */
    void setUseThemeGeneralShadowElevation();

    /**
     * 如果轮廓包括 padding，使用 false
     *
     * @param outLineExcludePadding
     */
    void setOutlineExcludePadding(boolean outLineExcludePadding);

    /**
     * See {@link android.view.View#setElevation(float)}
     *
     * @param elevation
     */
    void setShadowElevation(int elevation);

    /**
     * See {@link android.view.View#getElevation()}
     *
     * @return
     */
    int getShadowElevation();

    /**
     * 设置轮廓的透明度，它也将作用于阴影
     *
     * @param shadowAlpha
     */
    void setShadowAlpha(float shadowAlpha);

    /**
     * 获取阴影透明度
     *
     * @return
     */
    float getShadowAlpha();

    /**
     * @param shadowColor 不透明颜色值
     * @return
     */
    void setShadowColor(int shadowColor);

    /**
     * @return 获取阴影颜色值
     */
    int getShadowColor();

    /**
     * 设置圆角的值
     *
     * @param radius
     */
    void setRadius(int radius);

    /**
     * 设置圆角，可以隐藏不想展示的边或全部隐藏
     *
     * @param radius
     * @param hideRadiusSide
     */
    void setRadius(int radius, @HideRadiusSide int hideRadiusSide);

    /**
     * 获取圆角
     */
    int getRadius();

    /**
     * 动态设置边界轮廓
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    void setOutlineInset(int left, int top, int right, int bottom);

    /**
     * Android L 后才支持阴影，这里提供了一个降级策略去支持 Android 4.x；
     * 通常我们使用边框，但是边框在 Android L+ 可能是多余的，因此我们默认
     * 不展示边框；
     * 如果你想要边框和阴影同时存在，你可以设置 setShowBorderOnlyBeforeL(false).
     *
     * @param showBorderOnlyBeforeL
     */
    void setShowBorderOnlyBeforeL(boolean showBorderOnlyBeforeL);

    /**
     * 在某种情况下，我们可能希望布局只有一边有圆角；
     * 但是设置 canvas.drawPath 并不是很方便；
     * 因此，我们通过另一种方式去隐藏另一边的圆角
     *
     * @param hideRadiusSide
     */
    void setHideRadiusSide(@HideRadiusSide int hideRadiusSide);

    /**
     * 获取隐藏边的圆角
     *
     * @return
     */
    int getHideRadiusSide();

    /**
     * 设置圆角和阴影
     *
     * @param radius
     * @param shadowElevation
     * @param shadowAlpha
     */
    void setRadiusAndShadow(int radius, int shadowElevation, float shadowAlpha);

    /**
     * 设置圆角和阴影，可隐藏不需要的边
     *
     * @param radius
     * @param hideRadiusSide
     * @param shadowElevation
     * @param shadowAlpha
     */
    void setRadiusAndShadow(int radius, @HideRadiusSide int hideRadiusSide, int shadowElevation, float
            shadowAlpha);

    /**
     * 设置圆角和阴影(大于 android 9 的版本支持 shadowColor)，可隐藏不需要的边
     *
     * @param radius
     * @param hideRadiusSide
     * @param shadowElevation
     * @param shadowColor
     * @param shadowAlpha
     */
    void setRadiusAndShadow(int radius, @HideRadiusSide int hideRadiusSide, int shadowElevation, int
            shadowColor, float shadowAlpha);

    /**
     * 设置边框颜色（如果不设置将不会绘制）
     *
     * @param borderColor
     */
    void setBorderColor(@ColorInt int borderColor);

    /**
     * 设置边框宽度（默认为 1px）
     *
     * @param borderWidth
     */
    void setBorderWidth(int borderWidth);

    /**
     * 设置 top divider
     *
     * @param topInsetLeft
     * @param topInsetRight
     * @param topDividerHeight
     * @param topDividerColor
     */
    void updateTopDivider(int topInsetLeft, int topInsetRight, int topDividerHeight, int topDividerColor);

    /**
     * 设置 bottom divider
     *
     * @param bottomInsetLeft
     * @param bottomInsetRight
     * @param bottomDividerHeight
     * @param bottomDividerColor
     */
    void updateBottomDivider(int bottomInsetLeft, int bottomInsetRight, int bottomDividerHeight, int bottomDividerColor);

    /**
     * 设置 left divider
     *
     * @param leftInsetTop
     * @param leftInsetBottom
     * @param leftDividerWidth
     * @param leftDividerColor
     */
    void updateLeftDivider(int leftInsetTop, int leftInsetBottom, int leftDividerWidth, int leftDividerColor);

    /**
     * 设置 right divider
     *
     * @param rightInsetTop
     * @param rightInsetBottom
     * @param rightDividerWidth
     * @param rightDividerColor
     */
    void updateRightDivider(int rightInsetTop, int rightInsetBottom, int rightDividerWidth, int rightDividerColor);

    /**
     * 只显示 top divider，隐藏其他
     *
     * @param topInsetLeft
     * @param topInsetRight
     * @param topDiverHeight
     * @param topDividerColor
     */
    void onlyShowTopDivider(int topInsetLeft, int topInsetRight, int topDiverHeight, int topDividerColor);

    /**
     * 只显示 bottom divider，隐藏其他
     *
     * @param bottomInsetLeft
     * @param bottomInsetRight
     * @param bottomDividerHeight
     * @param bottomDividerColor
     */
    void onlyShowBottomDivider(int bottomInsetLeft, int bottomInsetRight, int bottomDividerHeight, int bottomDividerColor);

    /**
     * 只显示 left divider，隐藏其他
     *
     * @param leftInsetTop
     * @param leftInsetBottom
     * @param leftDividerWidth
     * @param leftDividerColor
     */
    void onlyShowLeftDivider(int leftInsetTop, int leftInsetBottom, int leftDividerWidth, int leftDividerColor);

    /**
     * 只显示 right divider，隐藏其他
     *
     * @param rightInsetTop
     * @param rightInsetBottom
     * @param rightDividerWidth
     * @param rightDividerColor
     */
    void onlyShowRightDivider(int rightInsetTop, int rightInsetBottom, int rightDividerWidth, int rightDividerColor);

    /**
     * 在设置完 border 后，有时我们需要去使用动画改变 divider 的 alpha，
     * 因此，这里提供一个单独的方法去改变 alpha
     *
     * @param dividerAlpha [0,255]
     */
    void setTopDividerAlpha(int dividerAlpha);

    /**
     * @param dividerAlpha [0,255]
     */
    void setBottomDividerAlpha(int dividerAlpha);

    /**
     * @param dividerAlpha [0,255]
     */
    void setLeftDividerAlpha(int dividerAlpha);

    /**
     * @param dividerAlpha [0,255]
     */
    void setRightDividerAlpha(int dividerAlpha);

    /**
     * 只在 android L 之前有效
     *
     * @param color
     */
    void setOuterNormalColor(int color);
}
