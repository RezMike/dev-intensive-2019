package ru.skillbranch.devintensive.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.annotation.Dimension.DP
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import ru.skillbranch.devintensive.R
import kotlin.math.min
import kotlin.math.pow

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyle: Int = 0
) : AppCompatImageView(context, attrs, defStyle) {

    companion object {
        private val SCALE_TYPE = ScaleType.CENTER_CROP
        private val BITMAP_CONFIG = Bitmap.Config.ARGB_8888
        private const val COLOR_DRAWABLE_DIMENSION = 2
        private const val DEFAULT_BORDER_WIDTH = 2
        private const val DEFAULT_BORDER_COLOR = Color.WHITE
        private const val DEFAULT_CIRCLE_BACKGROUND_COLOR = Color.TRANSPARENT
    }

    var initials: String? = null
        set(value) {
            if (field == value) return
            field = value
            if (value != null) {
                setImageDrawable(InitialsDrawable(value, context))
            } else {
                setImageResource(R.drawable.avatar_default)
            }
        }

    private val drawableRect = RectF()
    private val borderRect = RectF()

    private val shaderMatrix = Matrix()
    private val bitmapPaint = Paint()
    private val borderPaint = Paint()
    private val circleBackgroundPaint = Paint()

    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidthDp = DEFAULT_BORDER_WIDTH
    private val circleBackgroundColor = DEFAULT_CIRCLE_BACKGROUND_COLOR

    private var bitmap: Bitmap? = null
    private var bitmapShader: BitmapShader? = null
    private var bitmapWidth: Int = 0
    private var bitmapHeight: Int = 0

    private var drawableRadius: Float = 0.toFloat()
    private var borderRadius: Float = 0.toFloat()

    private var colorFilter: ColorFilter? = null

    private var isReady: Boolean = false
    private var isSetupPending: Boolean = false
    private var isBorderOverlay = false
    private val isDisableCircularTransformation: Boolean = false

    fun getBorderColor() = borderColor

    fun setBorderColor(hex: String) {
        val color = Color.parseColor(hex)
        setColor(color)
    }

    fun setBorderColor(@ColorRes colorId: Int) {
        val color = resources.getColor(colorId, context.theme)
        setColor(color)
    }

    private fun setColor(color: Int) {
        if (color == borderColor) return
        borderColor = color
        borderPaint.color = borderColor
        invalidate()
    }

    @Dimension(unit = DP)
    fun getBorderWidth() = borderWidthDp

    fun setBorderWidth(@Dimension(unit = DP) dp: Int) {
        if (dp == borderWidthDp) return
        borderWidthDp = dp
        setup()
    }

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0)

        borderWidthDp = a.getDimensionPixelSize(R.styleable.CircleImageView_cv_borderWidth, DEFAULT_BORDER_WIDTH)
        borderColor = a.getColor(R.styleable.CircleImageView_cv_borderColor, DEFAULT_BORDER_COLOR)

        a.recycle()

        super.setScaleType(SCALE_TYPE)
        isReady = true

        outlineProvider = OutlineProvider()

        if (isSetupPending) {
            setup()
            isSetupPending = false;
        }
    }

    override fun getScaleType() = SCALE_TYPE

    override fun setScaleType(scaleType: ScaleType) {
        if (scaleType != SCALE_TYPE) {
            throw IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType))
        }
    }

    override fun setAdjustViewBounds(adjustViewBounds: Boolean) {
        if (adjustViewBounds) {
            throw IllegalArgumentException("adjustViewBounds not supported.")
        }
    }

    override fun onDraw(canvas: Canvas) {
        if (bitmap == null) return

        canvas.drawCircle(drawableRect.centerX(), drawableRect.centerY(), drawableRadius, bitmapPaint)
        if (borderWidthDp > 0) {
            canvas.drawCircle(borderRect.centerX(), borderRect.centerY(), borderRadius, borderPaint)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setup()
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        super.setPadding(left, top, right, bottom)
        setup()
    }

    override fun setPaddingRelative(start: Int, top: Int, end: Int, bottom: Int) {
        super.setPaddingRelative(start, top, end, bottom)
        setup()
    }

    override fun setImageBitmap(bm: Bitmap) {
        super.setImageBitmap(bm)
        initializeBitmap()
    }

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        initializeBitmap()
    }

    override fun setImageResource(@DrawableRes resId: Int) {
        super.setImageResource(resId)
        initializeBitmap()
    }

    override fun setImageURI(uri: Uri?) {
        super.setImageURI(uri)
        initializeBitmap()
    }

    override fun setColorFilter(cf: ColorFilter) {
        if (cf === colorFilter) return

        colorFilter = cf
        applyColorFilter()
        invalidate()
    }

    override fun getColorFilter() = colorFilter

    private fun applyColorFilter() {
        bitmapPaint.colorFilter = colorFilter
    }

    private fun getBitmapFromDrawable(drawable: Drawable?): Bitmap? {
        if (drawable == null) return null

        if (drawable is BitmapDrawable) return drawable.bitmap

        try {
            val bitmap: Bitmap

            if (drawable is ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLOR_DRAWABLE_DIMENSION, COLOR_DRAWABLE_DIMENSION, BITMAP_CONFIG)
            } else {
                bitmap = Bitmap.createBitmap(drawable.bounds.width(), drawable.bounds.height(), BITMAP_CONFIG)
            }

            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    private fun initializeBitmap() {
        bitmap = getBitmapFromDrawable(drawable)
        setup()
    }

    private fun setup() {
        if (!isReady) {
            isSetupPending = true
            return
        }

        if (width == 0 && height == 0) return

        if (bitmap == null) {
            invalidate()
            return
        }

        bitmapShader = BitmapShader(bitmap!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        bitmapPaint.isAntiAlias = true
        bitmapPaint.shader = bitmapShader

        val borderWidth = borderWidthDp * resources.displayMetrics.density
        borderPaint.style = Paint.Style.STROKE
        borderPaint.isAntiAlias = true
        borderPaint.color = borderColor
        borderPaint.strokeWidth = borderWidth

        circleBackgroundPaint.style = Paint.Style.FILL
        circleBackgroundPaint.isAntiAlias = true
        circleBackgroundPaint.color = circleBackgroundColor

        bitmapHeight = bitmap!!.height
        bitmapWidth = bitmap!!.width

        borderRect.set(calculateBounds())
        borderRadius = min((borderRect.height() - borderWidth) / 2.0f, (borderRect.width() - borderWidth) / 2.0f)

        drawableRect.set(borderRect)
        if (!isBorderOverlay && borderWidth > 0) {
            drawableRect.inset(borderWidth - 1.0f, borderWidth - 1.0f)
        }
        drawableRadius = min(drawableRect.height() / 2.0f, drawableRect.width() / 2.0f)

        applyColorFilter()
        updateShaderMatrix()
        invalidate()
    }

    private fun calculateBounds(): RectF {
        val availableWidth = width - paddingLeft - paddingRight
        val availableHeight = height - paddingTop - paddingBottom

        val sideLength = min(availableWidth, availableHeight)

        val left = paddingLeft + (availableWidth - sideLength) / 2f
        val top = paddingTop + (availableHeight - sideLength) / 2f

        return RectF(left, top, left + sideLength, top + sideLength)
    }

    private fun updateShaderMatrix() {
        val scale: Float
        var dx = 0f
        var dy = 0f

        shaderMatrix.set(null)

        if (bitmapWidth * drawableRect.height() > drawableRect.width() * bitmapHeight) {
            scale = drawableRect.height() / bitmapHeight.toFloat()
            dx = (drawableRect.width() - bitmapWidth * scale) * 0.5f
        } else {
            scale = drawableRect.width() / bitmapWidth.toFloat()
            dy = (drawableRect.height() - bitmapHeight * scale) * 0.5f
        }

        shaderMatrix.setScale(scale, scale)
        shaderMatrix.postTranslate((dx + 0.5f).toInt() + drawableRect.left, (dy + 0.5f).toInt() + drawableRect.top)

        bitmapShader!!.setLocalMatrix(shaderMatrix)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return inTouchableArea(event.x, event.y) && super.onTouchEvent(event)
    }

    private fun inTouchableArea(x: Float, y: Float): Boolean {
        val x2 = (x - borderRect.centerX()).toDouble().pow(2.0)
        val y2 = (y - borderRect.centerY()).toDouble().pow(2.0)
        return x2 + y2 <= borderRadius.toDouble().pow(2.0)
    }

    private inner class OutlineProvider : ViewOutlineProvider() {

        override fun getOutline(view: View, outline: Outline) {
            val bounds = Rect()
            borderRect.roundOut(bounds)
            outline.setRoundRect(bounds, bounds.width() / 2f)
        }
    }
}

class InitialsDrawable(
    private val initials: String,
    private val context: Context
) : Drawable() {

    private val paint = Paint()

    init {
        val size = 112 * context.resources.displayMetrics.density
        setBounds(0, 0, size.toInt(), size.toInt())

        val textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 48f, context.resources.displayMetrics)
        paint.textSize = textSize
        paint.isAntiAlias = true
        paint.isFakeBoldText = true
        paint.setShadowLayer(6f, 0f, 0f, Color.BLACK)
        paint.style = Paint.Style.FILL
        paint.textAlign = Paint.Align.CENTER
    }

    override fun draw(canvas: Canvas) {
        paint.color = context.resources.getColor(R.color.color_accent, context.theme)
        canvas.drawRect(0f, 0f, bounds.width().toFloat(), bounds.height().toFloat(), paint)

        paint.color = Color.WHITE
        canvas.drawText(
            initials, 0, initials.length, bounds.centerX().toFloat(),
            bounds.centerY().toFloat() - paint.ascent() / 2, paint
        )
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }
}
