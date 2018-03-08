package com.curtesmalteser.peakinterface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by António "Curtes Malteser" Bastião on 03/03/2018.
 */

public class RadarChart extends View {

    private int width, height = 0;
    int radius = 0;
    private double[] numbers = {1, 2, 3, 4, 5, 6, 7};
    private Rect rect = new Rect();
    private Paint paint = new Paint();
    private Paint paintArea = new Paint();
    private boolean isInit;

    @Nullable
    private ArrayList<RadarData> mMainData;
    private ArrayList<RadarData> mSecondData;

    public RadarChart(Context context) {
        super(context);
    }

    public RadarChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initChart() {
        height = getHeight();
        width = getWidth();
        radius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
                getResources().getDisplayMetrics());
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        paintArea.setColor(getResources().getColor(R.color.white));
        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isInit)
            initChart();

        canvas.rotate(13, width / 2, height / 2);
        //setData();
        drawCircle(canvas);
        drawLines(canvas);
        drawPolygon(canvas);

        if (mSecondData!=null)
        secondPolygon(canvas);

    }

    private void drawCircle(Canvas canvas) {

        for (float i = 1; i > 0; i -= 0.1) {
            float circle = radius * i;
            canvas.drawCircle(width / 2, height / 2, circle, paint);

        }
    }

    private void drawLines(Canvas canvas) {

        for (double number : numbers) {
            double angle = (Math.PI * number / ((double) numbers.length / 2));
            int x = (int) (width / 2 + Math.cos(angle) * (radius + 20) - rect.width() / 2);
            int y = (int) (height / 2 + Math.sin(angle) * (radius + 20) + rect.height() / 2);

            canvas.drawLine(width / 2, height / 2, x, y, paint);
        }
    }

    private void drawPolygon(Canvas canvas) {

        boolean fill = true;

        if (fill)
            paintArea.setStyle(Paint.Style.FILL);
        else
            paintArea.setStyle(Paint.Style.STROKE);

        ArrayList<Float> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();

        for (int i = 0; i < mMainData.size(); i++) {
            x.add((float) (width / 2 + Math.cos(Math.PI * (i + 1) / ((double) numbers.length / 2)) * mMainData.get(i).value - rect.width() / 2));
            y.add((float) (height / 2 + Math.sin(Math.PI * (i + 1) / ((double) numbers.length / 2)) * mMainData.get(i).value + rect.height() / 2));
        }

        Path area = new Path();
        area.reset(); // only needed when reusing this path for a new build
        area.moveTo(x.get(0), y.get(0)); // used for first point
        area.lineTo(x.get(1), y.get(1));
        area.lineTo(x.get(2), y.get(2));
        area.lineTo(x.get(3), y.get(3));
        area.lineTo(x.get(4), y.get(4));
        area.lineTo(x.get(5), y.get(5));
        area.lineTo(x.get(6), y.get(6));
        area.lineTo(x.get(0), y.get(0)); // there is a setLastPoint action but i found it not to work as expected

        canvas.drawPath(area, paintArea);
    }

    public boolean hasData() {
        return mMainData != null && mMainData.size() > 0;
    }

    public ArrayList<RadarData> getData() {
        if (!hasData()) {
            return null;
        }
        final ArrayList<RadarData> data = new ArrayList<>();
        assert mMainData != null;
        for (RadarData item : mMainData) {
            data.add(new RadarData(item.value));
        }
        return data;
    }

    public void setData(@Nullable List<RadarData> data) {
        if (data != null) {
            mMainData = new ArrayList<>();
            for (RadarData item : data) {
                mMainData.add(new RadarData(item.value));
            }
        } else {
            mMainData = null;
        }
        invalidate();
    }

    private void secondPolygon(Canvas canvas) {

        paintArea.setColor(getResources().getColor(R.color.peakBlue));
        boolean fill = true;

        if (fill)
            paintArea.setStyle(Paint.Style.FILL);
        else
            paintArea.setStyle(Paint.Style.STROKE);

        ArrayList<Float> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();

        for (int i = 0; i < mSecondData.size(); i++) {
            x.add((float) (width / 2 + Math.cos(Math.PI * (i + 1) / ((double) numbers.length / 2)) * mSecondData.get(i).value - rect.width() / 2));
            y.add((float) (height / 2 + Math.sin(Math.PI * (i + 1) / ((double) numbers.length / 2)) * mSecondData.get(i).value + rect.height() / 2));
        }

        Path area = new Path();
        area.reset(); // only needed when reusing this path for a new build
        area.moveTo(x.get(0), y.get(0)); // used for first point
        area.lineTo(x.get(1), y.get(1));
        area.lineTo(x.get(2), y.get(2));
        area.lineTo(x.get(3), y.get(3));
        area.lineTo(x.get(4), y.get(4));
        area.lineTo(x.get(5), y.get(5));
        area.lineTo(x.get(6), y.get(6));
        area.lineTo(x.get(0), y.get(0)); // there is a setLastPoint action but i found it not to work as expected

        canvas.drawPath(area, paintArea);
    }

    public boolean hasSecondData() {
        return mSecondData != null && mSecondData.size() > 0;
    }


    public ArrayList<RadarData> getSecondData() {
        if (!hasSecondData()) {
            return null;
        }
        final ArrayList<RadarData> data = new ArrayList<>();
        assert mSecondData != null;
        for (RadarData item : mSecondData) {
            data.add(new RadarData(item.value));
        }
        return data;
    }

    public void setSecondData(@Nullable List<RadarData> data) {
        if (data != null) {
            mSecondData = new ArrayList<>();
            for (RadarData item : data) {
                mSecondData.add(new RadarData(item.value));
            }
        } else {
            mSecondData = null;
        }
        invalidate();
    }
}