package com.adyen.ipp.cxxpoet.emittables.expressions

import com.adyen.ipp.cxxpoet.CodeStream

class EqualityComparison(val lhs: Expression, val rhs: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${lhs.emitDefinition()}) == (${rhs.emitDefinition()})")
    }
}