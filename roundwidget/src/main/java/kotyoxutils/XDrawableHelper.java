package kotyoxutils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;

public class XDrawableHelper {

    //为了节省每次创建产生的开销，但要注意多线程操作 synchronized
    private static final Canvas sCanvas = new Canvas();

    /***************** VectorDrawable ********************/

    public static Drawable getVectorDrawable(Context context, int resVector) {
        try {
            return AppCompatResources.getDrawable(context, resVector);
        } catch (Exception e) {
            EzLog.d("Error in getVectorDrawable. resVector=" + resVector + ", resName=" + context.getResources().getResourceName(resVector) + e.getMessage());
            return null;
        }
    }
}
