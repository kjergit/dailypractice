package cn.com.kjer.practice.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import cn.com.kjer.practice.R;

/**
 * Created by kjer on 2016/3/18.
 */
public class CanvasView extends View {

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * canvas可以绘制的图形
     * canvas.drawArc （扇形）
     * canvas.drawCircle（圆）
     * canvas.drawOval（椭圆）
     * canvas.drawLine（线）
     * canvas.drawPoint（点）
     * canvas.drawRect（矩形）
     * canvas.drawRoundRect（圆角矩形）
     * canvas.drawVertices（顶点）
     * cnavas.drawPath（路径）
     * <p/>
     * 文字
     * canvas.drawText
     * <p/>
     * 图片
     * canvas.drawBitmap （位图）
     * canvas.drawPicture （图片）
     */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        clipRect(canvas);

        intersect(canvas);
    }

    /***
     * 相交区域进行绘制
     * 500,500到 750，750 交集出进行绘制
     * @param canvas
     */
    private void intersect(Canvas canvas) {
        canvas.drawColor(Color.BLUE);

        Rect rect = new Rect(0, 0, 500, 500);
        boolean intersect = rect.intersect(250, 250, 750, 750);
        if(intersect){
            canvas.clipRect(rect);
            canvas.drawColor(Color.RED);
        }
    }

    /**
     * 剪裁矩形
     * clipRect（） 将新选择的区域进行绘制，其他未制定区域不进行设置
     * canvas.clipRect(0, 0, 80, 80);//Rect
     */
    private void clipRect(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        canvas.clipRect(0, 0, 80, 80);
        canvas.drawColor(Color.RED);
    }


}
