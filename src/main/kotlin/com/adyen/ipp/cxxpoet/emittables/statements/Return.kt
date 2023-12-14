package com.adyen.ipp.cxxpoet.emittables.statements

import com.adyen.ipp.cxxpoet.CodeStream
import com.adyen.ipp.cxxpoet.emittables.expressions.Expression

class Return(val expr: Expression) : Statement() {
    override fun emitDefinition(out: CodeStream) {
        out.println("return ${expr.emitDefinition()};")
    }
}