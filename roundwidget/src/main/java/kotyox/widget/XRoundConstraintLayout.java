package kotyox.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import kotyox.layout.XConstraintLayout;
import kotyox.widget.roundrawable.XRoundLayoutDrawable;
import kotyox.widget.state.XRoundConstraintLayoutState;


/**
 * Created by wei.
 * Date: 2019/1/4 下午12:02
 * Description: see {@link XRoundConstraintLayout} 与 {@link XRoundLayoutDrawable}
 */
public class XRoundConstraintLayout extends XConstraintLayout {

    private XRoundConstraintLayoutState mBg;

    public XRoundConstraintLayout(@NonNull Context context) {
        this(context, null);
    }

    public XRoundConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XRoundConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mBg = new XRoundConstraintLayoutState(this);
        mBg.fromAttributeSet(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mBg = null;
    }
}
