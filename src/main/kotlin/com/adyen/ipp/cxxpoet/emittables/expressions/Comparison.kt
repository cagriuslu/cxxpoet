package com.adyen.ipp.cxxpoet.emittables.expressions

import com.adyen.ipp.cxxpoet.CodeStream

class Not(val expr: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("!(${expr.emitDefinition()})")
    }
}

class And(val lhs: Expression, val rhs: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${lhs.emitDefinition()}) && (${rhs.emitDefinition()})")
    }
}

class Or(val lhs: Expression, val rhs: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${lhs.emitDefinition()}) || (${rhs.emitDefinition()})")
    }
}

class EqualTo(val lhs: Expression, val rhs: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${lhs.emitDefinition()}) == (${rhs.emitDefinition()})")
    }
}

class NotEqualTo(val lhs: Expression, val rhs: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${lhs.emitDefinition()}) != (${rhs.emitDefinition()})")
    }
}

class LessThan(val lhs: Expression, val rhs: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${lhs.emitDefinition()}) < (${rhs.emitDefinition()})")
    }
}

class GreaterThan(val lhs: Expression, val rhs: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${lhs.emitDefinition()}) > (${rhs.emitDefinition()})")
    }
}

class LessOrEqualTo(val lhs: Expression, val rhs: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${lhs.emitDefinition()}) <= (${rhs.emitDefinition()})")
    }
}

class GreaterOrEqualTo(val lhs: Expression, val rhs: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${lhs.emitDefinition()}) >= (${rhs.emitDefinition()})")
    }
}
