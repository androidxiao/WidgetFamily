package kotyox.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import kotyox.layout.XFrameLayout;
import kotyoxutils.XViewHelper;


/**
 * Created by wei.
 * Date: 2019/1/3 下午10:41
 * Description: see {@link XRoundFrameLayout} 与 {@link XRoundLayoutDrawable}
 */
public class XRoundFrameLayout extends XFrameLayout {

    private XRoundLayoutDrawable mBg;

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
        mBg = new XRoundLayoutDrawable(this);
        mBg.fromAttributeSet(context, attrs, defStyleAttr);
        XViewHelper.setBackgroundKeepingPadding(this, mBg);
        mBg.setTouchListener();
    }

    //处理按钮点击事件无效
    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        mBg.setIsTouchPass(false);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mBg = null;
    }
}
