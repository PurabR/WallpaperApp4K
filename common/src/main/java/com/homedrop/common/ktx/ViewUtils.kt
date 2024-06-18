package com.homedrop.common.ktx

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast

const val MM_PER_INCH = 25.4f

fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

fun spToPx(sp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp,
        Resources.getSystem().displayMetrics
    )
}

fun inchToPx(inch: Float): Int {
    return mmToPx(inch * MM_PER_INCH)
}

fun mmToPx(mm: Float): Int {
    val metrics = Resources.getSystem().displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, mm, metrics).toInt()
}

fun Context?.showShortToast(text: String?) {
    if (this == null || text.isNullOrEmpty()) return
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Activity?.showShortToast(text: String?) {
    if (this == null || text.isNullOrEmpty()) return
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}