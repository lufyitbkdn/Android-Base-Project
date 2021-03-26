package com.shapee.sample.utils.extensions

import java.text.SimpleDateFormat
import java.util.*


fun Date.dayMonthYear(): String =
    SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(this)

fun Date.weekOfYear(): Int{
    val c = Calendar.getInstance()
    c.setTime(this)
    return c[Calendar.WEEK_OF_YEAR]
}
fun Date.shortDayMothYear(): String =
    SimpleDateFormat("dd MMM yy", Locale.getDefault()).format(this)