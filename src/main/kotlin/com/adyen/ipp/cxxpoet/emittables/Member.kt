package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.CodeStream
import com.adyen.ipp.cxxpoet.emittables.expressions.Expression
import com.adyen.ipp.cxxpoet.extensions.prepend
import java.lang.IllegalStateException

class Member(
        val classBuilder: Class.Builder,
        val type: String,
        val name: String,
        val defaultValue: Expression? = null,
        val isStatic: Boolean = false
) : Declarable, Definable {
    override fun emitDeclaration(out: CodeStream) {
        out.print(if (isStatic) {
            "static $type $name;"
        } else {
            val defaultInitialization = defaultValue?.emitDefinition()?.prepend(" = ") ?: ""
            "$type $name$defaultInitialization;"
        })
    }

    override fun emitDefinition(out: CodeStream) {
        if (!isStatic) {
            throw IllegalStateException("Non-static class member does not have a definition")
        }
        val defaultInitialization = defaultValue?.emitDefinition()?.prepend(" = ") ?: ""
        out.print("$type ${classBuilder.globalName}::${name}${defaultInitialization};")
    }
}