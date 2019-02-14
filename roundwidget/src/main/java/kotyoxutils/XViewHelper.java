package kotyoxutils;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.TouchDelegate;
import android.view.View;

/**
 * Created by wei.
 * Date: 2018/12/26 下午10:46
 * Description:
 */
public class XViewHelper {

    /**
     * 扩展点击区域的范围
     *
     * @param view       需要扩展的元素，此元素必需要有父级元素
     * @param expendSize 需要扩展的尺寸（以sp为单位的）
     */
    public static void expendTouchArea(final View view, final int expendSize) {
        if (view != null) {
            final View parentView = (View) view.getParent();

            parentView.post(new Runnable() {
                @Override
                public void run() {
                    Rect rect = new Rect();
                    view.getHitRect(rect); //如果太早执行本函数，会获取rect失败，因为此时UI界面尚未开始绘制，无法获得正确的坐标
                    rect.left -= expendSize;
                    rect.top -= expendSize;
                    rect.right += expendSize;
                    rect.bottom += expendSize;
                    parentView.setTouchDelegate(new TouchDelegate(rect, view));
                }
            });
        }
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void setBackgroundKeepingPadding(View view, Drawable drawable) {
        int[] padding = new int[]{view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom()};
        setBackground(view, drawable);
        view.setPadding(padding[0], padding[1], padding[2], padding[3]);
    }

    public static void setBackgroundColorKeepPadding(View view, @ColorInt int color) {
        int[] padding = new int[]{view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom()};
        view.setBackgroundColor(color);
        view.setPadding(padding[0], padding[1], padding[2], padding[3]);
    }

}
