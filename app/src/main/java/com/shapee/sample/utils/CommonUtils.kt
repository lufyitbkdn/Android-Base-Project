package com.shapee.sample.utils

import android.content.Context
import com.shapee.sample.R
import java.util.*

/**
 * Created by Alaa Moataz on 9/20/20.
 */
object CommonUtils {
    fun getDateAgoText(date: Date, context: Context): String {
        val diff = Date().time - date.time
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val months = days / 30
        val years = months / 12

        if (years > 0L)
            return context.resources.getQuantityString(R.plurals.years_ago, years.toInt(), years)

        if (months > 0L)
            return context.resources.getQuantityString(R.plurals.months_ago, months.toInt(), months)

        if (days > 0L)
            return context.resources.getQuantityString(R.plurals.days_ago, days.toInt(), days)

        if (hours > 0L)
            return context.resources.getQuantityString(R.plurals.hours_ago, hours.toInt(), hours)

        if (minutes > 0L)
            return context.resources.getQuantityString(
                R.plurals.minutes_ago,
                minutes.toInt(),
                minutes
            )

        if (seconds > 0L)
            return context.resources.getQuantityString(
                R.plurals.seconds_ago,
                seconds.toInt(),
                seconds
            )

        return context.getString(R.string.just_now)
    }

    fun floatToString(double: Double, precision: Int = 2): String {
        val formatter: String = ("%." + precision + "f").format(double)
        return formatter.replace("0*$".toRegex(), "")
            .replace("\\.$".toRegex(), "")
    }


    @JvmStatic
    fun doubleToString(double: Double): String {
        return if(double.toLong().toDouble() == double){
            double.toLong().toString()
        }else{
            double.toString()
        }
    }
}