package kotyox.alpha;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

/**
 * Created by wei.
 * Date: 2019/1/7 下午11:48
 * Description:
 */
public class XAlphaConstraintLayout extends ConstraintLayout {
    private XAlphaViewHelper mAlphaViewHelper;

    public XAlphaConstraintLayout(@NonNull Context context) {
        this(context, null);
    }

    public XAlphaConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XAlphaConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private XAlphaViewHelper getAlphaViewHelper() {
        if (mAlphaViewHelper == null) {
            mAlphaViewHelper = new XAlphaViewHelper(this);
        }
        return mAlphaViewHelper;
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        getAlphaViewHelper().onPressedChanged(this, pressed);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        getAlphaViewHelper().onEnabledChanged(this, enabled);
    }

    /**
     * 设置是否要在 press 时改变透明度
     *
     * @param changeAlphaWhenPress 是否要在 press 时改变透明度
     */
    public void setChangeAlphaWhenPress(boolean changeAlphaWhenPress) {
        getAlphaViewHelper().setChangeAlphaWhenPress(changeAlphaWhenPress);
    }

    /**
     * 设置是否要在 disabled 时改变透明度
     *
     * @param changeAlphaWhenDisable 是否要在 disabled 时改变透明度
     */
    public void setChangeAlphaWhenDisable(boolean changeAlphaWhenDisable) {
        getAlphaViewHelper().setChangeAlphaWhenDisable(changeAlphaWhenDisable);
    }
}
