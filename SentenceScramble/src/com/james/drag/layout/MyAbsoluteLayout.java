package com.james.drag.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews.RemoteView;

@RemoteView
public class MyAbsoluteLayout extends ViewGroup {
    public static class LayoutParams extends ViewGroup.LayoutParams {
        protected static String sizeToString(int size) {
            if (size == WRAP_CONTENT) {
                return "wrap-content";
            }
            if (size == MATCH_PARENT) {
                return "match-parent";
            }
            return String.valueOf(size);
        }

        public int x;
        public int y;

        /**
         * Creates a new set of layout parameters. The values are extracted from the supplied attributes set and context. The XML attributes mapped to
         * this set of layout parameters are:
         * 
         * <ul>
         * <li><code>layout_x</code>: the X location of the child</li>
         * <li><code>layout_y</code>: the Y location of the child</li>
         * <li>All the XML attributes from {@link android.view.ViewGroup.LayoutParams}</li>
         * </ul>
         * 
         * @param c
         *            the application environment
         * @param attrs
         *            the set of attributes from which to extract the layout parameters values
         */
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height, int x, int y) {
            super(width, height);
            this.x = x;
            this.y = y;
        }

        /**
         * {@inheritDoc}
         */
        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public String debug(String output) {
            return output + "Absolute.LayoutParams={width=" + sizeToString(width) + ", height=" + sizeToString(height) + " x=" + x + " y=" + y + "}";
        }
    }

    public MyAbsoluteLayout(Context context) {
        super(context);
    }

    public MyAbsoluteLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyAbsoluteLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyAbsoluteLayout.LayoutParams(getContext(), attrs);
    }

    // Override to allow type-checking of LayoutParams.
    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof MyAbsoluteLayout.LayoutParams;
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();

        int paddingL = getPaddingLeft();
        int paddingT = getPaddingTop();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                MyAbsoluteLayout.LayoutParams lp = (MyAbsoluteLayout.LayoutParams) child.getLayoutParams();

                int childLeft = paddingL + lp.x;
                int childTop = paddingT + lp.y;
                child.layout(childLeft, childTop, childLeft + child.getMeasuredWidth(), childTop + child.getMeasuredHeight());
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();

        int maxHeight = 0;
        int maxWidth = 0;

        // Find out how big everyone wants to be
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        // Find rightmost and bottom-most child
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                int childRight;
                int childBottom;

                MyAbsoluteLayout.LayoutParams lp = (MyAbsoluteLayout.LayoutParams) child.getLayoutParams();

                childRight = lp.x + child.getMeasuredWidth();
                childBottom = lp.y + child.getMeasuredHeight();

                maxWidth = Math.max(maxWidth, childRight);
                maxHeight = Math.max(maxHeight, childBottom);
            }
        }

        // Account for padding too
        maxWidth += getPaddingLeft() + getPaddingRight();
        maxHeight += getPaddingTop() + getPaddingBottom();

        // Check against minimum height and width
        maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());

        setMeasuredDimension(resolveSize(maxWidth, widthMeasureSpec), resolveSize(maxHeight, heightMeasureSpec));
    }

}