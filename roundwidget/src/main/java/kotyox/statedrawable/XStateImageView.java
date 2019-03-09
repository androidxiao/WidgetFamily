package kotyox.statedrawable;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by wei.
 * Date: 2019/3/5 下午11:34
 * Description:
 */
public class XStateImageView extends AppCompatImageView {

    public XStateImageView(Context context) {
        this(context,null);
    }

    public XStateImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XStateImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context,AttributeSet attrs,int defStyleAttr){
        XStateDrawable stateDrawable = new XStateDrawable(this);
        stateDrawable.fromAttributeSet(context, attrs, defStyleAttr);
    }
}
