package com.adyen.ipp.cxxpoet.emittables.expressions

import com.adyen.ipp.cxxpoet.CodeStream

class Conditional(val condition: Expression, val trueValue: Expression, val falseValue: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${condition.emitDefinition()}) ? (${trueValue.emitDefinition()}) : (${falseValue.emitDefinition()})")
    }
}