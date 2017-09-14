package android.support.v7.widget;

/**
 * Created by Max on 9/13/17.
 */

import android.content.Context;
import android.view.View;

public class MyGridLayoutManager extends GridLayoutManager {
    public MyGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public MyGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void setSpanCount(int spanCount) {
        super.setSpanCount(spanCount);
        if (mSet != null && mSet.length != spanCount) {
            View[] newSet = new View[spanCount];
            System.arraycopy(mSet, 0, newSet, 0, Math.min(mSet.length, spanCount));
            mSet = newSet;
        }
    }
}