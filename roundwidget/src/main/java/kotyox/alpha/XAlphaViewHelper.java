package kotyox.alpha;

import android.view.View;

import com.xutils.kotyo.XResHelper;

import java.lang.ref.WeakReference;

import family.widget.com.roundwidget.R;


public class XAlphaViewHelper {

    private WeakReference<View> mTarget;
    /**
     * 设置是否要在 press 时改变透明度
     */
    private boolean mChangeAlphaWhenPress = true;
    /**
     * 设置是否要在 disable 时改变透明度
     */
    private boolean mChangeAlphaWhenDisable = true;
    private float mNormalAlpha = 1f;
    private float mPressdAlpha = .5f;
    private float mDisabledAlpha = .5f;

    public XAlphaViewHelper(View target) {
        mTarget = new WeakReference<>(target);
        mPressdAlpha = XResHelper.getAttrFloatValue(target.getContext(), R.attr.x_alpha_pressed);
        mDisabledAlpha = XResHelper.getAttrFloatValue(target.getContext(), R.attr.x_alpha_disabled);
    }

    public XAlphaViewHelper(View target, float pressedAlpha, float disabledAlpha) {
        mTarget = new WeakReference<>(target);
        mPressdAlpha = pressedAlpha;
        mDisabledAlpha = disabledAlpha;
    }

    /**
     * the view to be handled, maybe not equal to target view
     *
     * @param current
     * @param pressed
     */
    public void onPressedChanged(View current, boolean pressed) {
        View target = mTarget.get();
        if (target == null) {
            return;
        }
        if (current.isEnabled()) {
            target.setAlpha(mChangeAlphaWhenPress && pressed && current.isClickable() ? mPressdAlpha : mNormalAlpha);
        } else {
            if (mChangeAlphaWhenDisable) {
                target.setAlpha(mDisabledAlpha);
            }
        }
    }

    /**
     * the view to be handled, maybe not equal to target view
     *
     * @param current
     * @param enabled
     */
    public void onEnabledChanged(View current, boolean enabled) {
        View target = mTarget.get();
        if (target == null) {
            return;
        }
        float alphaForIsEnable;
        if (mChangeAlphaWhenDisable) {
            alphaForIsEnable = enabled ? mNormalAlpha : mDisabledAlpha;
        } else {
            alphaForIsEnable = mNormalAlpha;
        }
        if (current != target && target.isEnabled() != enabled) {
            target.setEnabled(enabled);
        }
        target.setAlpha(alphaForIsEnable);
    }

    /**
     * 设置是否在 press 时改变透明度
     * @param changeAlphaWhenPress 是否要在 press 时改变透明度
     */
    public void setChangeAlphaWhenPress(boolean changeAlphaWhenPress) {
        mChangeAlphaWhenPress = changeAlphaWhenPress;
    }

    /**
     * 设置是否要在 disabled 时改变透明度
     * @param changeAlphaWhenDisable 是否要在 disabled 时改变透明度
     */
    public void setChangeAlphaWhenDisable(boolean changeAlphaWhenDisable) {
        mChangeAlphaWhenDisable = changeAlphaWhenDisable;
        View target = mTarget.get();
        if (target != null) {
            onEnabledChanged(target, target.isEnabled());
        }
    }
}
