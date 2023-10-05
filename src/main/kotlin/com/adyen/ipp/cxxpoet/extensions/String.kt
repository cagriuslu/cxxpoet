package com.adyen.ipp.cxxpoet.extensions

fun String.prepend(s: String?): String {
    return "${s ?: ""}${this}"
}

fun String.append(s: String?): String {
    return "${this}${s ?: ""}"
}