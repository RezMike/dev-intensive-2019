package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import ru.skillbranch.devintensive.R

class InitialsDrawable(
    private val initials: String,
    initialsSize: Int,
    drawableSize: Int,
    private val context: Context
) : Drawable() {

    private val paint = Paint()

    init {
        setBounds(0, 0, drawableSize, drawableSize)

        paint.textSize = initialsSize.toFloat()
        paint.isAntiAlias = true
        paint.isFakeBoldText = true
        paint.style = Paint.Style.FILL
        paint.textAlign = Paint.Align.CENTER
    }

    override fun draw(canvas: Canvas) {
        paint.color = context.resources.getColor(R.color.color_accent, context.theme)
        canvas.drawRect(0f, 0f, bounds.width().toFloat(), bounds.height().toFloat(), paint)

        paint.color = Color.WHITE
        canvas.drawText(
            initials, 0, initials.length, bounds.centerX().toFloat(),
            bounds.centerY().toFloat() - paint.ascent() * .4f, paint
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
