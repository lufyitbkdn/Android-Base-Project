package com.shapee.android.utils

import android.content.Context
import android.graphics.Bitmap
import android.location.Geocoder
import android.net.ParseException
import android.net.Uri
import android.os.SystemClock
import android.provider.MediaStore
import android.text.format.DateUtils
import android.view.View
import java.io.File
import java.io.FileOutputStream
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.*


object Utils {
    val offsetInMillis: Int
        get() {
            val tz = TimeZone.getDefault()
            val cal = GregorianCalendar.getInstance(tz)
            return tz.getOffset(cal.timeInMillis)
        }

    private const val salesTax = 10.0F
    private const val importDuty = 5.0F
    private val timeNumberFormatter = DecimalFormat("00")
    var decimalFormatter = DecimalFormat(
        "###,##0.0#",
        DecimalFormatSymbols(Constants.DEFAULT_LOCALE)
    )
    var decimalFormatterNotDec = DecimalFormat(
        "###,##0",
        DecimalFormatSymbols(Constants.DEFAULT_LOCALE)
    )

    fun getImportDuty(price: Float): Float {
        return (price * importDuty / 100)
    }

    fun getSalesTax(price: Float): Float {
        return (price * salesTax / 100)
    }

    fun formatDecimal(value: Float): String {
        val df = DecimalFormat("#,##,###.00")
        df.roundingMode = RoundingMode.CEILING
        return df.format((value * 20f).roundToInt() / 20f)
    }

    fun getTimeZone(): Long {
        val calendar: Calendar = GregorianCalendar()
        val gmtOffset: Int = calendar.timeZone.rawOffset
        return TimeUnit.HOURS.convert(gmtOffset.toLong(), TimeUnit.MILLISECONDS)
    }

    fun timeToString(timeInMillisecond: Long): String {
        val seconds = (timeInMillisecond / 1000f).roundToInt() % 60
        val hours = (timeInMillisecond / 1000f).roundToInt() / 3600
        val minutes = ((timeInMillisecond / 1000f).roundToInt() / 60) % 60
        return if (hours == 0) {
            minutes.toString()
                .plus(":")
                .plus(timeNumberFormatter.format(seconds))
        } else {
            hours.toString()
                .plus(":")
                .plus(timeNumberFormatter.format(minutes))
                .plus(":")
                .plus(timeNumberFormatter.format(seconds))
        }
    }

    fun minutesToString(minutes: Int): String {
        val hours = minutes / 60
        return hours.toString()
            .plus(":")
            .plus(timeNumberFormatter.format(minutes % 60))
    }

    fun getTempPicture(context: Context, generateFileName: Boolean): File {
        val dir = context.getExternalFilesDir("tmp")
        return File(dir, if (generateFileName) Date().time.toString().plus(".jpg") else "temp.jpg")
    }

    fun bitmapToFile(bitmap: Bitmap, file: File) {
        var outputStream: FileOutputStream? = null
        try {
            outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        } finally {
            outputStream?.close()
        }
    }

    fun getRealPathFromURI(context: Context, contentUri: Uri): String? {
        val cursor = context.contentResolver.query(contentUri, null, null, null, null)
        val path = if (cursor == null) {
            contentUri.path
        } else {
            cursor.moveToFirst()
            val index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            cursor.getString(index)
        }
        cursor?.close()
        return path
    }

    fun distanceInMeter(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val r = 6371000.0 // Radius of the earth in m
        val dLat = (lat1 - lat2) * Math.PI / 180f
        val dLon = (lon1 - lon2) * Math.PI / 180f
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(lat1 * Math.PI / 180f) * cos(lat2 * Math.PI / 180f) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2f * atan2(sqrt(a), sqrt(1 - a))
        return r * c
    }

    fun doubleToDecimalString(value: Double): String? {
        return if (value % 1 == 0.0) {
            decimalFormatterNotDec.format(value)
        } else decimalFormatter.format(value)
    }
    fun getDateStringFromTimestamp(timestamp: Long): String {
        if (timestamp == 0L) {
            return ""
        }
        val mSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd kk:mm")
        return mSimpleDateFormat.format(Date(timestamp + offsetInMillis))
    }

    fun getTimeFromTimestamp(time: Long): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(time)
    }

    fun getDateFromTimestamp(time: Long): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(time)
    }

    fun View.setSingleClickListener(action: () -> Unit) {
        this.setOnClickListener(object : View.OnClickListener {
            private var lastClickTime: Long = 0
            override fun onClick(v: View) {
                if (SystemClock.elapsedRealtime() - lastClickTime < 1000L) {
                    return
                }
                action()
                lastClickTime = SystemClock.elapsedRealtime()
            }
        })
    }

    fun getAddress(context: Context, lat: Double, lon: Double): String {
        val geo = Geocoder(context, Locale.getDefault())
        val listAddress = geo.getFromLocation(lat, lon, 1)
        return if (listAddress[0].postalCode != null) {
            listAddress[0].getAddressLine(0)
                .replace(listAddress[0].postalCode, "")
        } else {
            listAddress[0].getAddressLine(0)
        }
    }

    fun formatPhoneString(phoneNumber: String): String? {
        val arrPhone = phoneNumber.split(" ")
        val areaStringBuilder = StringBuilder()
        val phoneFormat: String
        val countryCode = arrPhone[0]
        val chars = arrPhone[1].toCharArray()

        areaStringBuilder.append("(")

        for (i in chars.indices) {
            areaStringBuilder.append(chars[i])
            if (i == 2) {
                areaStringBuilder.append(") ")
            }
            if (i == 5) {
                areaStringBuilder.append(" - ")

            }
            if (i == 7) {
                areaStringBuilder.append(" - ")

            }
        }
        phoneFormat = countryCode.plus(" ").plus(areaStringBuilder)

        return phoneFormat
    }

    fun formatDayTime(dateTime: String): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return try {
            if (dateTime.isNotEmpty()) {
                if (dateTime == "1970-01-01 00:00:00 UTC") {
                    null
                } else {
                    format.parse(dateTime)
                }
            } else {
                null
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun isTomorrow(d: Date): Boolean {
        return DateUtils.isToday(d.time - DateUtils.DAY_IN_MILLIS)
    }

    fun isYesterday(d: Date): Boolean {
        return DateUtils.isToday(d.time + DateUtils.DAY_IN_MILLIS)
    }
}
