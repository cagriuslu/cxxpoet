package com.adyen.ipp.cxxpoet.emittables.expressions

import com.adyen.ipp.cxxpoet.CodeStream

class BooleanValue(val value: Boolean) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print(value.toString())
    }
}

class DoubleValue(val value: kotlin.Double) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("$value")
    }
}

class FloatValue(val value: kotlin.Float) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("${value}F")
    }
}

class IntegerValue(val value: Int) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("$value")
    }
}

class LongValue(val value: Long) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("${value}L")
    }
}

class StringValue(val value: String) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("\"${value}\"")
    }
}

class UnsignedValue(val value: UInt) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("${value}U")
    }
}

class UnsignedLongValue(val value: ULong) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print("${value}LU")
    }
}
