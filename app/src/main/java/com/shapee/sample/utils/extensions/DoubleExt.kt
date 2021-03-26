package com.shapee.sample.utils.extensions

import java.text.DecimalFormat

fun Double.currencyFormat(): String =
    DecimalFormat("#.##").format(this)