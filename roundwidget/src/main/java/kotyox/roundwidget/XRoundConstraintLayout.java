package kotyox.roundwidget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.xutils.kotyo.XViewHelper;

import kotyox.alpha.XAlphaConstraintLayout;



/**
 * Created by wei.
 * Date: 2019/1/4 下午12:02
 * Description: see {@link XRoundConstraintLayout} 与 {@link XRoundLayoutDrawable}
 */
public class XRoundConstraintLayout extends XAlphaConstraintLayout {

    private XRoundLayoutDrawable mBg;

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

}
