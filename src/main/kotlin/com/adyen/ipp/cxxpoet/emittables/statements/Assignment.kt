package com.adyen.ipp.cxxpoet.emittables.statements

import com.adyen.ipp.cxxpoet.CodeStream
import com.adyen.ipp.cxxpoet.emittables.expressions.Expression

class Assignment(val lhs: String, val rhs: Expression) : Statement() {
    override fun emitDefinition(out: CodeStream) {
        out.print("$lhs = ${rhs.emitDefinition()};")
    }
}