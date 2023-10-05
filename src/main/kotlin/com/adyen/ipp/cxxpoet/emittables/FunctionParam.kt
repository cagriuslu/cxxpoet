package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.CodeStream
import com.adyen.ipp.cxxpoet.emittables.expressions.Expression
import com.adyen.ipp.cxxpoet.extensions.prepend

open class FunctionParam(
    val type: String,
    val name: String,
    val defaultValue: Expression? = null
) : Declarable, Definable {
    override fun emitDeclaration(out: CodeStream) {
        val defaultInitialization = defaultValue?.emitDefinition()?.prepend(" = ") ?: ""
        out.print("$type ${name}${defaultInitialization}")
    }

    override fun emitDefinition(out: CodeStream) {
        out.print("$type $name")
    }
}
