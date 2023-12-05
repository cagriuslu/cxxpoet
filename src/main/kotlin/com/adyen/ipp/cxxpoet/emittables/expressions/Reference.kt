package com.adyen.ipp.cxxpoet.emittables.expressions

import com.adyen.ipp.cxxpoet.CodeStream

sealed class ReferenceBase : Expression()

class Reference(val name: String) : ReferenceBase() {
    override fun emitDefinition(out: CodeStream) {
        out.print(name)
    }
}

class AddressOf(val reference: ReferenceBase) : ReferenceBase() {
    override fun emitDefinition(out: CodeStream) {
        out.print("&(${reference.emitDefinition()})")
    }
}

class Indirection(val reference: ReferenceBase) : ReferenceBase() {
    override fun emitDefinition(out: CodeStream) {
        out.print("*(${reference.emitDefinition()})")
    }
}

class Subscript(val reference: ReferenceBase, val expression: Expression) : ReferenceBase() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${reference.emitDefinition()})[${expression.emitDefinition()}]")
    }
}

class MemberOfObject(val reference: ReferenceBase, val memberName: String) : ReferenceBase() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${reference.emitDefinition()}).${memberName}")
    }
}

class MemberOfPointer(val reference: ReferenceBase, val memberName: String) : ReferenceBase() {
    override fun emitDefinition(out: CodeStream) {
        out.print("(${reference.emitDefinition()})->${memberName}")
    }
}
