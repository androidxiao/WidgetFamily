package kotyox.statedrawable;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;

import kotyox.statedrawable.state.XStateCheckedTextViewDrawable;

/**
 * Created by wei.
 * Date: 2019/3/5 下午11:34
 * Description:
 */
public class XStateCheckedTextView extends AppCompatCheckedTextView {

    public XStateCheckedTextView(Context context) {
        this(context,null);
    }

    public XStateCheckedTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XStateCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context,AttributeSet attrs,int defStyleAttr){
        XStateCheckedTextViewDrawable stateDrawable = new XStateCheckedTextViewDrawable(this);
        stateDrawable.fromAttributeSet(context, attrs, defStyleAttr);
        setClickable(true);
    }
}
