package com.adyen.ipp.cxxpoet.emittables.expressions

import com.adyen.ipp.cxxpoet.CodeStream

class UnsignedValue(val value: Int) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("${value}U")
    }
}