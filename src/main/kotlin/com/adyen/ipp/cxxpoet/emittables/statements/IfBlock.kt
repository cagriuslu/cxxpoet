package com.adyen.ipp.cxxpoet.emittables.statements

import com.adyen.ipp.cxxpoet.CodeStream
import com.adyen.ipp.cxxpoet.emittables.expressions.Expression

class IfBlock private constructor(
        private val builder: Builder
) : Statement() {

    override fun emitDefinition(out: CodeStream) {
        out.println("if (${builder.expr.emitDefinition()}) {")
        out.increaseIndentation()

        builder.statements.forEach {
            out.println(it.emitDefinition())
        }
        builder.elseIfBlocks.forEach {
            out.decreaseIndentation()
            out.println("} else if (${it.first.emitDefinition()}) {")
            out.increaseIndentation()
            it.second.forEach { st ->
                out.println(st.emitDefinition())
            }
        }
        if (builder.elseStatements.isNotEmpty()) {
            out.decreaseIndentation()
            out.println("} else {")
            out.increaseIndentation()
            builder.elseStatements.forEach {
                out.println(it.emitDefinition())
            }
        }

        out.decreaseIndentation()
        out.println("}")
    }

    class Builder(val expr: Expression) {
        internal val statements: MutableList<Statement> = mutableListOf()
        internal val elseIfBlocks: MutableList<Pair<Expression, List<Statement>>> = mutableListOf()
        internal var elseStatements: MutableList<Statement> = mutableListOf()

        fun build(): IfBlock {
            return IfBlock(this)
        }

        fun addStatement(statement: Statement): Builder {
            statements.add(statement)
            return this
        }

        fun addElseIfBlock(elseIfExpr: Expression, statements: List<Statement>): Builder {
            elseIfBlocks.add(Pair(elseIfExpr, statements))
            return this
        }

        fun addElseStatement(statement: Statement): Builder {
            elseStatements.add(statement)
            return this
        }
    }
}
