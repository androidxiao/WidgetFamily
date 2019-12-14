package kotyoxutils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
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

    /**
     * 获取设置之后的Selector
     *
     * @return stateListDrawable
     */
    public static StateListDrawable createStateListDrawable(GradientDrawable enableDrawable, GradientDrawable pressDrawable,
                                                            GradientDrawable disableDrawable) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        //注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
        //所以不要把大范围放在前面了，如果sd.addState(new[]{},normal)放在第一个的话，就没有什么效果了
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressDrawable);
        stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, disableDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, enableDrawable);
        stateListDrawable.addState(new int[]{}, enableDrawable);
        return stateListDrawable;
    }

    /**
     * 获取设置之后的Selector
     *
     * @return stateListDrawable
     */
    public static StateListDrawable createStateListDrawable(
            GradientDrawable enableDrawable,
            GradientDrawable pressDrawable,
            GradientDrawable disableDrawable,
            GradientDrawable selectedDrawable,
            GradientDrawable unSelectedDrawable) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        //注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
        //所以不要把大范围放在前面了，如果sd.addState(new[]{},normal)放在第一个的话，就没有什么效果了
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, selectedDrawable);
        stateListDrawable.addState(new int[]{-android.R.attr.state_selected}, unSelectedDrawable);
        stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, disableDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, enableDrawable);
        stateListDrawable.addState(new int[]{}, enableDrawable);
        return stateListDrawable;
    }

    /**
     * 创建按钮文字点击样式
     *
     * @param normal  正常样式
     * @param pressed 按下样式
     * @param focused 焦点样式
     * @param unable  不可用样式
     * @return ColorStateList
     */
    public static ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
        int[] colors = new int[]{pressed, focused, normal, focused, unable, normal};
        int[][] states = new int[colors.length][];
        states[0] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[2] = new int[]{android.R.attr.state_enabled};
        states[3] = new int[]{android.R.attr.state_focused};
        states[4] = new int[]{android.R.attr.state_window_focused};
        states[5] = new int[]{};
        return new ColorStateList(states, colors);
    }

    public static ColorStateList createColorStateList(int normal, int pressed, int focused, int unable,int select,int unselect) {
        int[] colors = new int[]{pressed,select,unselect, focused, normal, focused, unable, normal};
        int[][] states = new int[colors.length][];
        states[0] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_selected};
        states[2] = new int[]{-android.R.attr.state_selected};
        states[3] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[4] = new int[]{android.R.attr.state_enabled};
        states[5] = new int[]{android.R.attr.state_focused};
        states[6] = new int[]{android.R.attr.state_window_focused};
        states[7] = new int[]{};
        return new ColorStateList(states, colors);
    }

    /**
     * 渐变的方向
     *
     * @param orientation
     * @return
     */
    public static GradientDrawable.Orientation getOrientation(int orientation) {
        switch (orientation) {
            case 0:
                return GradientDrawable.Orientation.TOP_BOTTOM;
            case 1:
                return GradientDrawable.Orientation.TR_BL;
            case 2:
                return GradientDrawable.Orientation.RIGHT_LEFT;
            case 3:
                return GradientDrawable.Orientation.BR_TL;
            case 4:
                return GradientDrawable.Orientation.BOTTOM_TOP;
            case 5:
                return GradientDrawable.Orientation.BL_TR;
            case 6:
                return GradientDrawable.Orientation.LEFT_RIGHT;
            case 7:
                return GradientDrawable.Orientation.TL_BR;
        }
        return GradientDrawable.Orientation.LEFT_RIGHT;
    }

    /**
     * 构造 ColorStateList
     *
     * @param color
     * @return
     */
    public static ColorStateList colorStateList(int color) {
        int[] colors = new int[]{color};
        int[][] states = new int[colors.length][];
        states[0] = new int[]{};
        return new ColorStateList(states, colors);
    }
}
