package kotyox.statedrawable;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import kotyox.statedrawable.state.XStateButtonDrawable;

/**
 * Created by wei.
 * Date: 2019/3/5 下午11:34
 * Description:
 */
public class XStateButton extends AppCompatButton {

    public XStateButton(Context context) {
        this(context,null);
    }

    public XStateButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XStateButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context,AttributeSet attrs,int defStyleAttr){
        XStateButtonDrawable stateDrawable = new XStateButtonDrawable(this);
        stateDrawable.fromAttributeSet(context, attrs, defStyleAttr);
    }
}
