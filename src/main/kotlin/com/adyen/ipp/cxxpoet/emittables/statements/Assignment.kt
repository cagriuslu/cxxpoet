package com.adyen.ipp.cxxpoet.emittables.statements

import com.adyen.ipp.cxxpoet.CodeStream
import com.adyen.ipp.cxxpoet.emittables.expressions.Expression
import com.adyen.ipp.cxxpoet.emittables.expressions.ReferenceBase

class Assignment(val lhs: ReferenceBase, val rhs: Expression) : Statement() {
    override fun emitDefinition(out: CodeStream) {
        out.println("${lhs.emitDefinition()} = ${rhs.emitDefinition()};")
    }
}