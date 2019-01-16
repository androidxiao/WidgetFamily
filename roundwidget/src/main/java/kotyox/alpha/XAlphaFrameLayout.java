package kotyox.alpha;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by wei.
 * Date: 2019/1/6 下午11:35
 * Description:
 */
public class XAlphaFrameLayout extends FrameLayout {

    private XAlphaViewHelper mAlphaViewHelper;

    public XAlphaFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public XAlphaFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XAlphaFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
