package com.adyen.ipp.cxxpoet.emittables.expressions

import com.adyen.ipp.cxxpoet.CodeStream

class IntegerValue(val value: Int) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("$value")
    }
}