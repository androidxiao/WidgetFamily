package kotyox.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import kotyox.layout.XLinearLayout;
import kotyox.widget.roundrawable.XRoundLayoutDrawable;
import kotyox.widget.state.XRoundLinearLayoutState;


/**
 * Created by wei.
 * Date: 2019/1/3 下午11:52
 * Description: see {@link XRoundLinearLayout} 与 {@link XRoundLayoutDrawable}
 */
public class XRoundLinearLayout extends XLinearLayout {

    private XRoundLinearLayoutState mBg;

    public XRoundLinearLayout(@NonNull Context context) {
        this(context, null);
    }

    public XRoundLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XRoundLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mBg = new XRoundLinearLayoutState(this);
        mBg.fromAttributeSet(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mBg = null;
    }
}
