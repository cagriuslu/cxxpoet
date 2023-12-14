package com.adyen.ipp.cxxpoet.emittables.statements

import com.adyen.ipp.cxxpoet.CodeStream
import com.adyen.ipp.cxxpoet.emittables.expressions.Expression

class VariableDeclaration(val type: String, val name: String, val defaultValue: Expression? = null) : Statement() {
    override fun emitDefinition(out: CodeStream) {
        if (defaultValue != null) {
            out.println("$type $name = ${defaultValue.emitDefinition()};")
        } else {
            out.println("$type $name;")
        }
    }
}