package com.adyen.ipp.cxxpoet.emittables.expressions

import com.adyen.ipp.cxxpoet.CodeStream

class DoubleValue(val value: kotlin.Double) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("$value")
    }
}