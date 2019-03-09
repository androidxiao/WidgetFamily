package com.commonutils.kotyox.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * @author wei
 * Date: 2018/09/10
 * Desc: processing textview's text styles
 */
public class TextUtils {

    /**
     * modify some font colors
     * @param context
     * @param tv
     * @param resId
     * @param start
     * @param end
     */
    public static void getBuilder(Context context, TextView tv, int resId, int start, int end){
        SpannableStringBuilder style = setForegroundColorSpan(tv, resId, start, end, context);
        tv.setText(style);
    }

    /**
     * modify some font colors and sizes
     * @param tv
     */
    public static void getBuilder(TextView tv,int resId,int colorStart,int colorEnd,int textSize,int sizeStart,int sizeEnd){
        SpannableStringBuilder style = setForegroundColorSpan(tv, resId, colorStart, colorEnd, tv.getContext());
        setAbsoluteSizeSpan(textSize, sizeStart, sizeEnd, style);
        tv.setText(style);
    }

    /**
     * modify some font sizes
     * @param tv
     * @param textSize
     * @param sizeStart
     * @param sizeEnd
     */
    public static void getBuilder(TextView tv,int textSize,int sizeStart,int sizeEnd){
        SpannableStringBuilder style = getSpannableStringBuilder(tv);
        setAbsoluteSizeSpan(textSize, sizeStart, sizeEnd, style);
        tv.setText(style);
    }

    @NonNull
    private static SpannableStringBuilder getSpannableStringBuilder(TextView tv) {
        return new SpannableStringBuilder(tv.getText().toString());
    }

    private static void setAbsoluteSizeSpan(int textSize, int sizeStart, int sizeEnd, SpannableStringBuilder style) {
        style.setSpan(new AbsoluteSizeSpan(textSize, true), sizeStart, sizeEnd, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//modify font size behind
    }

    @NonNull
    private static SpannableStringBuilder setForegroundColorSpan(TextView tv, int resId, int colorStart, int colorEnd, Context context) {
        SpannableStringBuilder style = getSpannableStringBuilder(tv);
        style.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, resId)), colorStart, colorEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//modify font color behind
        return style;
    }

}
