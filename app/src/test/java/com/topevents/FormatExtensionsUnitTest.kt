package com.topevents

import com.topevents.util.parseIsoDate
import com.topevents.util.toStringCurrencyFormat
import com.topevents.util.toStringDateFormat
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FormatExtensionsUnitTest {

    @Test
    fun parseIsoDate() {
        assertEquals(1510002900000L, "2017-11-07T8:15:00Z".parseIsoDate().time)
        assertEquals(1525622400000L, "2018-05-07T02:00:00Z".parseIsoDate().time)
    }

    @Test
    fun stringDateToFormat() {
        assertEquals("Tuesday 7 Nov 2017 - 08:15", "2017-11-07T8:15:00Z".parseIsoDate().toStringDateFormat())
        assertEquals("Monday 7 May 2018 - 02:00", "2018-05-07T02:00:00Z".parseIsoDate().toStringDateFormat())
    }

    @Test
    fun doubleTenFormatCurrency() {
        assertEquals("$10.00", 10.0.toStringCurrencyFormat())
    }

    @Test
    fun doubleZeroFormatCurrency() {
        assertEquals("$0.00", 0.0.toStringCurrencyFormat())
    }
}