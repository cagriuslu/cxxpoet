package com.adyen.ipp.cxxpoet.emittables.expressions

import com.adyen.ipp.cxxpoet.CodeStream

class FunctionCall(val function: String, val params: List<Expression>) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("${function}(${params.joinToString { it.emitDefinition() }})")
    }
}