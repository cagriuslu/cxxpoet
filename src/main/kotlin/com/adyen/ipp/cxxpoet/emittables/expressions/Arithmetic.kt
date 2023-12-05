package com.adyen.ipp.cxxpoet.emittables.expressions

import com.adyen.ipp.cxxpoet.CodeStream

class Negate(val oper: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("-(${oper.emitDefinition()})")
    }
}

class Addition(val oper1: Expression, val oper2: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${oper1.emitDefinition()}) + (${oper2.emitDefinition()})")
    }
}

class Subtraction(val oper1: Expression, val oper2: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${oper1.emitDefinition()}) - (${oper2.emitDefinition()})")
    }
}

class Multiplication(val oper1: Expression, val oper2: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${oper1.emitDefinition()}) * (${oper2.emitDefinition()})")
    }
}

class Division(val oper1: Expression, val oper2: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${oper1.emitDefinition()}) / (${oper2.emitDefinition()})")
    }
}

class Remainder(val oper1: Expression, val oper2: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${oper1.emitDefinition()}) % (${oper2.emitDefinition()})")
    }
}

class BitwiseNot(val oper: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("~(${oper.emitDefinition()})")
    }
}

class BitwiseAnd(val oper1: Expression, val oper2: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${oper1.emitDefinition()}) & (${oper2.emitDefinition()})")
    }
}

class BitwiseOr(val oper1: Expression, val oper2: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${oper1.emitDefinition()}) | (${oper2.emitDefinition()})")
    }
}

class BitwiseXor(val oper1: Expression, val oper2: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${oper1.emitDefinition()}) ^ (${oper2.emitDefinition()})")
    }
}

class LeftShift(val oper1: Expression, val oper2: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${oper1.emitDefinition()}) << (${oper2.emitDefinition()})")
    }
}

class RightShift(val oper1: Expression, val oper2: Expression) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${oper1.emitDefinition()}) >> (${oper2.emitDefinition()})")
    }
}
