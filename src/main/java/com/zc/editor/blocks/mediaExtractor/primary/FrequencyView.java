package com.zc.editor.blocks.mediaExtractor.primary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;


public class FrequencyView extends View{

    private Paint paint;

    public FrequencyView(Context context) {
        this(context,null);
    }

    public FrequencyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FrequencyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    private LinkedList<Integer> mlist = new LinkedList<>();
    public void addInt(int i){
        mlist.add(i);
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mlist.size(); i++) {
            canvas.drawLine(i,0,i+ 1,mlist.get(i),paint);
        }
    }
}
