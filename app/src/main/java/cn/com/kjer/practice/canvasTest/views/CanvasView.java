package cn.com.kjer.practice.canvasTest.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by kjer on 2016/3/18.
 */
public class CanvasView extends View {

    /**
     * Paint
     * setARGB(int a,int r,int g,int b)设置绘制的颜色，a代表透明度，r，g，b代表颜色值。
     * <p/>
     * setAlpha(int a);设置绘制图形的透明度。
     * <p/>
     * setColor(int color);设置绘制的颜色，使用颜色值来表示，该颜色值包括透明度和RGB颜色。
     * <p/>
     * setAntiAlias(boolean aa);设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
     * <p/>
     * setDither(boolean dither);设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
     * <p/>
     * setFilterBitmap(boolean filter);如果该项设置为true，则图像在动画进行中会滤掉对Bitmap图像的优化操作，
     * 加快显示速度，本设置项依赖于dither和xfermode的设置
     * <p/>
     * setMaskFilter(MaskFilter maskfilter);设置MaskFilter，可以用不同的MaskFilter实现滤镜的效果，如滤化，立体等       *
     * setColorFilter(ColorFilter colorfilter);设置颜色过滤器，可以在绘制颜色时实现不用颜色的变换效果
     * <p/>
     * setPathEffect(PathEffect effect);设置绘制路径的效果，如点画线等
     * <p/>
     * setShader(Shader shader);设置图像效果，使用Shader可以绘制出各种渐变效果
     * <p/>
     * setShadowLayer(float radius ,float dx,float dy,int color);在图形下面设置阴影层，产生阴影效果，radius为阴影的角度，
     * dx和dy为阴影在x轴和y轴上的距离，color为阴影的颜色
     * <p/>
     * setStyle(Paint.Style style);设置画笔的样式，为FILL，FILL_OR_STROKE，或STROKE
     * <p/>
     * setStrokeCap(Paint.Cap cap);当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆形样式Cap.ROUND,或方形样式Cap.SQUARE
     * <p/>
     * setSrokeJoin(Paint.Join join);设置绘制时各图形的结合方式，如平滑效果等
     * <p/>
     * setStrokeWidth(float width);当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的粗细度
     * <p/>
     * setXfermode(Xfermode xfermode);设置图形重叠时的处理方式，如合并，取交集或并集，经常用来制作橡皮的擦除效果
     * <p/>
     * 2.文本绘制
     * setFakeBoldText(boolean fakeBoldText);模拟实现粗体文字，设置在小字体上效果会非常差
     * <p/>
     * setSubpixelText(boolean subpixelText);设置该项为true，将有助于文本在LCD屏幕上的显示效果
     * <p/>
     * setTextAlign(Paint.Align align);设置绘制文字的对齐方向
     * <p/>
     * setTextScaleX(float scaleX);设置绘制文字x轴的缩放比例，可以实现文字的拉伸的效果
     * <p/>
     * setTextSize(float textSize);设置绘制文字的字号大小
     * <p/>
     * setTextSkewX(float skewX);设置斜体文字，skewX为倾斜弧度
     * <p/>
     * setTypeface(Typeface typeface);设置Typeface对象，即字体风格，包括粗体，斜体以及衬线体，非衬线体等
     * <p/>
     * setUnderlineText(boolean underlineText);设置带有下划线的文字效果
     * <p/>
     * setStrikeThruText(boolean strikeThruText);设置带有删除线的效果
     */
    private Paint mPaint;
    private Path mPath;

    /**
     * 是否path路径动画,控制int() 绘制图形
     */
    private boolean isAnimation = false;
    /**
     * 动画坐标
     */
    private float[] mCurrentPosition = new float[2];
    private PathMeasure mPathMeasure;


