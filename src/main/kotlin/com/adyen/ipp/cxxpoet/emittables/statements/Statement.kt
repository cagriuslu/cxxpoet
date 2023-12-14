package com.adyen.ipp.cxxpoet.emittables.statements

import com.adyen.ipp.cxxpoet.CodeStream
import com.adyen.ipp.cxxpoet.emittables.Definable
import com.adyen.ipp.cxxpoet.emittables.expressions.Expression

sealed class Statement : Definable

class CompoundStatement(val statements: List<Statement>) : Statement() {
    override fun emitDefinition(out: CodeStream) {
        out.println("{")
        out.increaseIndentation()
        statements.forEach {
            out.printIndentation()
            out.print(it.emitDefinition())
        }
        out.decreaseIndentation()
        out.println("}")
    }
}

class ExpressionStatement(val expression: Expression) : Statement() {
    override fun emitDefinition(out: CodeStream) {
        out.println("${expression.emitDefinition()};")
    }
}