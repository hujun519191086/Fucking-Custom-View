package com.darren.custom.v13

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import com.darren.custom.R
import kotlin.math.sqrt

/**
date  6/3/21  4:10 PM
author  DarrenHang
 */
class NineView : View {

    //是否初始化
    private var isInit = false

    private lateinit var linePaint: Paint//线画笔
    private lateinit var pressPaint: Paint//按压画笔
    private lateinit var errorPaint: Paint//错误画笔
    private lateinit var normalPaint: Paint//默认画笔
    private lateinit var arrowPaint: Paint

    //外圆颜色
    private var outDotColor = Color.RED

    //内圆颜色
    private var inDotColor = Color.BLUE

    //外圆大小
    private var outDotWidth = 0f

    //内圆大小
    private var inDotWidth = 0f

    //圆环大小
    private var dotWidth = 2f

    private var isTouchPoint = false
    private var viewStatus = -1
    private var selectPoint = ArrayList<Point>()
    private val mPoints: Array<Array<Point?>> = Array(3) { Array<Point?>(3) { null } }

    private var callBack: CallBack? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttr(context, attrs)
        initPaint()
    }

    fun setCallBack(callBack: CallBack){
        this.callBack = callBack
    }

    //初始化属性
    private fun initAttr(context: Context, attrs: AttributeSet?) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.NineView)
        //外圆颜色
        outDotColor = array.getColor(R.styleable.NineView_outDotColor, outDotColor)
        //内圆颜色
        inDotColor = array.getColor(R.styleable.NineView_inDotColor, inDotColor)
        //圆环大小
        dotWidth = array.getDimension(R.styleable.NineView_dotWidth, dp2px(dotWidth))
        //回收
        array.recycle()
    }

    //绘制
    override fun onDraw(canvas: Canvas?) {
        //画9宫格
        if (!isInit) {
            initDot()
            isInit = true
        }
        drawDot(canvas!!)
        drawLine(canvas)
    }

    //初始化画笔
    private fun initPaint() {
        //默认画笔
        normalPaint = Paint()
        normalPaint.style = Paint.Style.STROKE
        normalPaint.isAntiAlias = true
        normalPaint.isDither = true
        normalPaint.strokeWidth = dotWidth

        //按压画笔
        pressPaint = Paint()
        pressPaint.style = Paint.Style.STROKE
        pressPaint.isAntiAlias = true
        pressPaint.isDither = true
        pressPaint.strokeWidth = dotWidth

        //错误画笔
        errorPaint = Paint()
        errorPaint.style = Paint.Style.STROKE
        errorPaint.isAntiAlias = true
        errorPaint.isDither = true
        errorPaint.strokeWidth = dotWidth

        //正确画笔
        arrowPaint = Paint()
        arrowPaint.style = Paint.Style.STROKE
        arrowPaint.isAntiAlias = true
        arrowPaint.isDither = true
        arrowPaint.strokeWidth = dotWidth

        linePaint = Paint()
        linePaint.style = Paint.Style.FILL_AND_STROKE
        linePaint.isAntiAlias = true
        linePaint.isDither = true
        linePaint.strokeWidth = dotWidth
    }

    //初始化点
    private fun initDot() {
        //初始化9宫格
        //记录点的状态，回掉密码

        //九宫格总宽度
        var width = this.width
        //九宫格总高度
        var height = this.height

        var offSetY = 0
        var offSetX = 0
        //兼容横竖屏
        if (height > width) {
            //Y轴偏移量
            offSetY = (height - width) / 2
            height = width
        } else {
            //X轴偏移量
            offSetX = (width - height) / 2
            width = height
        }

        //单元格宽度
        var pointWidth = width / 3

        //外圆大小
        outDotWidth = width / 12f
        //内圆大小
        inDotWidth = outDotWidth / 6

        //点的位置
        mPoints[0][0] = Point(offSetX + pointWidth / 2, offSetY + pointWidth / 2, 0)
        mPoints[0][1] = Point(offSetX + pointWidth * 3 / 2, offSetY + pointWidth / 2, 1)
        mPoints[0][2] = Point(offSetX + pointWidth * 5 / 2, offSetY + pointWidth / 2, 2)
        mPoints[1][0] = Point(offSetX + pointWidth / 2, offSetY + pointWidth * 3 / 2, 3)
        mPoints[1][1] = Point(offSetX + pointWidth * 3 / 2, offSetY + pointWidth * 3 / 2, 4)
        mPoints[1][2] = Point(offSetX + pointWidth * 5 / 2, offSetY + pointWidth * 3 / 2, 5)
        mPoints[2][0] = Point(offSetX + pointWidth / 2, offSetY + pointWidth * 5 / 2, 6)
        mPoints[2][1] = Point(offSetX + pointWidth * 3 / 2, offSetY + pointWidth * 5 / 2, 7)
        mPoints[2][2] = Point(offSetX + pointWidth * 5 / 2, offSetY + pointWidth * 5 / 2, 8)
    }

    class Point(var centerX: Int, var centerY: Int, var index: Int) {


        companion object {
            //默认状态
            const val STATUS_NORMAL = 1

            //按压状态
            const val STATUS_PRESSED = 2

            //错误状态
            const val STATUS_ERROR = 3

            //正确状态
            const val STATUS_RIGHT = 4
        }

        //当前点的状态
        private var status = STATUS_NORMAL

        fun setStatusNormal() {
            status = STATUS_NORMAL
        }

        fun setStatusPress() {
            status = STATUS_PRESSED
        }

        fun setStatusError() {
            status = STATUS_ERROR
        }

        fun setStatusRight() {
            status = STATUS_RIGHT
        }

        fun statusNormal(): Boolean {
            return status == STATUS_NORMAL
        }

        fun statusPressed(): Boolean {
            return status == STATUS_PRESSED
        }

        fun statusError(): Boolean {
            return status == STATUS_ERROR
        }

        fun statusRight(): Boolean {
            return status == STATUS_RIGHT
        }

    }

    /**
     * 绘制九宫格
     */
    private fun drawDot(canvas: Canvas) {
        for (pointList in mPoints) {
            for (point in pointList) {
                if (point!!.statusNormal()) {
                    //绘制外圆
                    drawOutCircle(canvas, point!!, outDotColor, normalPaint)
                    //绘制内圆
                    drawInCircle(canvas, point!!, inDotColor, normalPaint)
                }

                if (point!!.statusPressed()) {
                    //绘制外圆
                    drawOutCircle(canvas, point!!, Color.BLACK, pressPaint)
                    //绘制内圆
                    drawInCircle(canvas, point!!, Color.BLACK, pressPaint)
                }

                if (point!!.statusError()) {
                    //绘制外圆
                    drawOutCircle(canvas, point!!, Color.RED, errorPaint)
                    //绘制内圆
                    drawInCircle(canvas, point!!, Color.RED, errorPaint)
                }

                if (point!!.statusRight()) {
                    //绘制外圆
                    drawOutCircle(canvas, point!!, Color.GREEN, arrowPaint)
                    //绘制内圆
                    drawInCircle(canvas, point!!, Color.GREEN, arrowPaint)
                }
            }
        }
    }

    /**
     * 绘制线
     */
    private fun drawLine(canvas: Canvas) {

        if (selectPoint.size >= 1) {

            when (viewStatus) {
                0 ->
                    linePaint.color = Color.RED

                1 ->
                    linePaint.color = Color.GREEN

                else -> linePaint.color = Color.BLACK
            }

            var lastPoint = selectPoint[0]

            //两点之间绘制线和箭头
            for (point in selectPoint) {
                drawLine(lastPoint, point, canvas, linePaint)
                lastPoint = point
            }

            if (selectPoint.size != 9 && isTouchPoint) {
                drawLine(lastPoint, Point(mMoveX.toInt(), mMoveY.toInt(), -1), canvas, linePaint)
            }
        }
    }

    /**
     * 画外圆
     */
    private fun drawOutCircle(canvas: Canvas, point: Point, color: Int, paint: Paint) {
        paint.color = color
        canvas.drawCircle(point.centerX.toFloat(), point.centerY.toFloat(), outDotWidth, paint)
    }

    /**
     * 画内圆
     */
    private fun drawInCircle(canvas: Canvas, point: Point, color: Int, paint: Paint) {
        paint.color = color
        canvas.drawCircle(point.centerX.toFloat(), point.centerY.toFloat(), inDotWidth, paint)
    }

    /**
     * 画线
     */
    private fun drawLine(start: Point, end: Point, canvas: Canvas, paint: Paint) {
        //拿到2点的坐标和坐标差
        val startX = start.centerX
        val startY = start.centerY
        val endX = end.centerX
        val endY = end.centerY
        val dx = endX - startX
        val dy = endY - startY
        //计算2点的位置
        val d = (sqrt((dx * dx + dy * dy).toDouble())).toFloat()

        val rx = dx / d * inDotWidth
        val ry = dy / d * inDotWidth

        canvas.drawLine(startX + rx, startY + ry, endX - rx, endY - ry, paint)

    }

    private fun dp2px(sp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sp, resources.displayMetrics)
    }


    private var mMoveX = 0f
    private var mMoveY = 0f

    override fun onTouchEvent(event: MotionEvent): Boolean {

        mMoveX = event.x
        mMoveY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                //判断触摸的位置是否在圆里面 -> 点到圆心的位置小于半径
                val point = point
                if (point != null) {
                    isTouchPoint = true
                    selectPoint.add(point)
                    point.setStatusPress()
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (isTouchPoint) {
                    val point = point
                    if (point != null) {
                        if (!selectPoint.contains(point)) {
                            selectPoint.add(point)
                        }
                        point.setStatusPress()
                    }
                }

            }
            MotionEvent.ACTION_UP -> {
                isTouchPoint = false
                when {
                    selectPoint.size == 1 -> {
                        // 清空选择
                        clearSelectPoints()
                    }
                    selectPoint.size <= 4 -> {
                        showSelectError()
                    }
                    else -> {
                        showSelectRight()
                        val sb = StringBuffer()
                        selectPoint.forEach { point ->
                            sb.append(point.index)
                        }
                        callBack?.password(sb.toString())
                    }
                }
            }
        }
        invalidate()
        return true
    }

    private val point: Point?
        get() {
            for (i in 0..2) {
                for (point in mPoints[i]) {
                    if (checkInCircle(mMoveX, mMoveY, outDotWidth, point!!.centerX.toFloat(), point!!.centerY.toFloat()))
                        return point
                }
            }
            return null
        }

    /**
     * 检查触摸的点是否在圆内
     */
    private fun checkInCircle(mx: Float, my: Float, r: Float, x: Float, y: Float): Boolean {
        val offSetX = mx - x
        val offSetY = my - y
        return sqrt((offSetX * offSetX + offSetY * offSetY).toDouble()) < r
    }

    /**
     * 清空所有的点
     */
    private fun clearSelectPoints() {
        for (mSelectPoint in selectPoint) {
            mSelectPoint.setStatusNormal()
        }
        selectPoint.clear()
    }

    /**
     * 显示错误
     */
    private fun showSelectError() {
        for (mSelectPoint in selectPoint) {
            mSelectPoint.setStatusError()
            viewStatus = 0
        }

        postDelayed({
            clearSelectPoints()
            viewStatus = -1
            invalidate()
        }, 1000)
    }

    /**
     * 显示正确
     */
    private fun showSelectRight() {
        for (mSelectPoint in selectPoint) {
            mSelectPoint.setStatusRight()
            viewStatus = 1
        }

        postDelayed({
            clearSelectPoints()
            viewStatus = -1
            invalidate()
        }, 1000)
    }

    interface CallBack{
        fun password(pwd:String);
    }

}