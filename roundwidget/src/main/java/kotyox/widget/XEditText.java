package kotyox.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import kotyoxutils.XViewHelper;


/**
 * Created by wei.
 * Date: 2019/1/10 下午10:05
 * Description:
 */
public class XEditText extends AppCompatEditText {

    private XEditTextDrawable mBg;

    public XEditText(Context context) {
        this(context, null);
    }

    public XEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBg = new XEditTextDrawable();
        mBg.fromAttributeSet(context, attrs, defStyleAttr);
        XViewHelper.setBackgroundKeepingPadding(this, mBg);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }
}
