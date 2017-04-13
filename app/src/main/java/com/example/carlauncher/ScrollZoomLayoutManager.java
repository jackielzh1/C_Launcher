package com.example.carlauncher;

import android.content.Context;
import android.util.Log;
import android.view.View;

import rouchuan.customlayoutmanager.CustomLayoutManager;

/**
 * Created by zixintechno on 12/7/16.
 */

public class ScrollZoomLayoutManager extends CustomLayoutManager {

    private static final float SCALE_RATE = 1.15f;
    private int itemSpace = 0;

    public ScrollZoomLayoutManager(Context context, int itemSpace) {
        super(context);
        this.itemSpace = itemSpace;
    }

    public ScrollZoomLayoutManager(Context context, int itemSpace, boolean isClockWise) {
        super(context,isClockWise);
        this.itemSpace = itemSpace;
    }

    @Override
    protected float setInterval() {
        return (int) (mDecoratedChildWidth*((SCALE_RATE-1f)/2f+1)+itemSpace);
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void setItemViewProperty(View itemView, float targetOffset) {
        Log.d("lzh", "setItemViewProperty,index = " + itemView.getTag());
        float scale = calculateScale((int) targetOffset + startLeft);
        float rotation = calculateRotation((int) targetOffset + startLeft);
        itemView.setScaleX(scale);
        itemView.setScaleY(scale);
//        itemView.setCameraDistance(1000 * rotation + 1000);
        itemView.setRotationY(rotation * 100);
    }

    /**
     *
     * @param x start positon of the view you want scale
     * @return the scale rate of current scroll offset
     */
    private float calculateScale(int x){
        Log.d("lzh", "calculateScale,x = " + x);
        int center = (getHorizontalSpace() - mDecoratedChildWidth) / 2;
        float deltaX = Math.abs(x - center);
        float scale = 1.0f;
        float diff = 0f;
//        if((mDecoratedChildWidth-deltaX)>0) diff = mDecoratedChildWidth-deltaX;
        diff = mDecoratedChildWidth - deltaX;
        if (x < 0) {
            scale = 1.08f;
        } else if (x > getHorizontalSpace()) {
            scale = 1.08f;
        } else {
            scale = deltaX / center / 12 + 1;
        }

        return scale;
//        return (SCALE_RATE-1f)/mDecoratedChildWidth * diff + 1;
    }

    private float calculateRotation(int x){
        Log.d("lzh", "calculateScale,x = " + x);
        int center = (getHorizontalSpace() - mDecoratedChildWidth) / 2;
        float deltaX = center - x;
        float scale = 1.0f;
        float diff = 0f;
//        if((mDecoratedChildWidth-deltaX)>0) diff = mDecoratedChildWidth-deltaX;
        diff = mDecoratedChildWidth - deltaX;
        if (x < 0) {
            scale = 0.1f;
        } else if (x > getHorizontalSpace()) {
            scale = 0.1f;
        } else {
            scale = deltaX / center / 10;
        }

        return scale;
//        return (SCALE_RATE-1f)/mDecoratedChildWidth * diff + 1;
    }
}
