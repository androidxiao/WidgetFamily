package kotyox.roundwidget;

import android.content.Context;
import android.util.AttributeSet;

import com.xutils.kotyo.XViewHelper;

import family.widget.com.roundwidget.R;
import kotyox.alpha.XAlphaButton;


/**
 * 使按钮能方便地指定圆角、边框颜色、边框粗细、背景色、按下效果，字体颜色（按下颜色也可配置）
 * <p>
 * 注意：因为该控件的圆角采用 View 的 background 实现，所以与原生的 <code>android:background</code> 有冲突。
 * <ul>
 * <li>如果在 xml 中用 <code>android:background</code> 指定 background，该 background 不会生效。</li>
 * <li>如果在改 View 构造完后 {@link #setBackgroundResource(int)} 等方法设置背景，该背景将覆盖圆角效果</li>
 * </ul>
 * </p>
 * <p>
 * 如果在 xml 中指定圆角、边框颜色、边框粗细、背景色等值，采用 xml 属性
 * {@link R.styleable#XRoundButton}
 * </p>
 * <p>
 * 如需在 Java 中指定以上属性，需要通过 {@link #getBackground()} 获取 {@link XRoundButtonDrawable} 对象，
 * 然后使用 {@link XRoundButtonDrawable} 提供的方法进行设置。
 * </p>
 * <p>
 * 注意： Java 使用 <code>android:enabled</code> 为 false，请设置该类提供的 <code>setIsEnable</code> 方法；
 * 如果要设置按钮背景和字体按下效果，请使用该类提供的 <code>setPressedColor,set...</code> 方法；
 * 最后记得调用 <code>build()</code> 方法，否则设置的属性不起作用。
 * @see XRoundButtonDrawable
 */
public class XRoundTextView extends XAlphaButton {

    private XRoundButtonDrawable mBg;


    public XRoundTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public XRoundTextView(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.XButtonStyle);
        init(context, attrs, R.attr.XButtonStyle);
    }

    public XRoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mBg = new XRoundButtonDrawable(this);
        mBg.fromAttributeSet(context, attrs, defStyleAttr);
        XViewHelper.setBackgroundKeepingPadding(this, mBg);
        setChangeAlphaWhenDisable(false);
        setChangeAlphaWhenPress(false);
        mBg.setTouchListener();
    }

    //处理按钮点击事件无效
    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        mBg.setIsTouchPass(false);
    }


}
