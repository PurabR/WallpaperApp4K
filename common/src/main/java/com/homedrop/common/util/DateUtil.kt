package com.homedrop.common.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.round

object DateUtil {

    const val MILLIS_IN_HOUR = 3600000L
    const val MILLIS_IN_DAY = 86400000L
    const val MILLIS_IN_WEEK = MILLIS_IN_DAY * 7
    const val MILLIS_IN_MONTH = 2592000000

    fun getMidnightTimeFromDay1ThisMonth(): Long {
        val day = Calendar.getInstance()
        day[Calendar.MILLISECOND] = 0
        day[Calendar.SECOND] = 0
        day[Calendar.MINUTE] = 0
        day[Calendar.HOUR_OF_DAY] = 0
        day[Calendar.DAY_OF_MONTH] = 1
        return day.time.time
    }

    fun getLastMonthTimestamp(): Pair<Long, Long> {
        val day = Calendar.getInstance()
        day[Calendar.MILLISECOND] = 0
        day[Calendar.SECOND] = 0
        day[Calendar.MINUTE] = 0
        day[Calendar.HOUR_OF_DAY] = 0
        day[Calendar.DAY_OF_MONTH] = 1
        day.add(Calendar.MONTH, -1)
        val start = day.time.time

        day.add(Calendar.MONTH, 1)
        day.add(Calendar.DAY_OF_MONTH, -1)
        val end = day.time.time
        return Pair(start, end)
    }

    fun getMidnightTimeAgo1Week(): Long {
        val day = Calendar.getInstance()
        day[Calendar.MILLISECOND] = 0
        day[Calendar.SECOND] = 0
        day[Calendar.MINUTE] = 0
        day[Calendar.HOUR_OF_DAY] = 0
        return day.time.time - MILLIS_IN_WEEK
    }

    fun getMidnightTimeThisWeek(): Long {
        val day = Calendar.getInstance()
        day.firstDayOfWeek = Calendar.MONDAY
        day[Calendar.MILLISECOND] = 0
        day[Calendar.SECOND] = 0
        day[Calendar.MINUTE] = 0
        day[Calendar.HOUR_OF_DAY] = 0
        day[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
        return day.time.time
    }

    fun getMidnightTimeLastWeek(): Long {
        val day = Calendar.getInstance()
        day.firstDayOfWeek = Calendar.MONDAY
        day[Calendar.MILLISECOND] = 0
        day[Calendar.SECOND] = 0
        day[Calendar.MINUTE] = 0
        day[Calendar.HOUR_OF_DAY] = 0
        day.add(Calendar.WEEK_OF_YEAR, -1)
        day[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
        return day.time.time
    }

    fun getMidnightTime(): Long {
        val day = Calendar.getInstance()
        day[Calendar.MILLISECOND] = 0
        day[Calendar.SECOND] = 0
        day[Calendar.MINUTE] = 0
        day[Calendar.HOUR_OF_DAY] = 0
        return day.time.time
    }

    fun minutesToReadable(min: Int, is24HourFormat: Boolean = false): String {
        var hr = min / 60
        var suffix = "AM"
        if (!is24HourFormat && hr > 12) {
            suffix = "PM"
            hr -= 12
        }
        var m = (min % 60).toString()
        if (m.length == 1) {
            m = "0$m"
        }
        return buildString {
            append(hr)
            append(":")
            append(m)
            if (!is24HourFormat) {
                append(" ")
                append(suffix)
            }
        }
    }

    fun getWeekDay(millis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            Calendar.SUNDAY -> "Sunday"
            else -> ""
        }
    }

    fun getDayOfMonthSuffix(day: Int): String {
        if (day in 1..31) {
            return if (day in 11..13) {
                "th"
            } else when (day % 10) {
                1 -> "st"
                2 -> "nd"
                3 -> "rd"
                else -> "th"
            }
        } else {
            throw IllegalArgumentException("illegal day of month: $day")
        }
    }

    fun convertDateFormat(patternFrom: String, patternTo: String, dateString: String = ""): String {
        val dateFormatFrom = SimpleDateFormat(patternFrom, Locale.getDefault())
        val dateFormatTo = SimpleDateFormat(patternTo, Locale.getDefault())
        try {
            val date = dateFormatFrom.parse(dateString)
            date?.let {
                return dateFormatTo.format(date)
            }
        } catch (e: ParseException) {
        }
        return dateString
    }

    /**
     * @param milliseconds - This should be the UNIX timestamp
     */
    fun createDateFormat(pattern: String, milliseconds: Long): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return dateFormat.format(Date(milliseconds * 1000))
    }

