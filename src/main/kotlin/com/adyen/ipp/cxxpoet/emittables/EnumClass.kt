package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.CodeStream
import com.adyen.ipp.cxxpoet.extensions.append
import com.adyen.ipp.cxxpoet.extensions.prepend
import com.adyen.ipp.cxxpoet.properties.Namespace

class EnumClass private constructor(
    private val builder: Builder
) : ForwardDeclarable, Declarable {

    override fun emitForwardDeclaration(out: CodeStream) {
        // Namespace
        if (builder.namespace != null) {
            out.println("${Namespace.keyword} ${builder.namespace} {")
            out.increaseIndentation()
        }

        // Enum name
        out.println("enum class ${builder.name};")

        // End namespace
        if (builder.namespace != null) {
            out.decreaseIndentation()
            out.println("}")
        }
    }

    override fun emitDeclaration(out: CodeStream) {
        // Namespace
        if (builder.namespace != null) {
            out.println("${Namespace.keyword} ${builder.namespace} {")
            out.increaseIndentation()
        }

        // Enum name
        out.println("enum class ${builder.name} {")
        out.increaseIndentation()

        // Options
        builder.options.forEach {
            out.println(it.first
                .append(it.second?.toString()?.prepend(" = "))
                .append(","))
        }

        // End enum
        out.decreaseIndentation()
        out.println("};")

        // End namespace
        if (builder.namespace != null) {
            out.decreaseIndentation()
            out.println("}")
        }
    }

    class Builder(val name: String, val namespace: Namespace? = null) {
        internal val options: MutableList<Pair<String, Int?>> = mutableListOf()

        val globalName = "${namespace?.toGlobalPrefix() ?: ""}${name}"

        fun build(): EnumClass {
            return EnumClass(this)
        }

        fun addOption(option: String, int: Int? = null): Builder {
            options.add(Pair(option, int))
            return this
        }
    }
}