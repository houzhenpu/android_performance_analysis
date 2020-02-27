package com.performance.analysis.ui.widget.tableview.format;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.performance.analysis.ui.widget.tableview.intface.ISequenceFormat;
import com.performance.analysis.ui.widget.tableview.utils.DrawUtils;
import com.performance.analysis.ui.widget.tableview.TableConfig;

public abstract class BaseSequenceFormat implements ISequenceFormat {
    @Override
    public void draw(Canvas canvas, int sequence, Rect rect, TableConfig config) {
        //字体缩放
        Paint paint = config.getPaint();
        paint.setTextSize(paint.getTextSize() * (config.getZoom() > 1 ? 1 : config.getZoom()));
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(format(sequence + 1), rect.centerX(), DrawUtils.getTextCenterY(rect.centerY(), paint), paint);
    }
}
