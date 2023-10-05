package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.CodeStream
import com.adyen.ipp.cxxpoet.emittables.statements.Statement
import com.adyen.ipp.cxxpoet.extensions.append
import com.adyen.ipp.cxxpoet.extensions.prepend
import java.lang.IllegalArgumentException

class Method private constructor(private val builder: Builder) : Declarable, Definable {

    override fun emitDeclaration(out: CodeStream) {
        val params = builder.params.joinToString { it.emitDeclaration() }
        out.print("${builder.returnType} ${builder.name}(${params})"
            .prepend(if (builder.isVirtual) "virtual " else "")
            .prepend(if (builder.isStatic) "static " else "")
            .append(if (builder.isConst) " const" else "")
            .append(if (builder.isOverride) " override" else "")
            .append(";")
        )
    }

    override fun emitDefinition(out: CodeStream) {
        TODO("Not yet implemented")
    }

    class Builder(
        val owner: Class.Builder,
        val returnType: String,
        val name: String,
        val isVirtual: Boolean = false,
        val isStatic: Boolean = false,
        val isConst: Boolean = false,
        val isOverride: Boolean = false) {
        internal val params: MutableList<FunctionParam> = mutableListOf()
        internal val statements: MutableList<Statement> = mutableListOf()

        init {
            if (isVirtual) {
                if (isStatic) {
                    throw IllegalArgumentException("A method cannot be both virtual and static")
                }
            }
            if (isStatic) {
                if (isConst || isOverride) {
                    throw IllegalArgumentException("A static method cannot be virtual, const or override")
                }
            }
        }

        fun build(): Method {
            return Method(this)
        }

        fun addParam(param: FunctionParam): Builder {
            params.add(param)
            return this
        }

        fun addStatement(statement: Statement): Builder {
            statements.add(statement)
            return this
        }
    }
}