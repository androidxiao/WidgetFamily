# WidgetFamily

#基本样式是这些

![widgetfamily](https://github.com/androidxiao/WidgetFamily/blob/master/pic/ezgif-6-3e7e759741a6.gif)

### Download
```
   allprojects {
       repositories {
           ......
           maven { url 'https://jitpack.io' }
       }
   }
   implementation 'cn.round.widget:roundwidget:1.0.20'
```   
Customizable attributes
-----------------------

|        Attribute       |      default value     |           xml            |                 java                |                 explain                   |
|------------------------|------------------------|--------------------------|-------------------------------------|-------------------------------------------|
| x_backgroundColor        |   null  | x_backgroundColor          | setBg(int color)       |背景色|
| x_borderColor        |   null  | x_borderColor          | setColorBorder(int color)       |边框颜色|
| x_borderWidth        |   0  | x_borderWidth          | setBorderWidth(int borderWidth)       |边框宽度|
| x_isRadiusAdjustBounds        |   false  | x_isRadiusAdjustBounds          | setIsRadiusAdjustBounds(int flag)       |圆角是否要自适应为 View 高度的一半|
| x_radius        |   0  | x_radius          | setRadius(int radius)       |同时指定四个方向的圆角|
| x_radiusTopLeft        |   0  | x_radiusTopLeft          | setTopLeft(int radius)       |指定左上方圆角的大小|
| x_radiusTopRight        |   0  | x_radiusTopRight          | setTopRight(int radius)       |指定右上方圆角的大小|
| x_radiusBottomLeft        |   0  | x_radiusBottomLeft          | setBottomLeft(int radius)       |指定左下方圆角的大小|
| x_radiusBottomRight        |   0  | x_radiusBottomRight          | setBottomRight(int radius)       |指定右下方圆角的大小|
| x_disableColor        |   null  | x_disableColor          | setDisableColor(int color)       |不可用背景色|
| x_pressColor        |   null  | x_pressColor          | setPressColor(int color)       |按下背景色|
| x_fontEnableColor        |   0  | x_fontEnableColor          | setFontEnableColor(int color)       |正常字体颜色|
| x_fontPressColor        |   0  | x_fontPressColor          | setFontPressColor(int color)       |按下字体的颜色|
| x_fontDisableColor        |   0  | x_fontDisableColor          | setFontDisableColor(int color)       |不可用时字体的颜色|
| x_startColor        |   0  | x_startColor          | setStartColor(int color)       |开始颜色|
| x_middleColor        |   0  | x_middleColor          | setMiddleColor(int color)       |中间颜色|
| x_endColor        |   0  | x_endColor          | setEndColor(int color)       |结束颜色|
| x_gradientOrientation        |   -1  | x_gradientOrientation          | setGradientOrientation(int orientation)       |渐变方向（TOP_BOTTOM、TR_BL、RIGHT_LEFT、BR_TL、BOTTOM_TOP、BL_TR、LEFT_RIGHT、TL_BR）|
| x_bottomDividerHeight        |   0  | x_bottomDividerHeight          | null       |底边边框高度|
| x_bottomDividerColor        |   0  | x_bottomDividerColor          | null       |底边边框|
| x_bottomDividerInsetLeft        |   0  | x_bottomDividerInsetLeft          | null       |底边边框 leftMargin|
| x_bottomDividerInsetRight        |   0  | x_bottomDividerInsetRight          | null       |底边边框 rightMargin|
| x_topDividerHeight        |   0  | x_topDividerHeight          | null       |上边边框高度|
| x_topDividerColor        |   0  | x_topDividerColor          | null       |上边边框颜色|
| x_topDividerInsetLeft        |   0  | x_topDividerInsetLeft          | null       |上边边框 leftMargin|
| x_topDividerInsetRight        |   0  | x_topDividerInsetRight          | null       |上边边框 rightMargin|
| x_leftDividerWidth        |   0  | x_leftDividerWidth          | null       |左边边框宽度|
| x_leftDividerColor        |   0  | x_leftDividerColor          | null       |左边边框颜色|
| x_leftDividerInsetTop        |   0  | x_leftDividerInsetTop          | null       |左边边框 topMargin|
| x_leftDividerInsetBottom        |   0  | x_leftDividerInsetBottom          | null       |左边边框 bottomMargin|
| x_rightDividerWidth        |   0  | x_rightDividerWidth          | null       |右边边框宽度|
| x_rightDividerColor        |   0  | x_rightDividerColor          | null       |右边边框颜色|
| x_rightDividerInsetTop        |   0  | x_rightDividerInsetTop          | null       |右边边框 topMargin|
| x_rightDividerInsetBottom        |   0  | x_rightDividerInsetBottom          | null       |右边边框 bottomMargin|
| x_radiusViewGroup        |   0  | x_radiusViewGroup          | null       |同时指定四个方向的圆角|
| x_borderColorViewGroup        |   0  | x_borderColorViewGroup          | null       |viewGroup 边框颜色|
| x_outerNormalColor        |   0  | x_outerNormalColor          | null       |api 小于 21 时，外边框颜色|
| x_hideRadiusSide        |   0  | x_hideRadiusSide          | null       |有 radius，但是有一边不显示 radius(none、left、top、right、bottom)
| x_showBorderOnlyBeforeL        |   true  | x_showBorderOnlyBeforeL          | null       |是否在低于 android 5.0 版本上显示边框
| x_shadowElevation        |   0  | x_shadowElevation          | null       |阴影大小|
| x_useThemeGeneralShadowElevation        |   false  | x_useThemeGeneralShadowElevation          | null       |是否使用 theme 下的阴影大小|
| x_shadowAlpha        |   0  | x_shadowAlpha          | null       |阴影透明度|
| x_outlineInsetTop        |   0  | x_outlineInsetTop          | null       |outline topMargin|
| x_outlineInsetLeft        |   0  | x_outlineInsetLeft          | null       |outline leftMargin|
| x_outlineInsetRight        |   0  | x_outlineInsetRight          | null       |outline rightMargin|
| x_outlineInsetBottom        |   0  | x_outlineInsetBottom          | null       |outline bottomMargin|
| x_outlineExcludePadding        |   false  | x_outlineExcludePadding          | null       |outline 是否 excludePadding|
| x_stateSelect        |   null  | x_stateSelect          | null       |对应 selector selected|
| x_statePressed        |   null  | x_statePressed          | null       |对应 selector pressed|
| x_stateEnabled        |   null  | x_stateEnabled          | null       |对应 selector enabled|
| x_stateDisabled        |   null  | x_stateDisabled          | null       |对应 selector -enabled|
| x_stateCheck        |   null  | x_stateCheck          | null       |对应 selector checked|
| x_stateUnCheck        |   null  | x_stateUnCheck          | null       |对应 selector -checked|
| x_statePressedC        |   0  | x_statePressedC          | null       |对应 color presscolor|
| x_stateEnabledC        |   0  | x_stateEnabledC          | null       |对应 color enablecolor|
| x_stateDisableC        |   0  | x_stateDisableC          | null       |对应 color -enablecolor|
| x_stateCheckC        |   0  | x_stateCheckC          | null       |对应 color checkedcolor|
| x_stateDirection        |   0  | x_stateDirection          | null       |图标显示方向(left、top、right、bottom)|

# xml 属性用法
```
<kotyox.widget.XRoundButton
            style="@style/CommonStyleView"
            android:gravity="center"
            android:text="按钮"
            android:textSize="18sp"
            app:x_borderColor="@color/colorAccent"
            app:x_borderWidth="2dp"
            app:x_endColor="@color/c_press"
            app:x_fontDisableColor="@color/c_font_disable"
            app:x_fontEnableColor="@color/c_font_press"
            app:x_fontPressColor="@color/c_font_press"
            app:x_gradientOrientation="BR_TL"
            app:x_isRadiusAdjustBounds="false"
            app:x_middleColor="@color/colorAccent"
            app:x_pressColor="@color/c_press"
            app:x_radiusTopLeft="5dp"
            app:x_backgroundColor="@color/colorAccent"
            app:x_disableColor="@color/c_179c16"
            app:x_radius="@dimen/d_5"
            app:x_radiusBottomLeft="@dimen/d_5"
            app:x_radiusBottomRight="@dimen/d_5"
            app:x_radiusTopRight="@dimen/d_5"
            app:x_startColor="@color/colorAccent" />
```
# java 代码示例
>属性设置完毕一定要调用 `build()` 方法
```
XRoundButtonState state = new XRoundButtonState(btn);
        state.setFontEnableColor(R.color.c_font_normal)
                .setRadius(4)
                .setColorBorder(R.color.colorAccent)
                .setBorderWidth(1)
                .setBg(R.color.bg_gradient)
                .setBottomLeft(1)
                .setEndColor(R.color.colorAccent)
                .setBottomRight(1)
                .setDisableColor(R.color.colorAccent)
                .setFontPressColor(R.color.colorAccent)
                .setGradientOrientation(1)
                .setIsRadiusAdjustBounds(true)
                .setStartColor(R.color.colorAccent)
                .setMiddleColor(R.color.colorAccent)
                .setTopLeft(1)
                .setTopRight(1)
                .setPressColor(R.color.colorAccent)
                .build();
```
# Thanks

[QMUI_Android](https://github.com/Tencent/QMUI_Android)

>该库是对 `QMUI_Android` 中没有封装的属性进行了添加(如渐变色、并解决了一些属性同时设置时的冲突问题)，也添加了一些诸如 `Checkbox`、`RadioButton` 等选中、未选中的图片切换自定属性，这样就不用再写 selector 文件了。





