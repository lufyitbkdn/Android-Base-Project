package com.shapee.sample.utils.extensions


fun Char.isArithmeticOperator(): Boolean {
    return when (this) {
        '+', '-', 'x', '÷', '=' -> true
        else -> false
    }
}