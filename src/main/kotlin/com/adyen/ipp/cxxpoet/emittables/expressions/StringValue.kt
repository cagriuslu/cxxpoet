package com.adyen.ipp.cxxpoet.emittables.expressions

import com.adyen.ipp.cxxpoet.CodeStream

class StringValue(val value: String) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("\"${value}\"")
    }
}
