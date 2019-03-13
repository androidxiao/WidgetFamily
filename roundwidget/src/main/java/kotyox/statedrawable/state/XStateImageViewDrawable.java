package kotyox.statedrawable.state;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

import java.lang.ref.WeakReference;

import family.widget.com.roundwidget.R;

/**
 * Created by wei.
 * Date: 2019/3/5 下午11:36
 * Description: 用来修改 drawLeft,checkbox 等等图标状态
 */
public class XStateImageViewDrawable extends StateListDrawable {

    private final int[] STATE_SELECTED = new int[]{android.R.attr.state_selected};
    private final int[] STATE_PRESSED = new int[]{android.R.attr.state_pressed};
    private final int[] STATE_ENABLED = new int[]{android.R.attr.state_enabled};
    private final int[] STATE_DISABLED = new int[]{-android.R.attr.state_enabled};
    private final int[] STATE_CHECK = new int[]{android.R.attr.state_checked};
    private final int[] STATE_UNCHECK = new int[]{-android.R.attr.state_checked};

    private WeakReference<View> mReference;

    public XStateImageViewDrawable(View view) {
        mReference = new WeakReference<>(view);
    }


    public void fromAttributeSet(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XStateImageView, defStyleAttr, 0);
        Drawable disabledDa = ta.getDrawable(R.styleable.XStateImageView_x_stateDisabled);
        Drawable selectDa = ta.getDrawable(R.styleable.XStateImageView_x_stateSelect);
        Drawable enabledDa = ta.getDrawable(R.styleable.XStateImageView_x_stateEnabled);
        int pressedDaC = ta.getColor(R.styleable.XStateImageView_x_statePressedC, 0);
        int enabledDaC = ta.getColor(R.styleable.XStateImageView_x_stateEnabledC, 0);
        int disableDaC = ta.getColor(R.styleable.XStateImageView_x_stateDisableC, 0);
        int checkDaC = ta.getColor(R.styleable.XStateImageView_x_stateCheckC, 0);
        Drawable pressedDa = ta.getDrawable(R.styleable.XStateImageView_x_statePressed);
        Drawable checkDa = ta.getDrawable(R.styleable.XStateImageView_x_stateCheck);
        Drawable unCheckDa = ta.getDrawable(R.styleable.XStateImageView_x_stateUnCheck);
        int drawableDirection = ta.getInt(R.styleable.XStateImageView_x_stateDirection, 1);

        if (selectDa != null) {
            addState(STATE_SELECTED, selectDa);
        }

        if (pressedDa != null) {
            addState(STATE_PRESSED, pressedDa);
        }

        if (checkDa != null) {
            addState(STATE_CHECK, checkDa);
        }

        if (unCheckDa != null) {
            addState(STATE_UNCHECK, unCheckDa);
        }

        if (disabledDa != null) {
            addState(STATE_DISABLED, disabledDa);
        }

        if (enabledDa != null) {
            addState(STATE_ENABLED, enabledDa);
        }


        View view = mReference.get();
        ((AppCompatImageView) view).setImageDrawable(this);

    }

}
