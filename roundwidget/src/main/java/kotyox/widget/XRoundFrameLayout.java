package kotyox.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import kotyox.layout.XFrameLayout;
import kotyox.widget.state.XRoundFrameLayoutState;
import kotyox.widget.roundrawable.XRoundLayoutDrawable;


/**
 * Created by wei.
 * Date: 2019/1/3 下午10:41
 * Description: see {@link XRoundFrameLayout} 与 {@link XRoundLayoutDrawable}
 */
public class XRoundFrameLayout extends XFrameLayout {

    private XRoundFrameLayoutState mBg;

    public XRoundFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public XRoundFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XRoundFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mBg = new XRoundFrameLayoutState(this);
        mBg.fromAttributeSet(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mBg = null;
    }
}
