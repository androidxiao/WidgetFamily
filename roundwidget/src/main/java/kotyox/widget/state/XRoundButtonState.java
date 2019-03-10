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
import kotyoxutils.XViewHelper;

import static kotyoxutils.XDrawableHelper.createColorStateList;
import static kotyoxutils.XDrawableHelper.createStateListDrawable;

/**
 * Created by wei.
 * Date: 2019/3/10 下午3:11
 * Description:
 */
public class XRoundButtonState {

    private WeakReference<View> mReference;

    public XRoundButtonState(View view) {
        mReference = new WeakReference<>(view);
    }

    public void fromAttributeSet(Context context , AttributeSet set, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(set, R.styleable.XRoundButton, defStyleAttr, 0);

        ColorStateList colorBg = ta.getColorStateList(R.styleable.XRoundButton_x_backgroundColor);
        ColorStateList colorBorder = ta.getColorStateList(R.styleable.XRoundButton_x_borderColor);
        int borderWidth = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_borderWidth, 0);
        boolean isRadiusAdjustBounds = ta.getBoolean(R.styleable.XRoundButton_x_isRadiusAdjustBounds, false);
        int mRadius = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_radius, 0);
        int mRadiusTopLeft = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_radiusTopLeft, 0);
        int mRadiusTopRight = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_radiusTopRight, 0);
        int mRadiusBottomLeft = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_radiusBottomLeft, 0);
        int mRadiusBottomRight = ta.getDimensionPixelSize(R.styleable.XRoundButton_x_radiusBottomRight, 0);
        ColorStateList disableColor = ta.getColorStateList(R.styleable.XRoundButton_x_disableColor);
        ColorStateList pressColor = ta.getColorStateList(R.styleable.XRoundButton_x_pressColor);
        int fontEnableColor = ta.getColor(R.styleable.XRoundButton_x_fontEnableColor, ContextCompat.getColor(context, android.R.color.black));
        int fontPressColor = ta.getColor(R.styleable.XRoundButton_x_fontPressColor, ContextCompat.getColor(context, android.R.color.black));
        int fontDisableColor = ta.getColor(R.styleable.XRoundButton_x_fontDisableColor, ContextCompat.getColor(context, android.R.color.black));
        int startColor = ta.getColor(R.styleable.XRoundButton_x_startColor, 0);
        int middleColor = ta.getColor(R.styleable.XRoundButton_x_middleColor, 0);
        int endColor = ta.getColor(R.styleable.XRoundButton_x_endColor, 0);

        XRoundDrawable enableDrawable = new XRoundDrawable();
        enableDrawable.fromAttributeSet(colorBg,colorBorder,borderWidth,mRadiusTopLeft,mRadiusTopRight,mRadiusBottomLeft,mRadiusBottomRight,isRadiusAdjustBounds,mRadius);
        if(startColor!=0) {
            int colors[] = {startColor, middleColor, endColor};
            enableDrawable.setColors(colors);
        }

        XRoundDrawable pressDrawable = new XRoundDrawable();
        pressDrawable.fromAttributeSet(pressColor,colorBorder,borderWidth,mRadiusTopLeft,mRadiusTopRight,mRadiusBottomLeft,mRadiusBottomRight,isRadiusAdjustBounds,mRadius);

        XRoundDrawable disableDrawable = new XRoundDrawable();
        disableDrawable.fromAttributeSet(disableColor,colorBorder,borderWidth,mRadiusTopLeft,mRadiusTopRight,mRadiusBottomLeft,mRadiusBottomRight,isRadiusAdjustBounds,mRadius);

        StateListDrawable stateListDrawable = createStateListDrawable(enableDrawable, pressDrawable,disableDrawable);
        View view = mReference.get();
        XViewHelper.setBackgroundKeepingPadding(view, stateListDrawable);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(createColorStateList(fontEnableColor,fontPressColor,fontEnableColor,fontDisableColor));
        }
    }



}
