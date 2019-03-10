package kotyox.widget.state;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;

import java.lang.ref.WeakReference;

import family.widget.com.roundwidget.R;
import kotyox.widget.roundrawable.XRoundLayoutDrawable;
import kotyoxutils.XViewHelper;

import static kotyoxutils.XDrawableHelper.createStateListDrawable;
import static kotyoxutils.XDrawableHelper.getOrientation;

/**
 * Created by wei.
 * Date: 2019/3/10 下午3:11
 * Description:
 */
public class XRoundFrameLayoutState {

    private WeakReference<View> mReference;

    public XRoundFrameLayoutState(View view) {
        mReference = new WeakReference<>(view);
    }

    public void fromAttributeSet(Context context , AttributeSet set, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(set, R.styleable.XRoundFrameLayout, defStyleAttr, 0);

        ColorStateList colorBg = ta.getColorStateList(R.styleable.XRoundFrameLayout_x_backgroundColor);
        ColorStateList colorBorder = ta.getColorStateList(R.styleable.XRoundFrameLayout_x_borderColor);
        int borderWidth = ta.getDimensionPixelSize(R.styleable.XRoundFrameLayout_x_borderWidth, 0);
        boolean isRadiusAdjustBounds = ta.getBoolean(R.styleable.XRoundFrameLayout_x_isRadiusAdjustBounds, false);
        int mRadius = ta.getDimensionPixelSize(R.styleable.XRoundFrameLayout_x_radius, 0);
        int mRadiusTopLeft = ta.getDimensionPixelSize(R.styleable.XRoundFrameLayout_x_radiusTopLeft, 0);
        int mRadiusTopRight = ta.getDimensionPixelSize(R.styleable.XRoundFrameLayout_x_radiusTopRight, 0);
        int mRadiusBottomLeft = ta.getDimensionPixelSize(R.styleable.XRoundFrameLayout_x_radiusBottomLeft, 0);
        int mRadiusBottomRight = ta.getDimensionPixelSize(R.styleable.XRoundFrameLayout_x_radiusBottomRight, 0);
        ColorStateList disableColor = ta.getColorStateList(R.styleable.XRoundFrameLayout_x_disableColor);
        ColorStateList pressColor = ta.getColorStateList(R.styleable.XRoundFrameLayout_x_pressColor);
        int startColor = ta.getColor(R.styleable.XRoundFrameLayout_x_startColor, 0);
        int middleColor = ta.getColor(R.styleable.XRoundFrameLayout_x_middleColor, 0);
        int endColor = ta.getColor(R.styleable.XRoundFrameLayout_x_endColor, 0);
        int gradientOrientation = ta.getInt(R.styleable.XRoundFrameLayout_x_gradientOrientation, -1);

        XRoundLayoutDrawable enableDrawable = new XRoundLayoutDrawable();
        enableDrawable.fromAttributeSet(colorBg,colorBorder,borderWidth,mRadiusTopLeft,mRadiusTopRight,mRadiusBottomLeft,mRadiusBottomRight,isRadiusAdjustBounds,mRadius);
        if(startColor!=0) {
            int colors[] = {startColor, middleColor, endColor};
            enableDrawable.setColors(colors);
            enableDrawable.setOrientation(getOrientation(gradientOrientation));
        }

        XRoundLayoutDrawable pressDrawable = new XRoundLayoutDrawable();
        pressDrawable.fromAttributeSet(pressColor,colorBorder,borderWidth,mRadiusTopLeft,mRadiusTopRight,mRadiusBottomLeft,mRadiusBottomRight,isRadiusAdjustBounds,mRadius);

        XRoundLayoutDrawable disableDrawable = new XRoundLayoutDrawable();
        disableDrawable.fromAttributeSet(disableColor,colorBorder,borderWidth,mRadiusTopLeft,mRadiusTopRight,mRadiusBottomLeft,mRadiusBottomRight,isRadiusAdjustBounds,mRadius);

        StateListDrawable stateListDrawable = createStateListDrawable(enableDrawable, pressDrawable,disableDrawable);
        View view = mReference.get();
        XViewHelper.setBackgroundKeepingPadding(view, stateListDrawable);
    }

}
