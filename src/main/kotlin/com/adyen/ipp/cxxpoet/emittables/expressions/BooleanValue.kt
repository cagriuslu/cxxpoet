package com.adyen.ipp.cxxpoet.emittables.expressions

import com.adyen.ipp.cxxpoet.CodeStream

class BooleanValue(val value: Boolean) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print(value.toString())
    }
}