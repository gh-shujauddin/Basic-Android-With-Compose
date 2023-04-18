package com.example.tiptime

import org.junit.Test

import org.junit.Assert.*
import java.text.NumberFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TipCalculatorTests{
    @Test
    fun calculate_20_percent_tip_no_roundup() {
        val amount = 10.0
        val tipPercent = 20.0
        val expectedTip = NumberFormat.getCurrencyInstance(Locale.US).format(2.0)
        val actualTip = calculateTip(amount,tipPercent,false)
        assertEquals(expectedTip, actualTip)
    }
}