package com.adyen.ipp.cxxpoet.extensions

fun <T> List<T>.nonEmptyListOrNull() : List<T>? {
    return this.ifEmpty { null }
}