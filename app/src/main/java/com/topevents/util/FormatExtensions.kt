package com.topevents.util

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun Double.toStringCurrencyFormat(): String = DecimalFormat("$#,##0.00").format(this)
fun Date.toStringDateFormat(): String = SimpleDateFormat(SHORT_MONTH_WITH_DAY_TIME, Locale.ENGLISH).format(this.time)
fun String.parseIsoDate(): Date = SimpleDateFormat(RFC_822_DATE_TIME_PATTERN, Locale.getDefault()).parse(this)

private const val RFC_822_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private const val SHORT_MONTH_WITH_DAY_TIME = "EEEE d MMM yyyy - HH:mm"