    public CanvasView(Context context) {
        super(context);
        init();
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    /**
     * canvas可以绘制的图形
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
        if (!isAnimation) {
            setPaint();
            setPath(canvas);
            setBezieCurves(canvas);
            //about canvas
//        clipRect(canvas);
//        intersect(canvas);
//            clipPath(canvas);

        } else {

            //动画动态绘制,与其他方法冲突
            drawAnima(canvas);
        }
    }


    private void setPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        //设置画笔宽度
        mPaint.setStrokeWidth(2);
        //消除锯齿
        mPaint.setAntiAlias(true);
        //设置镂空
        mPaint.setStyle(Paint.Style.STROKE);
    }

    /**************************************** path***************************************/

    /**
     * 设置path
     * FillType.WINDING：取path所有所在区域； 默认类型
     * FillType.EVEN_ODD：取path所在并不相交区域；
     * FillType.INVERSE_WINDING：取path所有未占区域；
     * FillType.INVERSE_EVEN_ODD：取path未占或相交区域；
     */
    private void setPath(Canvas canvas) {
        Path mPath = new Path();
        Rect rect = new Rect(300, 300, 500, 500);//设定矩形
        mPath.moveTo(100, 100);//设置起始位置偏移量
        mPath.lineTo(100, 200);//移动距离，x轴不变，y轴向上绘制100px
        mPath.lineTo(150, 300);
        mPath.lineTo(300, 300);
        mPath.close();//将坐标重置到原点，形成闭合回路
        mPath.reset();//重置path设置
        mPath.lineTo(500, 500);

        //addArc(RectF oval, float startAngle, float sweepAngle)
        //绘制圆弧，角度取自矩形内接圆（水平右为0度，顺时针绘制），startAngle起始度数，sweepAngle绘制结束度数
        mPath.addArc(new RectF(rect), 180, 90);
        //绘制圆弧，arcTo如果当前点坐标和曲线的起始点不是同一个点的话，还会自动添加一条直线补齐路径
        mPath.arcTo(new RectF(rect), 0, 90);

        //绘制圆形,xy为圆的圆心 radius为圆的半径，Direction 为绘制元的方向，Direction.CCW 逆时针方向，Direction.CW 顺时针方向
        mPath.addCircle(500, 500, 100, Path.Direction.CW);
        //addOval(RectF oval, Path.Direction dir)//同上绘制圆的方法

        //addPath(Path src, float dx, float dy) 将在已有的Path上通过平移创建新的path，xy偏移量

        canvas.drawPath(mPath, mPaint);//绘制路径(线)

        //改变画笔颜色，以区分和之前绘制
        mPaint.setColor(Color.BLUE);
        //绘制图形
        canvas.drawRect(rect, mPaint);//绘制矩形
//        canvas.drawOval(new RectF(rect), mPaint);//绘制椭圆（为矩形内切圆）


    }

    /**
     * 贝塞尔曲线相关
     * 二次贝塞尔曲线对应 quadTo(float x1,float y1,float x2, float y2)
     * 三次贝塞尔曲线对应 cubicTo(float x1,float y1, float x2, float y2, float x3,float y3)
     */
    private void setBezieCurves(Canvas canvas) {
        mPaint.setColor(Color.GREEN);

        mPath = new Path();
        //二次贝塞尔曲线
        mPath.moveTo(300, 270);
        // x1、y1 代表控制点的 x、y，x2、y2 代表目标点的 x、y；
        mPath.quadTo(150, 200, 300, 400);
        mPath.quadTo(450, 200, 300, 270);
        //三次贝塞尔曲线

        canvas.drawPath(mPath, mPaint);

    }


    /**
     * 路径动画
     */
    public void pathAnimation() {
        mPathMeasure = new PathMeasure(mPath, true);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(3000);//设置时常
        // 减速插值器
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                Log.d("canvasView", "value=" + value);
                // 获取当前点坐标封装到mCurrentPosition
                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    private void init() {
        if (isAnimation) {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(2);
            mPaint.setColor(Color.RED);

            mPath = new Path();
            //将起始位移动到对应位置
            mPath.moveTo(300, 270);
            //贝塞尔曲线
            mPath.quadTo(150, 200, 300, 400);
            mPath.quadTo(450, 200, 300, 270);

            mPathMeasure = new PathMeasure(mPath, true);
            mCurrentPosition = new float[2];
        }
    }

    /**
     * 动态绘制路径
     *
     * @param canvas
     */
    private void drawAnima(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(mPath, mPaint);
        // 绘制对应目标
        canvas.drawCircle(mCurrentPosition[0], mCurrentPosition[1], 10, mPaint);
    }


    /***************************************path over**********************************/


    /***************************************CANVAS*************************************/
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

    /***
     * 相交区域进行绘制
     * 500,500到 750，750 交集出进行绘制
     *
     * @param canvas
     */
    private void intersect(Canvas canvas) {
        canvas.drawColor(Color.BLUE);

        Rect rect = new Rect(0, 0, 500, 500);
        boolean intersect = rect.intersect(150, 150, 750, 750);
        if (intersect) {
            canvas.clipRect(rect);
            canvas.drawColor(Color.RED);
        }
    }


    /**
     * quadTo 用于绘制圆滑曲线，即贝塞尔曲线。
     * mPath.quadTo(x1, y1, x2, y2) (x1,y1) 为控制点，(x2,y2)为结束点
     * <p/>
     * cubicTo 同样是用来实现贝塞尔曲线的,
     * mPath.cubicTo(x1, y1, x2, y2, x3, y3) (x1,y1) 为控制点，(x2,y2)为控制点，(x3,y3) 为结束点。
     * <p/>
     */
    private void clipPath(Canvas canvas) {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

        // 实例化路径
        Path mPath = new Path();
        // 移动起点至[100,50],通过移动位置，绘制自定义图形
        mPath.moveTo(100, 50);
        mPath.lineTo(200, 300);
        mPath.lineTo(200, 150);
        mPath.lineTo(50, 60);
        // 闭合路径
        mPath.close();

        // 在原始画布上绘制蓝色
        canvas.drawColor(Color.BLUE);
        // 按照路径进行裁剪
        canvas.clipPath(mPath);
        // 在裁剪后剩余的画布上绘制红色
        canvas.drawColor(Color.RED);
    }

    /***************************************CANVAS OVER**********************************/

}
