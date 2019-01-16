package kotyox.roundwidget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.xutils.kotyo.XViewHelper;


/**
 * Created by wei.
 * Date: 2019/1/3 下午11:52
 * Description: see {@link XRoundLinearLayout} 与 {@link XRoundLayoutDrawable}
 */
public class XRoundLinearLayout extends LinearLayout {

    private XRoundLayoutDrawable mBg;

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