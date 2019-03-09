package com.commonutils.kotyox.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ReplacementSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wei.
 * Date: 2019/1/20 15:56
 * Desc:
 * <p>
 * StyleCss Method	    Internal Span
 * textSize	            AbsoluteSizeSpan
 * textColor	        ForegroundColorSpan
 * textSizeRelative	    RelativeSizeSpan
 * backgroundColor	    BackgroundColorSpan
 * style	            StyleSpan
 * underline	        UnderlineSpan
 * strike	            StrikethroughSpan
 * superscript	        SuperscriptSpan
 * subscript	        SubscriptSpan
 * </p>
 */
public class SpanStyleText extends AppCompatTextView {

    // some default params
    private static int DEFAULT_ABSOLUTE_TEXT_SIZE;
    private static float DEFAULT_RELATIVE_TEXT_SIZE = 1;

    private List<StyleCss> mStyleCss;

    /**
     * Create a new instance of a this class
     *
     * @param context
     */
    public SpanStyleText(Context context) {
        this(context, null);
    }

    public SpanStyleText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpanStyleText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mStyleCss = new ArrayList<>();
        SpanStyleText.DEFAULT_ABSOLUTE_TEXT_SIZE = (int) getTextSize();
    }

    /**
     * Use this method to add a {@link SpanStyleText} to a SpanStyleText.
     * Each {@link StyleCss } is added sequentially, so the
     * order you call this method matters.
     *
     * @param aStyleCss the StyleCss
     */
    public void addStyleCss(StyleCss aStyleCss) {
        mStyleCss.add(aStyleCss);
    }

    /**
     * Adds a StyleCss at this specific location. The underlying data structure is a
     * {@link List}, so expect the same type of behaviour.
     *
     * @param aStyleCss the StyleCss to add.
     * @param location  the index at which to add.
     */
    public void addStyleCss(StyleCss aStyleCss, int location) {
        mStyleCss.add(location, aStyleCss);
    }

    /**
     * Replaces the StyleCss at the specified location with this new StyleCss. The underlying data
     * structure is a {@link List}, so expect the same type of behaviour.
     *
     * @param newStyleCss the StyleCss to insert.
     * @param location    the index at which to insert.
     */
    public void replaceStyleCssAt(int location, StyleCss newStyleCss) {
        mStyleCss.set(location, newStyleCss);
    }

    /**
     * Removes the StyleCss at this specified location. The underlying data structure is a
     * {@link List}, so expect the same type of behaviour.
     *
     * @param location the index of the StyleCss to remove
     */
    public void removeStyleCss(int location) {
        mStyleCss.remove(location);
    }

    /**
     * Get a specific {@link StyleCss} in position index.
     *
     * @param location position of StyleCss (0 based)
     * @return StyleCss o null if invalid index
     */
    public StyleCss getStyleCss(int location) {
        if (location >= 0 && location < mStyleCss.size()) {
            return mStyleCss.get(location);
        }

        return null;
    }

    /**
     * Call this method when you're done adding {@link StyleCss}s
     * and want this TextView to display the final, styled version of it's String contents.
     * <p>
     * You MUST also call this method whenever you make a modification to the text of a StyleCss that
     * has already been displayed.
     */
    public void display() {

        // generate the final string based on the stylecss
        StringBuilder builder = new StringBuilder();
        for (StyleCss aStyleCss : mStyleCss) {
            builder.append(aStyleCss.text);
        }

        // apply spans
        int cursor = 0;
        SpannableString finalString = new SpannableString(builder.toString());
        for (StyleCss aStyleCss : mStyleCss) {
            applySpannablesTo(aStyleCss, finalString, cursor, cursor + aStyleCss.text.length());
            cursor += aStyleCss.text.length();
        }

        // set the styled text
        setText(finalString);
    }

    private void applySpannablesTo(StyleCss aStyleCss, SpannableString finalString, int start, int end) {

        if (aStyleCss.roundCornerSize > 0) {
            finalString.setSpan(new RoundBackgroundColorSpan(aStyleCss.backgroundColor, aStyleCss.textColor, aStyleCss.roundCornerSize), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (aStyleCss.subscript) {
            finalString.setSpan(new SubscriptSpan(), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (aStyleCss.superscript) {
            finalString.setSpan(new SuperscriptSpan(), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (aStyleCss.strike) {
            finalString.setSpan(new StrikethroughSpan(), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (aStyleCss.underline) {
            finalString.setSpan(new UnderlineSpan(), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // style
        finalString.setSpan(new StyleSpan(aStyleCss.style), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // absolute text size
        finalString.setSpan(new AbsoluteSizeSpan(aStyleCss.textSize), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // relative text size
        finalString.setSpan(new RelativeSizeSpan(aStyleCss.textSizeRelative), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // text color
        finalString.setSpan(new ForegroundColorSpan(aStyleCss.textColor), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // background color
        if (aStyleCss.backgroundColor != -1) {
            finalString.setSpan(new BackgroundColorSpan(aStyleCss.backgroundColor), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }


    /**
     * Resets the styling of this view and sets it's content to an empty String.
     */
    public void reset() {
        mStyleCss = new ArrayList<>();
        setText("");
    }

    /**
     * Change text color of all stylecss of textview.
     */
    public void changeTextColor(int textColor) {
        for (StyleCss mStyleCss : mStyleCss) {
            mStyleCss.setTextColor(textColor);
        }
        display();
    }

    /**
     * A StyleCss represents a part of the text that you want to style. Say for example you want this
     * SpanStyleText to display "Hello World" such that "Hello" is displayed in Bold and "World" is
     * displayed in Italics. Since these have different styles, they are both separate StyleCsss.
     * <p>
     * You create a StyleCss by using it's {@link  Builder}
     */
    public static class StyleCss {

        private String text;
        private int textColor;
        private final int textSize;
        private final int backgroundColor;
        private final float textSizeRelative;
        private final int style;
        private final boolean underline;
        private final boolean superscript;
        private final boolean strike;
        private final boolean subscript;
        private int roundCornerSize;

        public StyleCss(Builder builder) {
            this.text = builder.text;
            this.textSize = builder.textSize;
            this.textColor = builder.textColor;
            this.backgroundColor = builder.backgroundColor;
            this.textSizeRelative = builder.textSizeRelative;
            this.style = builder.style;
            this.underline = builder.underline;
            this.superscript = builder.superscript;
            this.subscript = builder.subscript;
            this.strike = builder.strike;
            this.roundCornerSize = builder.roundCornerSize;
        }

        /**
         * Sets the text of this StyleCss. If you're creating a new StyleCss, you should do so using it's
         * {@link Builder}.
         * <p>
         * Use this method if you want to modify the text of an existing StyleCss that is already
         * displayed. After doing so, you MUST call {@code display()} for the changes to show up.
         *
         * @param text the text to display
         */
        public void setText(String text) {
            this.text = text;
        }


        /**
         * Sets the text color of this StyleCss. If you're creating a new StyleCss, you should do so using it's
         * {@link Builder}.
         * <p>
         * Use this method if you want to change the text color of an existing StyleCss that is already
         * displayed. After doing so, you MUST call {@code display()} for the changes to show up.
         *
         * @param textColor of text (it is NOT android Color resources ID, use getResources().getColor(R.color.colorId) for it)
         */
        public void setTextColor(int textColor) {
            this.textColor = textColor;
        }

        /**
         * set apart of text background corner
         *
         * @param roundCornerSize
         */
        public void roundCorner(int roundCornerSize) {
            this.roundCornerSize = roundCornerSize;
        }

        /**
         * Builder of StyleCss
         */
        public static class Builder {

            // required
            private final String text;

            // optional
            private int textSize = DEFAULT_ABSOLUTE_TEXT_SIZE;
            private int textColor = Color.BLACK;
            private int backgroundColor = -1;
            private float textSizeRelative = DEFAULT_RELATIVE_TEXT_SIZE;
            private int style = Typeface.NORMAL;
            private boolean underline = false;
            private boolean strike = false;
            private boolean superscript = false;
            private boolean subscript = false;
            private int roundCornerSize;

            /**
             * Set apart of text background corner
             *
             * @param roundCornerSize corner size
             * @return a Builder
             */
            public Builder roundCorner(int roundCornerSize) {
                this.roundCornerSize = roundCornerSize;
                return this;
            }

            /**
             * Creates a new Builder for this StyleCss.
             *
             * @param text the text of this StyleCss
             */
            public Builder(String text) {
                this.text = text;
            }

            /**
             * Sets the absolute text size.
             *
             * @param textSize text size in pixels
             * @return a Builder
             */
            public Builder textSize(int textSize) {
                this.textSize = textSize;
                return this;
            }

            /**
             * Sets the text color.
             *
             * @param textColor the color
             * @return a Builder
             */
            public Builder textColor(int textColor) {
                this.textColor = textColor;
                return this;
            }

            /**
             * Sets the text color.
             *
             * @param textColor the color
             * @return a Builder
             */
            public Builder textColor(String textColor) {
                this.textColor = Color.parseColor(textColor);
                return this;
            }

            /**
             * Sets the background color.
             * please use ContextCompat.getColor(context,colorId)
             *
             * @param backgroundColor the color
             * @return a Builder
             */
            public Builder backgroundColor(int backgroundColor) {
                this.backgroundColor = backgroundColor;
                return this;
            }

            /**
             * Sets the background color.
             *
             * @param backgroundColor the color
             * @return a Builder
             */
            public Builder backgroundColor(String backgroundColor) {
                this.backgroundColor = Color.parseColor(backgroundColor);
                return this;
            }

            /**
             * Sets the relative text size.
             *
             * @param textSizeRelative relative text size
             * @return a Builder
             */
            public Builder textSizeRelative(float textSizeRelative) {
                this.textSizeRelative = textSizeRelative;
                return this;
            }

            /**
             * Sets a style to this StyleCss.
             *
             * @param style see {@link Typeface}
             * @return a Builder
             */
            public Builder style(int style) {
                this.style = style;
                return this;
            }

            /**
             * Underlines this StyleCss.
             *
             * @return a Builder
             */
            public Builder underline() {
                this.underline = true;
                return this;
            }

            /**
             * Strikes this StyleCss.
             *
             * @return a Builder
             */
            public Builder strike() {
                this.strike = true;
                return this;
            }

            /**
             * Sets this StyleCss as a superscript.
             *
             * @return a Builder
             */
            public Builder superscript() {
                this.superscript = true;
                return this;
            }

            /**
             * Sets this StyleCss as a subscript.
             *
             * @return a Builder
             */
            public Builder subscript() {
                this.subscript = true;
                return this;
            }

            /**
             * Creates a {@link StyleCss} with the customized
             * parameters.
             *
             * @return a StyleCss
             */
            public StyleCss build() {
                return new StyleCss(this);
            }
        }
    }

    public class RoundBackgroundColorSpan extends ReplacementSpan {
        private int bgColor;
        private int textColor;
        private int cornerSize;

        public RoundBackgroundColorSpan(int bgColor, int textColor, int cornerSize) {
            super();
            this.bgColor = bgColor;
            this.textColor = textColor;
            this.cornerSize = cornerSize;
        }

        @Override
        public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
//            return ((int) paint.measureText(text, start, end) + 60);
            return ((int) paint.measureText(text, start, end));
        }

        @Override
        public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
            int color1 = paint.getColor();
            paint.setColor(this.bgColor);
//            canvas.drawRoundRect(new RectF(x, top + 1, x + ((int) paint.measureText(text, start, end) + 40), bottom - 1), 15, 15, paint);
            canvas.drawRoundRect(new RectF(x, top, x + ((int) paint.measureText(text, start, end)), bottom - 1), dp2px(cornerSize), dp2px(cornerSize), paint);
            paint.setColor(this.textColor);
//            canvas.drawText(text, start, end, x + 20, y, paint);
            canvas.drawText(text, start, end, x, y, paint);
            paint.setColor(color1);
        }
    }

    /**
     * dp2px
     *
     * @param value
     * @return
     */
    private float dp2px(float value) {
        final float scale = getResources().getDisplayMetrics().densityDpi;
        return value * scale / 160 + 0.5f;
    }

}