    /**
     * this - This should be the UNIX timestamp
     */
    fun Long.getDayAgoTime(): String {
        val currentDateCalendar = Calendar.getInstance()
        val startDateCalendar = Calendar.getInstance()
        startDateCalendar.timeInMillis = this * 1000

        val millisecondsDifference = currentDateCalendar.time.time - startDateCalendar.time.time
        val dayLeft = millisecondsDifference / (24 * 60 * 60 * 1000)

        val timeString = when {
            dayLeft > 1 -> "$dayLeft days"
            dayLeft == 1.toLong() -> "$dayLeft day"
            else -> {
                when (val hourLeft = millisecondsDifference / (60 * 60 * 1000)) {
                    in 2..23 -> "$hourLeft hours"
                    1.toLong() -> "$hourLeft hour"
                    else -> {
                        when (val minuteLeft = millisecondsDifference / (60 * 1000)) {
                            in 2..59 -> "$minuteLeft mins"
                            1.toLong() -> "$minuteLeft min"
                            else -> "few secs"
                        }
                    }
                }
            }
        }

        return "$timeString ago"
    }

    fun getDateTitleForGallery(date: Long): String {
        val targetCalendar = Calendar.getInstance()
        targetCalendar.timeInMillis = date * 1000
        val calendar = Calendar.getInstance()
        return if (isCurrentWeek(calendar, targetCalendar)) "Recent"
        else {
            calendar.add(Calendar.WEEK_OF_YEAR, -1)
            if (isPastWeek(calendar, targetCalendar)) "Last Week"
            else {
                calendar.set(Calendar.DAY_OF_WEEK, 1)
                if (isCurrentMonth(calendar, targetCalendar)) "Last Month"
                else getMonthName(date)
            }
        }
    }

    fun parseDateTime(date: Long?, format: String?): String {
        if (date == null) return ""
        val sdf = SimpleDateFormat(
            if (format.isNullOrEmpty()) {
                "hh:mm a, dd-MMM-YYYY"
            } else {
                format
            }, Locale.ENGLISH
        )
        return sdf.format(Date(date))
    }

    private fun isCurrentWeek(calendar: Calendar, targetCalendar: Calendar): Boolean {
        return calendar[Calendar.WEEK_OF_YEAR] == targetCalendar[Calendar.WEEK_OF_YEAR] && calendar[Calendar.YEAR] == targetCalendar[Calendar.YEAR]
    }

    private fun isPastWeek(calendar: Calendar, targetCalendar: Calendar): Boolean {
        return calendar[Calendar.WEEK_OF_YEAR] == targetCalendar[Calendar.WEEK_OF_YEAR] && calendar[Calendar.YEAR] == targetCalendar[Calendar.YEAR]
    }

    private fun isCurrentMonth(calendar: Calendar, targetCalendar: Calendar): Boolean {
        return calendar[Calendar.YEAR] == targetCalendar[Calendar.YEAR] && calendar[Calendar.MONTH] == targetCalendar[Calendar.MONTH] && calendar[Calendar.DAY_OF_MONTH] > targetCalendar[Calendar.DAY_OF_MONTH]
    }

    private fun getMonthName(date: Long): String {
        return SimpleDateFormat("MMMM", Locale.getDefault()).format(date * 1000)
    }

    fun getDays(t: Long): Int {
        return round(t.toDouble() / MILLIS_IN_DAY).toInt()
    }

    fun getInPurchaseDateFormat(date: Double?): String? {
        if (date == null) return null
        return SimpleDateFormat("DD-MM-yyyy", Locale.getDefault()).format(date)
    }

    fun convertToMillis(date: String?): Long? {
        if (date == null) return null
        return SimpleDateFormat("DD-MM-yyyy", Locale.getDefault()).parse(date)?.time
    }

    fun convertToUnix(currentTimeMillis: Long): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", Locale.getDefault()).format(
            currentTimeMillis
        )
    }

}