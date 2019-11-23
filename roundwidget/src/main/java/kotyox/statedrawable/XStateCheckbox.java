package kotyox.statedrawable;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

import kotyox.statedrawable.state.XStateCheckboxDrawable;

/**
 * Created by wei.
 * Date: 2019/3/5 下午11:34
 * Description:
 */
public class XStateCheckbox extends AppCompatCheckBox {

    public XStateCheckbox(Context context) {
        this(context,null);
    }

    public XStateCheckbox(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XStateCheckbox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context,AttributeSet attrs,int defStyleAttr){
        XStateCheckboxDrawable stateDrawable = new XStateCheckboxDrawable(this);
        stateDrawable.fromAttributeSet(context, attrs, defStyleAttr);
        setClickable(true);
    }
}
