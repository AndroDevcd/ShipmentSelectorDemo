package com.intuisoft.moderncalc.shipmentselector.ui

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class AppDateTimeFormatter() {

    fun formatTimeFromDate(date: Instant): String {
        val localDate = date.atZone(ZoneId.systemDefault())
        return DateTimeFormatter.ofPattern("MMMM dd, yyyy h:mm a").format(localDate)
    }
}
