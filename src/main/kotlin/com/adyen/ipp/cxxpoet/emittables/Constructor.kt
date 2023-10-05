package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.CodeStream
import com.adyen.ipp.cxxpoet.emittables.statements.Statement
import com.adyen.ipp.cxxpoet.extensions.prepend
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

// TODO make constructor class, make default constructors companion object functions that generate constructors

class Constructor private constructor(private val builder: Builder) : Declarable, Definable {
    enum class Type {
        DEFAULT_CONSTRUCTOR,
        COPY_CONSTRUCTOR,
        COPY_ASSIGNMENT_OPERATOR,
        MOVE_CONSTRUCTOR,
        MOVE_ASSIGNMENT_OPERATOR,
        DESTRUCTOR,
        CUSTOM_CONSTRUCTOR
    }

    override fun emitDeclaration(out: CodeStream) {
        val className = builder.owner.name
        val state = when (builder.implementation) {
            Builder.Implementation.DEFAULT -> " = default"
            Builder.Implementation.DELETED -> " = delete"
            else -> ""
        }

        out.print(when (builder.constructorType) {
            Type.DEFAULT_CONSTRUCTOR -> "${className}()${state};"
            Type.COPY_CONSTRUCTOR -> "${className}(const ${className}&)${state};"
            Type.COPY_ASSIGNMENT_OPERATOR -> "${className}& operator=(const ${className}&)${state};"
            Type.MOVE_CONSTRUCTOR -> "${className}(${className}&&)${state};"
            Type.MOVE_ASSIGNMENT_OPERATOR -> "${className}& operator=(${className}&&)${state};"
            Type.DESTRUCTOR -> "~${className}()${state};".prepend(if (builder.isVirtual) "virtual " else null)
            Type.CUSTOM_CONSTRUCTOR -> {
                val params = builder.params.joinToString { it.emitDeclaration() }
                "${className}($params);"
            }
        })
    }

    override fun emitDefinition(out: CodeStream) {
        TODO("Not yet implemented")
    }

    class Builder(val owner: Class.Builder, val constructorType: Type, val implementation: Implementation, val isVirtual: Boolean = false) {
        enum class Implementation {
            CUSTOM,
            DEFAULT,
            DELETED
        }

        internal val params: MutableList<FunctionParam> = mutableListOf()
        internal val statements: MutableList<Statement> = mutableListOf()

        init {
            if (implementation == Implementation.DEFAULT || implementation == Implementation.DELETED) {
                if (constructorType == Type.CUSTOM_CONSTRUCTOR) {
                    throw IllegalArgumentException("Custom constructor cannot be defaulted or deleted")
                }
            }
            if (isVirtual && constructorType != Type.DESTRUCTOR) {
                throw IllegalArgumentException("Only the destructor can be virtual")
            }
        }

        fun build(): Constructor {
            return Constructor(this)
        }

        fun addParam(param: FunctionParam): Builder {
            if (constructorType != Type.CUSTOM_CONSTRUCTOR) {
                throw IllegalStateException("Only the custom constructor can hold parameters")
            }
            params.add(param)
            return this
        }

        fun addStatement(statement: Statement): Builder {
            if (implementation != Implementation.CUSTOM) {
                throw IllegalStateException("Only the custom implementations can hold statements")
            }
            statements.add(statement)
            return this
        }
    }
}