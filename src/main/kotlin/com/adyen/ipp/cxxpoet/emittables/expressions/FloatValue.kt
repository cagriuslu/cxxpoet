package com.adyen.ipp.cxxpoet.emittables.expressions

import com.adyen.ipp.cxxpoet.CodeStream

class FloatValue(val value: kotlin.Float) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("${value}F")
    }
}