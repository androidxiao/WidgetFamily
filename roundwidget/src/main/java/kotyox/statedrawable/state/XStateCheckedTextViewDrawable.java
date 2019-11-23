package kotyox.statedrawable.state;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import family.widget.com.roundwidget.R;

/**
 * Created by wei.
 * Date: 2019/3/5 下午11:36
 * Description: 用来修改 drawLeft,checkbox 等等图标状态
 */
public class XStateCheckedTextViewDrawable extends StateListDrawable {

    private final int[] STATE_SELECTED = new int[]{android.R.attr.state_selected};
    private final int[] STATE_PRESSED = new int[]{android.R.attr.state_pressed};
    private final int[] STATE_DISABLED = new int[]{-android.R.attr.state_enabled};
    private final int[] STATE_CHECK = new int[]{android.R.attr.state_checked};
    private final int[] STATE_UNCHECK = new int[]{-android.R.attr.state_checked};

    private WeakReference<View> mReference;

    public XStateCheckedTextViewDrawable(View view) {
        mReference = new WeakReference<>(view);
    }


    public void fromAttributeSet(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XStateCheckedTextView, defStyleAttr, 0);
        Drawable disabledDa = ta.getDrawable(R.styleable.XStateCheckedTextView_x_stateDisabled);
        Drawable selectDa = ta.getDrawable(R.styleable.XStateCheckedTextView_x_stateSelect);
        int pressedDaC = ta.getColor(R.styleable.XStateCheckedTextView_x_statePressedC, 0);
        int enabledDaC = ta.getColor(R.styleable.XStateCheckedTextView_x_stateEnabledC, 0);
        int disableDaC = ta.getColor(R.styleable.XStateCheckedTextView_x_stateDisableC, 0);
        int checkDaC = ta.getColor(R.styleable.XStateCheckedTextView_x_stateCheckC, 0);
        Drawable pressedDa = ta.getDrawable(R.styleable.XStateCheckedTextView_x_statePressed);
        Drawable checkDa = ta.getDrawable(R.styleable.XStateCheckedTextView_x_stateCheck);
        Drawable unCheckDa = ta.getDrawable(R.styleable.XStateCheckedTextView_x_stateUnCheck);
        int drawableDirection = ta.getInt(R.styleable.XStateCheckedTextView_x_stateDirection, 1);

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


        View view = mReference.get();
        setBounds(0, 0, getMinimumWidth(), getMinimumHeight());
        if (enabledDaC != 0) {
            ((TextView) view).setTextColor(createColorStateList(enabledDaC, pressedDaC, disableDaC, checkDaC));
        }
        if (drawableDirection == 1) {
            ((TextView) view).setCompoundDrawables(this, null, null, null);
        } else if (drawableDirection == 2) {
            ((TextView) view).setCompoundDrawables(null, this, null, null);
        } else if (drawableDirection == 3) {
            ((TextView) view).setCompoundDrawables(null, null, this, null);
        } else if (drawableDirection == 4) {
            ((TextView) view).setCompoundDrawables(null, null, null, this);
        }

    }

    /**
     * 对 TextView 设置不同状态时文字的颜色。
     */
    private ColorStateList createColorStateList(int normal, int pressed, int unable, int check) {
        int[] colors = new int[]{check, check, pressed, unable, normal, normal};
        int[][] states = new int[colors.length][];
        states[0] = new int[]{android.R.attr.state_checked};
        states[1] = new int[]{android.R.attr.state_selected};
        states[2] = new int[]{android.R.attr.state_pressed};
        states[3] = new int[]{-android.R.attr.state_enabled};
        states[4] = new int[]{android.R.attr.state_enabled};
        states[5] = new int[]{};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }
}
